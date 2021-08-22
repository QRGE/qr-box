package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.SysUser;
import com.qr.blog.pojo.dto.UpdatePwdDto;

/**
 * @Author: QR
 * @Date: 2021/7/31-17:17
 */
public interface SysUserService {

    /**
     * 根据 username 查询 account
     * @param username username
     * @return account
     */
    SysUser getByUsername(String username);

    /**
     * 根据传入的 sysUser 信息修改爷的信息
     * @param sysUser sysUser
     */
    void update(SysUser sysUser);

    /**
     * 修改爷的密码
     * @param updatePwdDto 旧密码 + 新密码
     */
    void updatePwd(UpdatePwdDto updatePwdDto);

    /**
     * 获取爷的信息
     * @return sysUser
     */
    SysUser getSysUser();
}
