package com.qr.blog.pojo.vo;

import lombok.Data;
import lombok.NonNull;

/**
 * @Author: QR
 * @Date: 2021/8/8-20:37
 */
@Data
public class HotTagVo {

    @NonNull
    private Long tagCount;
    @NonNull
    private String tagName;
}
