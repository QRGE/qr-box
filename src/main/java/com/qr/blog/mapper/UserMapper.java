package com.qr.blog.mapper;

import com.qr.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QR
 * @Date: 2021/8/9-15:08
 */
@Mapper
public interface UserMapper {

    /**
     * 添加新的 user 对象到数据库
     * @param user user
     */
    void save(User user);

    /**
     * 通过 username 获取对应的 user 对象
     * @param username 指定的 username
     * @return 查询到的 user 对象
     */
    User getUserByUsername(String username);

    /**
     * 修改当前用户密码
     * @param user user
     */
    void updatePwd(User user);

    /**
     * 修改当前用户信息
     * @param user user
     */
    void updateInfo(User user);

    /**
     * 获取管理员用户
     * @return 管理员用户对象
     */
    User getSysUser();

    /**
     * 根据 username 获取管理员对象
     * @param username username
     * @return 管理员对象
     */
    User getAdminByUsername(String username);
}
