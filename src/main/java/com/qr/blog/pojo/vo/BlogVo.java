package com.qr.blog.pojo.vo;

import com.qr.blog.pojo.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-16:51
 */
@Data
public class BlogVo implements Serializable {

    private long blogId;

    private String blogTitle;

    private String blogImage;

    private Integer blogGoods;

    private Integer blogRead;

    private String blogContent;

    private Integer blogCollection;

    private Long blogType;

    private String blogRemark;

    private Integer blogComment;

    private String blogSource;

    private String createdTime;

    private String updateTime;

    /**
     * blog 的所有 tag
     */
    private List<String> tagsName;

    /**
     * blog 的分类名称
     */
    private String typeName;
}
