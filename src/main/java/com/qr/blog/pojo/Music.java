package com.qr.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/6-14:52
 */
@Data
public class Music implements Serializable {

    /**
     * 自增 id
     */
    private Long id;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 歌手
     */
    private String artist;

    /**
     * 歌曲链接
     */
    private String url;

    /**
     * 封面
     */
    private String cover;

    /**
     * 歌词
     */
    private String lrc;

    /**
     * 是否启用
     */
    private String createdTime;

    /**
     * 启用
     */
    private Integer enable;

    /**
     * 删除
     */
    private Integer deleted;
}
