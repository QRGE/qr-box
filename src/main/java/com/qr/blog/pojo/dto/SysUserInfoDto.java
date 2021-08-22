package com.qr.blog.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/19-15:14
 */
@Data
public class SysUserInfoDto implements Serializable {

    private Long userId;
    private String name;
    private String header;
    private String signature;
    private String comment;
}
