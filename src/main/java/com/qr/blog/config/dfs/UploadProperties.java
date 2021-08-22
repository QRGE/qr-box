package com.qr.blog.config.dfs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * FastDfs 配置
 * @Author: QR
 * @Date: 2021/8/6-13:02
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {

    private String baseUrl;
    private List<String> allowTypes;
}
