package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/2-22:43
 */
@Data
public class Link implements Serializable {

    /**
     * 自增 ID
     */
    private Long linkId;

    /**
     * 友链名称
     */
    private String linkName;

    /**
     * 友链地址
     */
    private String linkUrl;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 是否删除
     */
    private Integer deleted;
}
