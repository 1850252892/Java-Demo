package mapper;

import entity.Item;
import org.apache.ibatis.annotations.Delete;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);

    @Delete("delete from tb_item where uid=#{id}")
    int deleteByUserId(Integer id);
}