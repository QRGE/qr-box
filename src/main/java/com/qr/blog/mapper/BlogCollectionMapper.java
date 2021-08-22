package com.qr.blog.mapper;

import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.BlogCollection;
import com.qr.blog.pojo.BlogGoods;
import com.qr.blog.pojo.dto.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/9-21:19
 */
@Mapper
public interface BlogCollectionMapper {

    /**
     * 存储收藏信息
     * @param blogCollection 收藏信息
     */
    void save(BlogCollection blogCollection);

    /**
     * 通过 blogId 和 userId 查询收藏数量
     * @param blogId blogId
     * @param userId userId
     * @return 收藏数量
     */
    Integer getCountByBlogIdAndUserId(@Param("blogId") Long blogId, @Param("userId") Long userId);

    /**
     * 通过 page 和  userId 分页查询
     * @param page page
     * @param userId userId
     * @return 查询的
     */
    List<BlogCollection> getByPage(@Param("page") Page<Blog> page, @Param("userId") Long userId);

    /**
     * 查询分页的总条数
     * @param page page
     * @param userId userId
     * @return 总条数
     */
    Integer getCountByPage(@Param("page") Page<Blog> page, @Param("userId") Long userId);
}
