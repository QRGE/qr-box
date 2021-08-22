package com.qr.blog.controller;

import com.qr.blog.pojo.BlogComment;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.vo.BlogCommentVo;
import com.qr.blog.service.interfaces.BlogCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:37
 */
@RestController
@RequestMapping("/comment")
public class BlogCommentController {

    @Resource
    private BlogCommentService blogCommentService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody BlogComment blogComment) {
        blogCommentService.save(blogComment);
        return new Result<>("感谢评论! ( •̀ ω •́ )✧");
    }

    /**
     * 根据 blogId 查询对应博客的评论
     * @param blogId blogId
     * @return 查询的评论集 + 查询结果
     */
    @GetMapping("/getByBlogId/{blogId}")
    public Result<List<BlogComment>> getByBlogId(@PathVariable Long blogId){
        List<BlogComment> commentList = blogCommentService.getByBlogId(blogId);
        return new Result<>(commentList);
    }

    /**
     * 根据 page 查询评论和博客
     * @param page page
     * @return 查询结果
     */
    @PostMapping("/getByPage")
    public Result<Page<BlogCommentVo>> getByPage(@RequestBody Page<BlogCommentVo> page){
        page = blogCommentService.getByPage(page);
        return new Result<>(page);
    }
}
