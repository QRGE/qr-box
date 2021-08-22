package com.qr.blog.service;

import com.qr.blog.mapper.MusicMapper;
import com.qr.blog.pojo.Music;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.service.interfaces.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/6-15:01
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private MusicMapper musicMapper;


    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    public void delete(Long id) {
        musicMapper.deleteById(id);
    }

    @Override
    public Music get(Long id) {
        return musicMapper.getById(id);
    }

    @Override
    public Page<Music> getByPage(Page<Music> page) {
        List<Music> musicList = musicMapper.getByPage(page);
        Integer totalCount = musicMapper.getCountByPage(page);
        page.setList(musicList);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void toggleEnable(Music music) {
        musicMapper.updateEnable(music);
    }

    @Override
    public List<Music> getAllEnable() {
        return musicMapper.getAllEnable();
    }
}
