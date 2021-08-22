package com.qr.blog.service;

import com.qr.blog.mapper.BlogTagMapper;
import com.qr.blog.mapper.TagMapper;
import com.qr.blog.pojo.BlogTag;
import com.qr.blog.pojo.Tag;
import com.qr.blog.pojo.vo.HotTagVo;
import com.qr.blog.service.interfaces.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: QR
 * @Date: 2021/8/4-13:48
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Override
    public List<Tag> getByName(String name) {
        return tagMapper.getByName(name);
    }

    @Override
    public List<Tag> getByBlogId(Long blogId) {
        List<BlogTag> blogTags = blogTagMapper.getByBlogId(blogId);
        return blogTags.stream().map(e -> {
            Tag tag = new Tag();
            tag.setId(e.getTagId());
            tag.setName(e.getTagName());
            return tag;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HotTagVo> getHotTags() {
        return tagMapper.getHotTags();
    }
}
