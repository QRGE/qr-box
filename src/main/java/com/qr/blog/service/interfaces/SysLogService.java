package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.SysLog;
import com.qr.blog.pojo.dto.Page;
import org.springframework.stereotype.Service;

/**
 * @Author: QR
 * @Date: 2021/7/30-17:25
 */
public interface SysLogService {

    /**
     * 保存操作日志
     * @param sysLog sysLog
     */
    void save(SysLog sysLog);

    /**
     * 分页查询日志记录
     * @param page 分页对象
     * @return 日志记录
     */
    Page<SysLog> getByPage(Page<SysLog> page);

    /**
     * 根据 id 查询对应的日志记录
     * @param id id
     * @return 日志记录
     */
    SysLog getById(Long id);

    /**
     * 根据 id 删除对应的日志
     * @param id id
     */
    void deleteById(Long id);
}
