package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/7/31-16:58
 */
@Data
public class SysUser implements Serializable {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String header;

    /**
     * 签名
     */
    private String signature;

    /**
     * 介绍
     */
    private String comment;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
