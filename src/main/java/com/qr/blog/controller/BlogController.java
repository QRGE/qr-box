package com.qr.blog.controller;

import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.dto.BlogDto;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.vo.BlogVo;
import com.qr.blog.service.interfaces.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-14:01
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    /**
     * 保存博客
     * @param blogDto 传输博客数据对象
     * @return 保存结果
     */
    @PostMapping("/save")
    public Result<?> save(@RequestBody BlogDto blogDto){
        blogService.save(blogDto);
        return new Result<>("Add a blog!");
    }

    /**
     * 更新博客内容
     * @param blogDto 后台传入的新的博客数据
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody BlogDto blogDto){
        blogService.update(blogDto);
        return new Result<>("Update some info...");
    }

    /**
     * 根据 id 删除博客, 逻辑删除
     * @param id 待删除博客的 id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        blogService.deleteById(id);
        return new Result<>("Delete a blog!");
    }

    /**
     * 根据 后台传入的 page 对象进行分页查询
     * @param page page对象
     * @return 查询结果
     */
    @PostMapping ("/getByPage")
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page){
        page = blogService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 通过 id 查询用于修改的博客数据
     * @param id id
     * @return 查询的 blog
     */
    @GetMapping("/getUpdate/{id}")
    public Result<Blog> getUpdate(@PathVariable Long id){
        Blog blog = blogService.getUpdate(id);
        return new Result<>(blog);
    }

    /**
     * 通过 id 查询用于后台显示的博客详情数据
     * @param id id
     * @return 查询结果
     */
    @GetMapping("/getInfo/{id}")
    public Result<BlogVo> getInfo(@PathVariable Long id){
        BlogVo blogVo = blogService.getInfo(id);
        return new Result<>(blogVo);
    }

    /**
     * 获取时间轴
     * @param index 当前博客数量
     * @return 时间轴 + 返回状态
     */
    @GetMapping("/getTimeLine/{index}")
    public Result<List<Blog>> getTimeLine(@PathVariable Integer index) {
        List<Blog> timeLine = blogService.getTimeLine(index);
        return new Result<>(timeLine);
    }
}
