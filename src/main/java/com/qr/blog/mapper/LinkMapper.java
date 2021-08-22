package com.qr.blog.mapper;

import com.qr.blog.pojo.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-22:46
 */
@Mapper
public interface LinkMapper {

    /**
     * 保存友链 link
     * @param link link
     */
    void save(Link link);

    /**
     * 通过 id 获取 link
     * @param id id
     * @return  或得到的link
     */
    Link getById(Long id);

    /**
     * 修改 link
     * @param link link
     */
    void update(Link link);

    /**
     * 通过 id 删除 link
     * @param id id
     */
    void delete(Long id);

    /**
     * 获取所有 link
     * @return link集合
     */
    List<Link> getAll();
}
