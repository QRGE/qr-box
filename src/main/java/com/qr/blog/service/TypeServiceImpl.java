package com.qr.blog.service;

import com.qr.blog.mapper.BlogMapper;
import com.qr.blog.mapper.TypeMapper;
import com.qr.blog.pojo.Type;
import com.qr.blog.service.interfaces.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-15:42
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Resource
    private BlogMapper blogMapper;

    @Override
    public void save(Type type) {
        typeMapper.save(type);
    }

    @Override
    public Type getById(Long id) {
        return typeMapper.getById(id);
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void delete(Long id) {
        typeMapper.delete(id);
    }

    @Override
    public List<Type> getAllTypes() {
        List<Type> typeList = typeMapper.getAllTypes();
        // 查询 t_blog 表中存在的 type 的 blog 数量
        List<Type> countList = blogMapper.getTypeCount();
        // 设置所有 type 的 blog 数量
        typeList.forEach(e -> {
            Type type = countList.stream().filter(t -> t.getTypeId().equals(e.getTypeId())).findFirst().orElse(new Type());
            if (type.getTypeBlogCount() == null) {
                e.setTypeBlogCount(0);
            }else {
                e.setTypeBlogCount(type.getTypeBlogCount());
            }
        });
        return typeList;
    }
}
