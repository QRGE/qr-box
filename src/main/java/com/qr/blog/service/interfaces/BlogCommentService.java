package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.BlogComment;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.vo.BlogCommentVo;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:35
 */
public interface BlogCommentService {

    /**
     * 保存评论
     * @param blogComment 评论信息
     */
    void save(BlogComment blogComment);

    /**
     * 根据 blogId 查询对应博客的评论
     * @param blogId blogId
     * @return 查询的评论集
     */
    List<BlogComment> getByBlogId(Long blogId);

    /**
     * 分页查询 blog 和其平路内容
     * @param page page
     * @return 查询结果
     */
    Page<BlogCommentVo> getByPage(Page<BlogCommentVo> page);
}
