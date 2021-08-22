package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/2-15:30
 */
@Data
public class Type implements Serializable {

    /**
     * 分类id
     */
    private Long typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 该分类的博客数量
     */
    private Integer typeBlogCount;

    /**
     * 是否启用, 1为启用, 0为不启用
     */
    private Integer enable;

    /**
     * 是否删除, 1为删除, 0为不删除
     */
    private Integer deleted;
}
