package com.qr.blog.controller;

import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.vo.UploadVo;
import com.qr.blog.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: QR
 * @Date: 2021/8/6-13:36
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping( "/uploadFile")
    public Result<UploadVo> uploadFile(MultipartFile file){
        String path = uploadService.uploadFile(file);
        return new Result<>(new UploadVo(path, Objects.requireNonNull(file.getOriginalFilename()), UUID.randomUUID().toString()));
    }
}
