package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.Type;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-15:42
 */
public interface TypeService {

    /**
     * 保存一个分类对象
     * @param type type
     */
    void save(Type type);

    /**
     * 根据 id 查询返回对应的Type
     * @param id 查询id
     * @return 查询结果
     */
    Type getById(Long id);

    /**
     * 根据传入的 type 修改对应 type 信息
     * @param type 传入的type
     */
    void update(Type type);

    /**
     * 根据 id 删除对应的type
     * @param id id
     */
    void delete(Long id);

    /**
     * 查询所有的 type 并封装进 一个list
     * @return type集合
     */
    List<Type> getAllTypes();
}
