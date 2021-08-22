package com.qr.blog.controller;

import com.qr.blog.annotation.LogIgnore;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.SysLog;
import com.qr.blog.service.interfaces.SysLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/1-22:20
 */
@RestController
@RequestMapping("/sysLog")
public class LogController {

    @Resource
    private SysLogService sysLogService;


    /**
     * 分页查询日志记录
     * @param page 分页对象
     * @return 查询的分页结果
     */
    @PostMapping("/getByPage")
    @LogIgnore
    public Result<Page<SysLog>> getByPage(@RequestBody Page<SysLog> page){
        page = sysLogService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据 id 查询对应的日志记录
     * @param id id
     * @return 查询的日志记录
     */
    @GetMapping("/get/{id}")
    @LogIgnore
    public Result<SysLog> getById(@PathVariable Long id){
        SysLog sysLog = sysLogService.getById(id);
        return new Result<>(sysLog);
    }

    /**
     * 根据 id 删除对应的日志
     * @param id id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @LogIgnore
    public Result<?> deleteById(@PathVariable Long id){
        sysLogService.deleteById(id);
        return new Result<>("Delete a log!");
    }
}
