package service;

import entity.User;
import mapper.ItemMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    RedisService redisService;

    @Override
    public int insertUser(User user) {
        userMapper.insert(user);
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    /**
     * 使用@CacheEvict注解，将数据中删除的数据从缓存中删除
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    @CacheEvict(value = "user",key = "'uid_'+#id")
    public int deleteUser(int id) {
        itemMapper.deleteByUserId(id);
        userMapper.deleteByPrimaryKey(id);
        return 0;
    }

    /**
     * 使用@Cachable注解，当查询时先查找缓存中的数据，没有则在数据库中查询然后添加到缓存中
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "user",key = "'uid_'+#id")
    public User selectUser(int id) {
        User user= (User) redisService.getObj(id);
        if (user==null){
            user=userMapper.selectByPrimaryKey(id);
            System.out.println("select mysql:"+user.getId());
            redisService.setObj(id,user);
        }else{
            System.out.println("select redis:"+user.getId());
        }
        return user;
    }
}
