package com.qr.blog.controller;

import com.qr.blog.pojo.Link;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.service.interfaces.LinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-23:27
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    /**
     * 保存友链 link
     * @param link link
     * @return 保存成功信息
     */
    @PostMapping("/save")
    public Result<?> save(@RequestBody Link link){
        linkService.save(link);
        return new Result<>("添加Link: " + link.getLinkName() + " 成功");
    }

    /**
     * 通过 id 获取对应的 link
     * @param id id
     * @return 获取的link
     */
    @GetMapping("/get/{id}")
    public Result<Link> getById(@PathVariable Long id){
        Link link = linkService.getById(id);
        return new Result<>(link);
    }

    /**
     * 修改 link
     * @param link link
     * @return 修改后的结果
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Link link){
        linkService.update(link);
        return new Result<>("Update a link");
    }


    @DeleteMapping("/delete/{id}")
    public Result<?> deleteById(@PathVariable Long id){
        linkService.deleteById(id);
        return new Result<>("Delete a link");
    }

    @GetMapping("/list")
    public Result<List<Link>> linkList(){
        List<Link> linkList = linkService.getAllLink();
        return new Result<>(linkList);
    }
}
