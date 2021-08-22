package com.qr.blog.service;

import com.qr.blog.mapper.SysLogMapper;
import com.qr.blog.pojo.SysLog;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.service.interfaces.SysLogService;
import com.qr.blog.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/7/30-17:27
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Resource
    private IdWorker idWorker;

    @Override
    public void save(SysLog sysLog) {
        sysLog.setLogId(idWorker.nextId());
        sysLog.setCreatedBy("admin");
        sysLogMapper.save(sysLog);
    }

    @Override
    public Page<SysLog> getByPage(Page<SysLog> page) {
        List<SysLog> logs = sysLogMapper.getByPage(page);
        Integer totalCount = sysLogMapper.countByPage(page);
        page.setList(logs);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public SysLog getById(Long id) {
        return sysLogMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        sysLogMapper.deleteById(id);
    }
}
