package com.qr.blog.service.interfaces;

import com.qr.blog.pojo.User;
import com.qr.blog.pojo.dto.SysUserInfoDto;

/**
 * @Author: QR
 * @Date: 2021/8/9-15:20
 */
public interface UserService {

    /**
     * 保存注册用户
     * @param user 注册传入的 user 对象
     */
    void save(User user);

    /**
     * 通过 username 查找对应的 user 对象
     * @param username 指定的 username
     * @return 查找到的 user 对象
     */
    User getUserByUsername(String username);

    /**
     * 修改用户密码
     * @param user user
     */
    void updatePwd(User user);

    /**
     * 修改当前用户信息
     * @param user user
     */
    void updateInfo(User user);

    /**
     * 查询管理员的相关信息
     * @return 传递管理员信息的dto
     */
    SysUserInfoDto getSysUser();

    /**
     * 获取管理员对象
     * @return 管理员对象
     */
    User getAdminInfo();
}
