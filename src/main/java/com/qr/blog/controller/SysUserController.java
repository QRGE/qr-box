package com.qr.blog.controller;

import com.qr.blog.pojo.User;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.SysUser;
import com.qr.blog.pojo.dto.UpdatePwdDto;
import com.qr.blog.service.interfaces.SysUserService;
import com.qr.blog.util.ShiroUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author: QR
 * @Date: 2021/7/31-17:18
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 修改用户信息
     * @param sysUser 接受请求体中的传入的 sysUser
     * @return  修改成功状态
     */
    @PutMapping("/update")
    public Result<?> updateInfo(@RequestBody SysUser sysUser){
        sysUserService.update(sysUser);
        return new Result<>("修改信息成功");
    }

    @PutMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody UpdatePwdDto updatePwdDto){
        sysUserService.updatePwd(updatePwdDto);
        return new Result<>("修改密码成功");
    }

    /**
     * 获取管理员信息
     * @return 管理员信息对象
     */
    @GetMapping("/getSysUser")
    public Result<SysUser> getSysUser() {
        SysUser sysUser = sysUserService.getSysUser();
        return new Result<>(sysUser);
    }
}
