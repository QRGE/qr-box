package com.qr.blog.mapper;

import com.qr.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-15:33
 */
@Mapper
public interface TypeMapper {

    /**
     * 保存一个分类对象
     * @param type 分类对象
     */
    void save(Type type);

    /**
     * 查询 id 对应的对象
     * @param id 查询id
     * @return 对应的 type 对象
     */
    Type getById(Long id);

    /**
     * 根据传入的 type 修改对应的 type
     * @param type 传入的 type
     */
    void update(Type type);

    /**
     * 根据 id 删除对应的type
     * @param id id
     */
    void delete(Long id);

    /**
     * 查询所有的 type 并存进一个list中
     * @return type集合
     */
    List<Type> getAllTypes();

    /**
     * 根据分类的 id 集合查询所有分类
     * @param typeIds id集合
     * @return 查询的分类集合
     */
    List<Type> getByIds(List<Long> typeIds);
}
