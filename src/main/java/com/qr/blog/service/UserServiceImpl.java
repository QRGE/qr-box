package com.qr.blog.service;

import com.qr.blog.mapper.UserMapper;
import com.qr.blog.pojo.User;
import com.qr.blog.pojo.dto.SysUserInfoDto;
import com.qr.blog.service.interfaces.UserService;
import com.qr.blog.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/8/9-15:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, SysUserInfoDto> redisTemplate;

    @Override
    public void save(User user) {
        if (StringUtils.isBlank(user.getHeader())){
            user.setHeader("https://gitee.com/qrlearning/image/raw/master/img/Umy.jpg");
        }
        userMapper.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void updatePwd(User user) {
        userMapper.updatePwd(user);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }

    @Override
    public SysUserInfoDto getSysUser() {
        SysUserInfoDto userInfo = redisTemplate.opsForValue().get("userInfo");
        if (userInfo == null) {
            // 从数据库中查找管理员信息
            SysUserInfoDto info = new SysUserInfoDto();
            User sysUser = userMapper.getSysUser();
            BeanUtils.copyProperties(sysUser, info);
            // 将管理员信息写入 redis 中保存
            redisTemplate.opsForValue().set("userInfo", info);
            return info;
        } else {
            return userInfo;
        }
    }

    @Override
    public User getAdminInfo() {
        // 由于目前还是个人网站只有一个管理员, 先固定为 admin
        return userMapper.getAdminByUsername("admin");
    }
}
