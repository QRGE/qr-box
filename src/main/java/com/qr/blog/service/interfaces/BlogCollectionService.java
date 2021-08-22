package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.dto.Page;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:21
 */
public interface BlogCollectionService {

    /**
     * 通过 blogId 点赞
     * @param blogId 前台传入的blogId
     */
    void collection(Long blogId);

    /**
     * 查询某个博客的点赞数量
     * @param blogId 指定查询的博客 id
     * @return 点赞数量
     */
    Integer getCollection(Long blogId);

    /**
     * 根据 page 分页查询收藏博客
     * @param page page
     * @return 封装进 page 的查询博客集
     */
    Page<Blog> getByPage(Page<Blog> page);
}
