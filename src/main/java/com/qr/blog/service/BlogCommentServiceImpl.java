package com.qr.blog.service;

import com.qr.blog.mapper.BlogCommentMapper;
import com.qr.blog.mapper.BlogMapper;
import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.BlogComment;
import com.qr.blog.pojo.User;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.vo.BlogCommentVo;
import com.qr.blog.service.interfaces.BlogCommentService;
import com.qr.blog.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:35
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Resource
    private BlogMapper blogMapper;

    @SuppressWarnings("all")
    @Override
    public void save(BlogComment blogComment) {
        User loginUser = ShiroUtils.getLoginUser();
        // 设置 blogComment 中 user 相关的字段
        blogComment.setUserId(loginUser.getUserId());
        blogComment.setBlogId(blogComment.getBlogId());
        blogComment.setHeader(loginUser.getHeader());
        blogComment.setNickname(loginUser.getNickname());
        blogCommentMapper.save(blogComment);
    }

    @Override
    public List<BlogComment> getByBlogId(Long blogId) {
        return blogCommentMapper.getByBlogId(blogId);
    }

    @SuppressWarnings("all")
    @Override
    public Page<BlogCommentVo> getByPage(Page<BlogCommentVo> page) {
        Long userId = ShiroUtils.getLoginUser().getUserId();
        List<BlogComment> blogCommentList = blogCommentMapper.getByPage(page, userId);
        Integer count = blogCommentMapper.getCountByPage(page, userId);
        page.setTotalCount(count);
        if (!CollectionUtils.isEmpty(blogCommentList)){
            List<Long> blogIds = blogCommentList.stream().map(BlogComment::getBlogId).collect(Collectors.toList());
            blogIds = new ArrayList<>(new HashSet<>(blogIds));
            List<Blog> blogList = blogMapper.getByIds(blogIds);
            // 封装 blogCommentVos 集
            List<BlogCommentVo> blogCommentVos = blogCommentList.stream().map(e -> {
                BlogCommentVo blogCommentVo = new BlogCommentVo();
                blogCommentVo.setContent(e.getContent());
                Blog blog = blogList.stream().filter(b -> b.getBlogId().equals(e.getBlogId())).findFirst().orElse(new Blog());
                blogCommentVo.setBlog(blog);
                return blogCommentVo;
            }).collect(Collectors.toList());
            page.setList(blogCommentVos);
        }else { // 没查询到评论博客
            page.setList(new ArrayList<>(0));
        }
        return page;
    }
}
