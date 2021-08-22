package com.qr.blog.mapper;

import com.qr.blog.pojo.SysLog;
import com.qr.blog.pojo.dto.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/7/30-17:13
 */
@Mapper
public interface SysLogMapper {

    /**
     * 保存日志
     * @param log log
     */
    void save(SysLog log);

    /**
     * 分页查询日志
     * @param page 分页对象
     * @return 查询的日志集合
     */
    List<SysLog> getByPage(Page<SysLog> page);

    /**
     * 查询记录的总页数
     * @param page  分页对象
     * @return 总页数
     */
    Integer countByPage(Page<SysLog> page);

    /**
     * 根据 id 查询日志记录
     * @param id id
     * @return 日志记录
     */
    SysLog getById(Long id);

    /**
     * 根据 id 删除对应日志
     * @param id id
     */
    void deleteById(Long id);
}
