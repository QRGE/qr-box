package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.dto.BlogDto;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.vo.BlogVo;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/4-14:00
 */
public interface BlogService {

    /**
     * 添加博客
     * @param blogDto 博客内容
     */
    void save(BlogDto blogDto);

    /**
     * 修改博客
     * @param blogDto 后台传入的博客内容
     */
    void update(BlogDto blogDto);

    /**
     * 通过 id 删除对应的博客
     * @param id 后台传入的 id
     */
    void deleteById(Long id);

    /**
     * 分页查询博客内容
     * @param page 分页对象 page
     * @return 查询结果
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 通过 id 查询用于修改的 blog 数据
     * @param id id
     * @return 查询的 Blog
     */
    Blog getUpdate(Long id);

    /**
     * 通过 id 查询后于后台显示的博客详情数据
     * @param id id
     * @return 查询结果
     */
    BlogVo getInfo(Long id);

    /**
     * 获取时间轴, 即通过时间排序的 blog 对象集
     * @param index 当前博客数量
     * @return 时间轴对象
     */
    List<Blog> getTimeLine(Integer index);
}
