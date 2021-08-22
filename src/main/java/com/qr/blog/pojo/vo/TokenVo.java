package com.qr.blog.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * VO: View Object: 视图对象, 传输一部分内容
 * @Author: QR
 * @Date: 2021/7/31-17:20
 */
@Data
public class TokenVo implements Serializable {

    Serializable  token;

    public TokenVo(Serializable token) {
        this.token = token;
    }
}
