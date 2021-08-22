package com.qr.blog.mapper;

import com.qr.blog.pojo.Tag;
import com.qr.blog.pojo.vo.HotTagVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-13:43
 */
@Mapper
public interface TagMapper {

    /**
     * 根据名称搜索标签
     * @param name 输入的标签名
     * @return 搜索结果集
     */
    List<Tag> getByName(String name);

    /**
     * 批量创建 tag
     * @param tags 待创建的 tags
     */
    void saveBatch(List<Tag> tags);

    /**
     * 获取热门标签
     * @return 热门标签集合
     */
    List<HotTagVo> getHotTags();
}
