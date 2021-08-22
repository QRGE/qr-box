package com.qr.blog.config.shiro.realm;

import com.qr.blog.enums.ResultEnum;
import com.qr.blog.exception.BlogException;
import com.qr.blog.pojo.User;
import com.qr.blog.service.interfaces.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: QR
 * @Date: 2021/7/31-16:37
 */
@Component("sysUserRealm")
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取登陆账号
        String username = (String) authenticationToken.getPrincipal();
        // 通过账号获取对应的用户信息, 如果用户信息不存在则抛出异常, 通过则设置认证信息
        User user = userService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new BlogException(ResultEnum.LOGIN_PARAM_ERROR);
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
    }
}
