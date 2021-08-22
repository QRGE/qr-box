package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/4-13:42
 */
@Data
public class Tag implements Serializable {

    /**
     * 分类编号
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;
}
