package com.qr.blog.mapper;

import com.qr.blog.pojo.SysUser;
import com.qr.blog.pojo.dto.UpdatePwdDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QR
 * @Date: 2021/7/31-17:14
 */
@Mapper
public interface SysUserMapper {

    /**
     * 通过 username 查询 sysUser 信息
     * @param username username
     * @return sysUser
     */
    SysUser getByUsername(String username);

    /**
     * 修改爷的信息
     * @param sysUser sysUser
     */
    void update(SysUser sysUser);

    /**
     * 修改爷的密码
     * @param updatePwdDto sysUser
     */
    void updatePwd(UpdatePwdDto updatePwdDto);

    /**
     * 根据 id 查询账号信息
     * @param id id
     * @return 账号信息
     */
    SysUser getUserById(Long id);

    /**
     * 获取爷的信息
     * @return sysUser
     */
    SysUser getSysUser();
}
