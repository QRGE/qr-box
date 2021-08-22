package com.qr.blog.service;

import com.qr.blog.exception.BlogException;
import com.qr.blog.mapper.SysUserMapper;
import com.qr.blog.pojo.SysUser;
import com.qr.blog.pojo.dto.UpdatePwdDto;
import com.qr.blog.service.interfaces.SysUserService;
import com.qr.blog.util.ShiroUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QR
 * @Date: 2021/7/31-17:17
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.getByUsername(username);
    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.update(sysUser);
        // 更新 shiro 存储的信息
        ShiroUtils.setUser(sysUser);
    }

    @Override
    public void updatePwd(UpdatePwdDto updatePwdDto) {
        SysUser sysUser = sysUserMapper.getUserById(updatePwdDto.getId());
        // 比较输入的当前密码和数据库中的记录
        if (!updatePwdDto.getCurrentPwd().equals(sysUser.getPassword())){
            throw new BlogException("输入的当前密码不正确");
        }
        sysUserMapper.updatePwd(updatePwdDto);
    }

    @Override
    public SysUser getSysUser() {
        return sysUserMapper.getSysUser();
    }
}
