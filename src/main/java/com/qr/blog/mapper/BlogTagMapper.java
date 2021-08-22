package com.qr.blog.mapper;

import com.qr.blog.pojo.BlogTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-14:21
 */
@Mapper
public interface BlogTagMapper {

    /**
     * 批量保存 blogTag
     * @param blogTags blogTag数据集
     */
    void saveBatch(List<BlogTag> blogTags);

    /**
     * 根据 blogId 删除对应的 blog 的所有 tag 记录
     * @param blogId blogId
     */
    void deleteByBlogId(long blogId);

    /**
     * 根据 blogIds 查询所有 blogTag
     * @param blogIds blogIds
     * @return 查询的 blogTag 集合
     */
    List<BlogTag> getByBlogIds(List<Long> blogIds);

    /**
     * 根据 blogId 查询某 blog 的所有 tag
     * @param blogId blogId
     * @return 查询的tag集合
     */
    List<BlogTag> getByBlogId(Long blogId);
}
