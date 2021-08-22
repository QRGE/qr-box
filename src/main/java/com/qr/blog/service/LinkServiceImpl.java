package com.qr.blog.service;

import com.qr.blog.mapper.LinkMapper;
import com.qr.blog.pojo.Link;
import com.qr.blog.service.interfaces.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/2-22:50
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public void save(Link link) {
        linkMapper.save(link);
    }

    @Override
    public Link getById(Long id) {
        return linkMapper.getById(id);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    @Override
    public void deleteById(Long id) {
        linkMapper.delete(id);
    }

    @Override
    public List<Link> getAllLink() {
        return linkMapper.getAll();
    }
}
