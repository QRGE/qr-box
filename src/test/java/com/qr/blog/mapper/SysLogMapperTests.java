package com.qr.blog.mapper;

import com.qr.blog.BlogApplication;
import com.qr.blog.pojo.SysLog;
import com.qr.blog.pojo.dto.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/3-13:54
 */
@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
public class SysLogMapperTests {

    @Resource
    private SysLogMapper logMapper;

    @Test
    public void getByPage(){
        Page<SysLog> page = new Page<>();
        page.setPageNumber(1);
        page.setPageSize(20);
        List<SysLog> sysLogs = logMapper.getByPage(page);
        System.out.println(sysLogs);
        System.out.println(sysLogs.size());
    }

    @Test
    public void countByPage(){
        Page<SysLog> page = new Page<>();
        page.setPageNumber(1);
        page.setPageSize(20);
        System.out.println(logMapper.countByPage(page));

    }

    @Test
    public void getByIdTest(){
        System.out.println(logMapper.getById(1421807434644066304L));
    }
}
