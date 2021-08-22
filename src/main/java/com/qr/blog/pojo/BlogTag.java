package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/4-14:14
 */
@Data
public class BlogTag implements Serializable {

    /**
     * 利用雪花算法生成 id
     */
    private Long id;

    private Long blogId;

    private Long tagId;

    private String tagName;
}
