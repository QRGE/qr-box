package com.qr.blog.service;

import com.qr.blog.mapper.BlogCollectionMapper;
import com.qr.blog.mapper.BlogMapper;
import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.BlogCollection;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.service.interfaces.BlogCollectionService;
import com.qr.blog.service.interfaces.UserService;
import com.qr.blog.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:04
 */
@Service
public class BlogCollectionServiceImpl implements BlogCollectionService {


    @Resource
    private BlogCollectionMapper blogCollectionMapper;

    @Resource
    private UserService userService;

    @Resource
    private BlogMapper blogMapper;

    @Override
    public void collection(Long blogId) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Long userId = userService.getUserByUsername(username).getUserId();
        BlogCollection blogCollection = new BlogCollection(blogId, userId);
        blogCollectionMapper.save(blogCollection);
    }

    @Override
    public Integer getCollection(Long blogId) {
        return null;
    }

    @SuppressWarnings("all")
    @Override
    public Page<Blog> getByPage(Page<Blog> page) {
        Long userId = ShiroUtils.getLoginUser().getUserId();
        List<BlogCollection> blogCollectionList = blogCollectionMapper.getByPage(page, userId);
        Integer count = blogCollectionMapper.getCountByPage(page, userId);
        page.setTotalCount(count);
        if (!CollectionUtils.isEmpty(blogCollectionList)){
            List<Long> blogIds = blogCollectionList.stream().map(BlogCollection::getBlogId).collect(Collectors.toList());
            List<Blog> blogList = blogMapper.getByIds(blogIds);
            page.setList(blogList);
        }else { // 没查询到评论博客
            page.setList(new ArrayList<>(0));
        }
        return page;
    }
}
