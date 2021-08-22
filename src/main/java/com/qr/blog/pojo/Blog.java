package com.qr.blog.pojo;

import lombok.Data;

/**
 * 目前设置一个博客只有一个大分类(type)和若干个小标签(tag)
 * @Author: QR
 * @Date: 2021/8/4-13:50
 */
@Data
public class Blog {

    /**
     * 博客Id, 利用雪花算法生成
     */
    private Long blogId;

    private String blogTitle;

    private String blogImage;

    private String blogContent;

    private Integer blogGoods;

    private Integer blogRead;

    private Integer blogCollection;

    private Long blogType;

    private String blogRemark;

    private Integer blogComment;

    private String blogSource;

    private String createdTime;

    private String updateTime;

    private Integer deleted;
}
