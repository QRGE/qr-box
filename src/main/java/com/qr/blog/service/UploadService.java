package com.qr.blog.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qr.blog.config.dfs.UploadProperties;
import com.qr.blog.exception.BlogException;
import com.qr.blog.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/6-13:09
 */
@Service
@Slf4j
public class UploadService {

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Resource
    private UploadProperties uploadProperties;

    /**
     * 上传文件
     * @param file 上传的文件
     * @return 文件地址
     */
    public String uploadFile(MultipartFile file){
        String contentType = file.getContentType();
        // 检验上传文件的类型
        if (!uploadProperties.getAllowTypes().contains(contentType)){
            throw new BlogException("暂不支持上传文件的类型...");
        }
        // 上传文件
        try {
            String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), suffix, null);
            return uploadProperties.getBaseUrl() + storePath.getFullPath();
        }catch (Exception e){
            log.error("上传文件发生错误! {}", e.getMessage());
            throw new BlogException("上传文件发生错误! " + e.getMessage());
        }
    }
}
