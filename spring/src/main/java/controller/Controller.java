package controller;

import com.alibaba.fastjson.JSON;
import entity.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.RedisService;
import service.UserService;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @PostMapping(value = "/api/user")
    public String apiaddUser(@RequestBody User user){
        userService.insertUser(user);

        return JSON.toJSONString("success");
    }

    @PostMapping(value = "/api/useritem")
    public String apideleteUser(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        userService.deleteUser(id);
        return JSON.toJSONString("result");
    }

    @GetMapping(value = "/api/user")
    public String apiGetUser(@RequestParam String uid){
        User user=userService.selectUser(Integer.valueOf(uid));

        return JSON.toJSONString(user);
    }

    @GetMapping(value = "/test/redis")
    public void apiRedisTest(){
        redisService.setStr("a","a");
        String a= (String) redisService.getObj("a");
        String a1= redisService.getStr("a");
        System.out.println("a="+a);
        System.out.println("a1="+a1);

        redisService.setObj("aaa","aaa");
        String aaa=redisService.getStr("aaa");
        String aaa1= (String) redisService.getObj("aaa");
        System.out.println("aaa="+aaa);
        System.out.println("aaa1="+aaa1);
    }

}
