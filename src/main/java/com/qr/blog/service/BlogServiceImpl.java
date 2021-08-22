package com.qr.blog.service;

import com.qr.blog.exception.BlogException;
import com.qr.blog.mapper.BlogMapper;
import com.qr.blog.mapper.BlogTagMapper;
import com.qr.blog.mapper.TagMapper;
import com.qr.blog.mapper.TypeMapper;
import com.qr.blog.pojo.Blog;
import com.qr.blog.pojo.BlogTag;
import com.qr.blog.pojo.Tag;
import com.qr.blog.pojo.Type;
import com.qr.blog.pojo.dto.BlogDto;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.vo.BlogVo;
import com.qr.blog.service.interfaces.BlogService;
import com.qr.blog.util.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: QR
 * @Date: 2021/8/4-14:00
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private IdWorker idWorker;

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private TagMapper tagMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private TypeMapper typeMapper;

    /**
     * 查询允许的排列字段
     */
    private static final String[] SORT_COLUMNS = {"created_time", "blog_goods", "blog_read", "blog_collection", "blog_comment"};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BlogDto blogDto) {
        long blogId = idWorker.nextId();
        blogDto.setBlogId(blogId);
        Blog blog = new Blog();
        // 复制数据
        BeanUtils.copyProperties(blogDto, blog);
        blog.setBlogId(blogId);
        blogMapper.save(blog);
        // 处理标签
        saveTag(blogDto, blogId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BlogDto blogDto) {
        Blog blog = new Blog();
        // 修改 bl_blog 表
        BeanUtils.copyProperties(blogDto, blog);
        blogMapper.update(blog);
        // 先删除 blog 原来的 tag 记录在处理标签
        blogTagMapper.deleteByBlogId(blog.getBlogId());
        // 处理标签
        saveTag(blogDto, blog.getBlogId());
    }

    @Override
    public void deleteById(Long id) {
        blogMapper.deleteById(id);
    }

    @Override
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        // 校验排序方式防止 sql 注入
        String sortColumn = page.getSortColumn();
        if (!Arrays.asList(SORT_COLUMNS).contains(sortColumn)){
            throw new BlogException("非法的排序列!");
        }
        // 按照分页信息查询 blog 数据集合
        List<Blog> blogs = blogMapper.getByPage(page);
        Integer count = blogMapper.countByPage(page);
        page.setTotalCount(count);
        // 数据为 0 时直接返回
        if (count == null || count == 0) {
            page.setList(new ArrayList<>(0));
            return page;
        }
        // 查询 page 中所有 blog 的分类
        List<Long> typeIds = blogs.stream().map(Blog::getBlogType).collect(Collectors.toList());
        List<Type> types = typeMapper.getByIds(typeIds);
        // 查询 page 中所有 blog 的所有标签
        List<Long> blogIds = blogs.stream().map(Blog::getBlogId).collect(Collectors.toList());
        List<BlogTag> blogTags = blogTagMapper.getByBlogIds(blogIds);
        // 封装分类和标签
        List<BlogVo> blogVos = blogs.stream().map(e -> {
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e, blogVo);
            return blogVo;
        }).peek(e ->{
            // 根据分类 id 取值分类列表中对应的值
            Type type = types.stream()
                             .filter(t -> t.getTypeId().equals(e.getBlogType()))
                             .findFirst()
                             .orElse(new Type());
            e.setTypeName(type.getTypeName());
        }).peek(e -> {
            // 根据 blogId 获取其所有 tagName
            List<String> tagNames = blogTags.stream()
                                            .filter(t -> t.getBlogId().equals(e.getBlogId()))
                                            .map(BlogTag::getTagName)
                                            .collect(Collectors.toList());
            e.setTagsName(tagNames);
        }).collect(Collectors.toList());
        page.setList(blogVos);
        return page;
    }

    @Override
    public Blog getUpdate(Long id) {
        return blogMapper.getUpdate(id);
    }

    @Override
    public BlogVo getInfo(Long id) {
        Blog blog = blogMapper.getInfo(id);
        // 生成 blogVo
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        Type type = typeMapper.getById(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    @Override
    public List<Blog> getTimeLine(Integer index) {
        return blogMapper.getTimeLine(index);
    }

    private void saveTag(BlogDto blogDto, long blogId) {
        List<Tag> tags = blogDto.getTags();
        if (!CollectionUtils.isEmpty(tags)) {
            // tag_id == null 的标签为新添加的标签, 需要存入 bl_tag 表中
            List<Tag> saveTags = tags.stream().filter(e -> e.getId() == null).collect(Collectors.toList());
            tagMapper.saveBatch(saveTags);
            // 添加新的 tag 记录, 即存 bl_blog_tag 表
            List<BlogTag> blogTags = tags.stream().map(e -> {
                BlogTag blogTag = new BlogTag();
                blogTag.setId(idWorker.nextId());
                blogTag.setBlogId(blogId);
                blogTag.setTagId(e.getId());
                blogTag.setTagName(e.getName());
                return blogTag;
            }).collect(Collectors.toList());
            blogTagMapper.saveBatch(blogTags);
        }
    }
}
