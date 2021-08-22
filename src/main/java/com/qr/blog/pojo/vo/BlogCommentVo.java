package com.qr.blog.pojo.vo;

import com.qr.blog.pojo.Blog;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/10-14:38
 */
@Data
public class BlogCommentVo implements Serializable {

    /**
     * blog
     */
    private Blog blog;

    /**
     * 评论内容
     */
    private String content;
}
