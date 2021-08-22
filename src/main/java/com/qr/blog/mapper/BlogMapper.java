package com.qr.blog.mapper;

import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.Type;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.vo.BlogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-13:54
 */
@Mapper
public interface BlogMapper {

    /**
     * 保存博客
     * @param blog blog对象
     */
    void save(Blog blog);

    /**
     * 更新博客内容
     * @param blog 后台传入的博客内容
     */
    void update(Blog blog);

    /**
     * 通过 id 删除博客
     * @param id 后台传入的 id
     */
    void deleteById(Long id);

    /**
     * 分页查询 Blog
     * @param page 分页对象
     * @return 查询的 blog 集
     */
    List<Blog> getByPage(Page<BlogVo> page);

    /***
     * 分页查询的 Blog 的数量
     * @param page 分页对象
     * @return Blog 数量
     */
    Integer countByPage(Page<BlogVo> page);

    /**
     * 通过 id 查询修改的博客的回显数据
     * @param id id
     * @return 查询的 Blog
     */
    Blog getUpdate(Long id);

    /**
     * 通过 id 查询 Blog
     * @param id id
     * @return Blog
     */
    Blog getInfo(Long id);

    /**
     * 查询 bl_blog 表中存在的分类的博客数量
     * @return 每个分类的博客数量
     */
    List<Type> getTypeCount();

    /**
     * 获取时间轴对象
     * @param index 当前博客数量
     * @return 时间轴对象
     */
    List<Blog> getTimeLine(Integer index);

    List<Blog> getByIds(List<Long> blogIds);
}
