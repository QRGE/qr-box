package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.Link;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-22:49
 */
public interface LinkService {

    /**
     * 保存友链
     * @param link link
     */
    void save(Link link);

    /**
     * 通过 id 获取 link
     * @param id id
     * @return 查询到的 link
     */
    Link getById(Long id);

    /**
     * 修改link
     * @param link link
     */
    void update(Link link);

    /**
     * 通过 id 删除 link
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 获取所有 link
     * @return link集合
     */
    List<Link> getAllLink();
}
