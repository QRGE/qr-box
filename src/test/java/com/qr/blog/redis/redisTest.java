package com.qr.blog.redis;

import com.qr.blog.BlogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/19-16:00
 */
@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
public class redisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testString(){
        stringRedisTemplate.opsForValue().set("name", "ZhangSan");
    }
}
