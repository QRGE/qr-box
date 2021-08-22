package com.qr.blog.mapper;

import com.qr.blog.pojo.BlogComment;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.vo.BlogCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/10-10:31
 */
@Mapper
public interface BlogCommentMapper {

    /**
     * 保存评论信息
     * @param blogComment 评论信息
     */
    void save(BlogComment blogComment);

    /**
     * 保存 blogId 查询评论
     * @param blogId blogId
     * @return 查询的评论集
     */
    List<BlogComment> getByBlogId(Long blogId);

    /**
     * 分页查询 blogComment
     * @param page page
     * @param userId userId
     * @return 查询结果
     */
    List<BlogComment> getByPage(@Param("page") Page<BlogCommentVo> page,@Param("userId") Long userId);

    /**
     * 查询分页结果总数
     * @param page page
     * @param userId userId
     * @return 查询结果
     */
    Integer getCountByPage(@Param("page") Page<BlogCommentVo> page, @Param("userId") Long userId);
}
