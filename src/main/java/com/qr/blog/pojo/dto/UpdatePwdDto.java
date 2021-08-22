package com.qr.blog.pojo.dto;

import lombok.Data;

/**
 * @Author: QR
 * @Date: 2021/8/2-20:38
 */
@Data
public class UpdatePwdDto {

    private Long id;
    private String currentPwd;
    private String newPwd;
}
