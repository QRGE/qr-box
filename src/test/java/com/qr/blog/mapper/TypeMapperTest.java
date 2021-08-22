package com.qr.blog.mapper;

import com.qr.blog.BlogApplication;
import com.qr.blog.pojo.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * TypeMapper测试
 * @Author: QR
 * @Date: 2021/8/2-16:47
 */
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TypeMapperTest {

    @Resource
    private TypeMapper typeMapper;

    @Test
    public void saveTest(){
        Type type = new Type();
        type.setTypeName("Java");
        typeMapper.save(type);
    }

    @Test
    public void getALlTypesTest(){
        List<Type> types = typeMapper.getAllTypes();
        types.forEach(System.out::println);
    }

    @Test
    public void getByIdTest(){
        Type type = typeMapper.getById(1L);
        System.out.println(type);
    }

    @Test
    public void deleteTest(){
        typeMapper.delete(8L);
    }

    @Test
    public void updateTest(){
        Type type = new Type();
        type.setTypeId(8L);
        type.setTypeName("VB");
        typeMapper.update(type);
    }
}
