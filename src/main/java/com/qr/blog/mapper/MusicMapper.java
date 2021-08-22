package com.qr.blog.mapper;

import com.qr.blog.pojo.Music;
import com.qr.blog.pojo.dto.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/6-14:57
 */
@Mapper
public interface MusicMapper {

    /**
     * 保存 music 对象到数据库
     * @param music music
     */
    void save(Music music);

    /**
     * 修改对应的音乐信息
     * @param music 后台传入的 music 数据
     */
    void update(Music music);

    /**
     * 通过 id 删除音乐
     * @param id 音乐 id
     */
    void deleteById(Long id);

    /**
     * 通过 id 获取 music 对象
     * @param id id
     * @return music
     */
    Music getById(Long id);

    /**
     * 分页查询 page 对象
     * @param page page
     * @return 查询的 music 对象集
     */
    List<Music> getByPage(Page<Music> page);

    /**
     * 通过 page 对象查询当前页 music 的总条数
     * @param page page
     * @return 总条数
     */
    Integer getCountByPage(Page<Music> page);

    /**
     * 修改 music 的启用状态
     * @param music 前台传入的音乐启用状态
     */
    void updateEnable(Music music);

    /**
     * 查询启用音乐
     * @return 启用音乐集合
     */
    List<Music> getAllEnable();
}
