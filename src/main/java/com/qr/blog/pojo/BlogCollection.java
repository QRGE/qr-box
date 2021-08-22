package com.qr.blog.pojo;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:17
 */
@Data
public class BlogCollection implements Serializable {

    /**
     * 自增 id
     */
    private Long id;

    @NonNull
    private Long blogId;

    @NonNull
    private Long userId;
}
