package com.qr.blog.pojo.vo;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/6-13:39
 */
@Data
public class UploadVo implements Serializable {

    /**
     * 文件路径
     */
    @NonNull
    private String url;

    /**
     * 文件名字
     */
    @NonNull
    private String name;

    /**
     * uid
     */
    @NonNull
    private String uid;
}
