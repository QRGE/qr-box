package com.qr.blog.controller;

import com.qr.blog.pojo.BlogGoods;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.service.interfaces.BlogGoodsService;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:22
 */
@RestController
@RequestMapping("/goods")
public class BlogGoodsController {

    @Resource
    private BlogGoodsService blogGoodsService;

    /**
     * 点赞
     * @param blogId 点赞的 blogId
     * @return 点赞结果
     */
    @PostMapping("/goods/{blogId}")
    public Result<?> goods(@PathVariable Long blogId) {
        blogGoodsService.goods(blogId);
        return new Result<>("感谢点赞 ψ(｀∇´)ψ");
    }

    @GetMapping("/getGoods/{blogId}")
    public Result<Integer> getGoods(@PathVariable Long blogId) {
        Integer count = blogGoodsService.getGoods(blogId);
        return new Result<>(count);
    }
}
