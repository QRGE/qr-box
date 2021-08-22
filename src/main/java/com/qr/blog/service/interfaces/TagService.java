package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.Tag;
import com.qr.blog.pojo.vo.HotTagVo;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-13:48
 */
public interface TagService {

    /**
     * 根据名称搜索分类
     * @param name 搜索的分类名称
     * @return 搜索结果集
     */
    List<Tag> getByName(String name);

    /**
     * 根据 blogId 查询 tags
     * @param blogId blogId
     * @return 查询的 tags
     */
    List<Tag> getByBlogId(Long blogId);

    /**
     * 获取热门标签
     * @return 热门标签集合
     */
    List<HotTagVo> getHotTags();
}
