package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:29
 */
@Data
public class BlogComment implements Serializable {

    private Long id;
    private Long blogId;
    private Long userId;
    private String header;
    private String nickname;
    private String content;
    private String createdTime;
}
