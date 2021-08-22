package com.qr.blog.service.interfaces;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:21
 */
public interface BlogGoodsService {

    /**
     * 通过 blogId 点赞
     * @param blogId 前台传入的blogId
     */
    void goods(Long blogId);

    /**
     * 查询某个博客的点赞数量
     * @param blogId 指定查询的博客 id
     * @return 点赞数量
     */
    Integer getGoods(Long blogId);
}
