package com.qr.blog.controller;

import com.qr.blog.pojo.Music;
import com.qr.blog.pojo.dto.Page;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.service.interfaces.MusicService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QR
 * @Date: 2021/8/6-15:27
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    private MusicService musicService;

    @Resource
    private RedisTemplate<String, List<Music>> redisTemplate;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Music music){
        musicService.save(music);
        return new Result<>("成功添加音乐: " + music.getName());
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Music music) {
        musicService.update(music);
        return new Result<>("修改音乐信息成功!");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id){
        musicService.delete(id);
        return new Result<>("成功删除该首音乐!");
    }

    @GetMapping("/get/{id}")
    public Result<Music> getById(@PathVariable Long id){
        Music music = musicService.get(id);
        return new Result<>(music);
    }

    @PostMapping("/getByPage")
    public Result<Page<Music>> getByPage(@RequestBody Page<Music> page){
        page = musicService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 设置音乐的启用状态
     * @return 设置结果
     */
    @PutMapping("/toggleEnable")
    public Result<?> toggleEnable(@RequestBody Music music){
        musicService.toggleEnable(music);
        return Result.ok();
    }

    /**
     * 查询启用的音乐
     * @return 查询的音乐集合
     */
    @SuppressWarnings("all")
    @GetMapping("/getAllEnableMusic")
    public Result<List<Music>> getAllEnable(){
        if (Boolean.TRUE.equals(redisTemplate.hasKey("music"))){
            List<Music> musicList = redisTemplate.opsForValue().get("music");
            return new Result<>(musicList);
        }else {
            List<Music> musicList = musicService.getAllEnable();
            redisTemplate.opsForValue().set("music", musicList);
            return new Result<>(musicList);
        }
    }
}
