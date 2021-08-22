package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/9-15:02
 */
@Data
public class User implements Serializable {

    /**
     * 自增 id
     */
    private Long userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 性别: 1男0女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String header;

    /**
     * 昵称/网名
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 是否删除, 1是0否
     */
    private Integer deleted;

    /**
     * 签名
     */
    private String signature;

    /**
     * 自我简介
     */
    private String comment;

    /**
     * 角色: 1为管理员, 0为普通用户
     */
    private Integer role;

}
