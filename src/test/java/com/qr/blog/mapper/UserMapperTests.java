package com.qr.blog.mapper;

import com.qr.blog.BlogApplication;
import com.qr.blog.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/20-19:35
 */
@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void getAdminInfoTest() {
        User user = userMapper.getUserByUsername("admin");
        System.out.println(user);
    }
}
