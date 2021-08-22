package com.qr.blog.controller;

import com.qr.blog.enums.ResultEnum;
import com.qr.blog.enums.UserTypeEnum;
import com.qr.blog.pojo.User;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.pojo.dto.SysUserInfoDto;
import com.qr.blog.pojo.vo.TokenVo;
import com.qr.blog.service.interfaces.UserService;
import com.qr.blog.config.shiro.LoginToken;
import com.qr.blog.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Author: QR
 * @Date: 2021/8/9-15:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册普通用户
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        // 检验是否已经存在 username 相同的账户
        if (userService.getUserByUsername(user.getUsername()) != null) {
            return new Result<>("该用户名已经被注册 (。_。)...");
        }else { // 注册账户
            userService.save(user);
            return new Result<>("注册成功! (≧∇≦)ﾉ");
        }
    }

    /**
     * 普通用户登陆
     * @return 登陆后返回token
     */
    @SuppressWarnings("all")
    @PostMapping("/login")
    public Result<TokenVo> login(@RequestBody User user){
        // 参数校验
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return new Result<>(ResultEnum.PARAMS_NULL);
        }
        // 利用 shiro 进行登陆校验
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken loginToken = new LoginToken(user.getUsername(), user.getPassword(), UserTypeEnum.USER);
        try {
            subject.login(loginToken);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.LOGIN_PARAM_ERROR);
        }
        Serializable token = subject.getSession().getId();
        return new Result<>(new TokenVo(token));
    }

    @GetMapping("/logout")
    public Result<?> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result<>("退出登陆!");
    }


    /**
     * 修改用户密码
     * @param user 前台传入的 user 对象
     * @return 修改结果
     */
    @PutMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody User user){
        userService.updatePwd(user);
        return new Result<>("修改密码成功!");
    }


    /**
     * 获取 QR 个人用户前台展示的信息
     * @return 管理员相关字段信息 + 成功状态
     */
    @GetMapping("/getQRInfo")
    public Result<SysUserInfoDto> getSysUser() {
        SysUserInfoDto info = userService.getSysUser();
        return new Result<>(info);
    }

    /**
     * 获取前台当前登陆用户的信息
     * @return 登陆用户对象 + 成功状态
     */
    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        User user = userService.getUserByUsername(username);
        return new Result<>(user);
    }

    /**
     * 获取管理员的所有信息, 后于后台的展示
     * @return 管理员对象
     */
    @GetMapping("/getAdminInfo")
    public Result<User> getAdminInfo() {
        User user = userService.getAdminInfo();
        return new Result<>(user);
    }

    /**
     * 修改当前用户信息
     * @param user user
     * @return 修改结果
     */
    @PutMapping("/updateInfo")
    public Result<?> updateInfo(@RequestBody User user) {
        userService.updateInfo(user);
        User loginUser = new User();
        BeanUtils.copyProperties(user, loginUser);
        loginUser.setUserId(user.getUserId());
        // 动态更新当前登陆用户
        ShiroUtils.setUser(loginUser);
        return new Result<>("修改用户信息成功!");
    }
}
