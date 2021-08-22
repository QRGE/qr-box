package com.qr.blog.mapper;

import com.qr.blog.pojo.BlogGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:19
 */
@Mapper
public interface BlogGoodsMapper {

    /**
     * 存储点赞信息
     * @param blogGoods 点赞信息
     */
    void save(BlogGoods blogGoods);

    /**
     * 通过 blogId 和 userId 查询点赞数量
     * @param blogId blogId
     * @param userId userId
     * @return 点赞数量
     */
    Integer getCountByBlogIdAndUserId(@Param("blogId") Long blogId, @Param("userId") Long userId);
}
