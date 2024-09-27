package com.quan.communityhelpuserCenterManager.redis;

import com.quan.communityhelpModel.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @className: redisTest
 * @author: quan
 * @date: 2024:09:04
 * @Task:
 */

@SpringBootTest
public class redisTest {
//region redis连接测试
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Test
    public void addTest() {
        ValueOperations<String, Object> stringStringValueOperations = redisTemplate.opsForValue();

        User user = new User();
        user.setUserAccount("xiongquan");
        user.setUserName("xiongquan");
        user.setUserPassword("123456789");
        stringStringValueOperations.set("user:xiongquan", user);
        Object o = stringStringValueOperations.get("user:xiongquan");
        System.out.println(o);
    }

    @Test
    public void deleteTest() {

        System.out.println(redisTemplate.hasKey("user:xiongquan"));
        redisTemplate.delete("user:xiongquan");
        System.out.println(redisTemplate.hasKey("user:xiongquan"));
    }


}
