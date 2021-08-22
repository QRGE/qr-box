package com.qr.blog.controller;

import com.qr.blog.pojo.Tag;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.vo.HotTagVo;
import com.qr.blog.service.interfaces.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-13:49
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 根据名称搜索标签
     * @param name 搜索的标签名
     * @return 搜索结果集
     */
    @GetMapping("/searchByName/{name}")
    public Result<List<Tag>> searchByName(@PathVariable String name){
        List<Tag> tags = tagService.getByName(name);
        return new Result<>(tags);
    }

    /**
     * 根据博客 Id 查询 tag
     * @param blogId blogId
     * @return 查询的 tags
     */
    @GetMapping("/getByBlogId/{blogId}")
    public Result<List<Tag>> getByBlogId(@PathVariable Long blogId){
        List<Tag> tags = tagService.getByBlogId(blogId);
        return new Result<>(tags);
    }

    /**
     * 获取热门标签
     * @return 热门标签集合
     */
    @GetMapping("/getHotTag")
    public Result<List<HotTagVo>> getHotTag(){
        List<HotTagVo> hotTags = tagService.getHotTags();
        return new Result<>(hotTags);
    }
}
