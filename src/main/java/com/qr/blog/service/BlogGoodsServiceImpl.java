package com.qr.blog.service;

import com.qr.blog.mapper.BlogGoodsMapper;
import com.qr.blog.mapper.UserMapper;
import com.qr.blog.pojo.BlogGoods;
import com.qr.blog.service.interfaces.BlogGoodsService;
import com.qr.blog.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:21
 */
@Service
public class BlogGoodsServiceImpl implements BlogGoodsService {

    @Resource
    private BlogGoodsMapper blogGoodsMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void goods(Long blogId) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Long userId = userMapper.getUserByUsername(username).getUserId();
        BlogGoods blogGoods = new BlogGoods(blogId, userId);
        blogGoodsMapper.save(blogGoods);
    }

    @Override
    public Integer getGoods(Long blogId) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Long userId = userMapper.getUserByUsername(username).getUserId();
        return blogGoodsMapper.getCountByBlogIdAndUserId(blogId, userId);
    }
}
