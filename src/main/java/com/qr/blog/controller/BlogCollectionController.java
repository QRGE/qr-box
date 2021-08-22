package com.qr.blog.controller;

import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.service.interfaces.BlogCollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:17
 */
@RestController
@RequestMapping("/collection")
public class BlogCollectionController {

    @Resource
    private BlogCollectionService blogCollectionService;

    /**
     * 点赞
     * @param blogId 点赞的 blogId
     * @return 点赞结果
     */
    @PostMapping("/collection/{blogId}")
    public Result<?> goods(@PathVariable Long blogId) {
        blogCollectionService.collection(blogId);
        return new Result<>("感谢收藏 (´▽`ʃ♡ƪ)");
    }

    @GetMapping("/getCollection/{blogId}")
    public Result<Integer> getGoods(@PathVariable Long blogId) {
        Integer count = blogCollectionService.getCollection(blogId);
        return new Result<>(count);
    }

    /**
     * 分页查询评论过的博客
     * @param page 分页对象信息
     * @return 查询结果
     */
    @PostMapping("/getByPage")
    public Result<Page<Blog>> getByPage(@RequestBody Page<Blog> page){
        page = blogCollectionService.getByPage(page);
        return new Result<>(page);
    }
}
