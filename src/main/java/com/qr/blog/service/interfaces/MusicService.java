package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.Music;
import com.qr.blog.pojo.dto.Page;

import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/6-15:00
 */
public interface MusicService {

    /**
     * 添加音乐
     * @param music music
     */
    void save(Music music);

    /**
     * 修改音乐信息
     * @param music 后台传入的 music 数据
     */
    void update(Music music);

    /**
     * 通过删除音乐
     * @param id 后台传入的id
     */
    void delete(Long id);

    /**
     * 通过 id 获取 music 对象
     * @param id id
     * @return music 对象
     */
    Music get(Long id);

    /**
     * 分页查询音乐
     * @param page page 对象
     * @return 查询的音乐
     */
    Page<Music> getByPage(Page<Music> page);

    /**
     * 设置音乐的启用状态
     * @param music 前台传入的 music 对象
     */
    void toggleEnable(Music music);

    /**
     * 查询所有启用的音乐
     * @return 启用的音乐集合
     */
    List<Music> getAllEnable();
}
