package com.qr.blog.controller;

import com.qr.blog.pojo.Type;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.service.interfaces.TypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-15:45
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * 保存分类
     * @param type 接受前端的 type 信息
     * @return 保存结果
     */
    @PostMapping("/save")
    public Result<?> save(@RequestBody Type type){
        typeService.save(type);
        return new Result<>("Save a Blog type");
    }

    /**
     * 根据 id 查询分类
     * @param id 分类id
     * @return 查询结果
     */
    @GetMapping("/get/{id}")
    public Result<Type> get(@PathVariable Long id){
        Type type = typeService.getById(id);
        return new Result<>(type);
    }

    /**
     * 根据传入的 type 的内容修改对应的 type
     * @param type 新 type 内容
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Type type){
        typeService.update(type);
        return new Result<>("Update a Type");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id){
        typeService.delete(id);
        return new Result<>("Delete a Type");
    }

    @GetMapping("/list")
    public Result<List<Type>> list(){
        List<Type> typeList = typeService.getAllTypes();
        return new Result<>(typeList);
    }
}
