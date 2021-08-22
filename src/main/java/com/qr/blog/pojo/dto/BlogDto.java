package com.qr.blog.pojo.dto;

import com.qr.blog.pojo.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-14:41
 */
@Data
public class BlogDto implements Serializable {

    private Long blogId;
    private String blogTitle;
    private String blogImage;
    private String blogContent;
    private Long blogType;
    private String blogRemark;
    private String blogSource;

    /**
     * 接收传入的批量标签
     */
    private List<Tag> tags;
}
