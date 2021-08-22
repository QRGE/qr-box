package com.qr.blog.config.shiro;

import com.alibaba.fastjson.JSON;
import com.qr.blog.pojo.dto.Result;
import com.qr.blog.enums.ResultEnum;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Shiro对于登陆失效的默认处理方式为重定向到某个指定的页面, 如果没有配置, 默认重定向到 login.jsp
 * 而前后端分离的项目并没有 jsp或其他的template页面,
 *    选择重写 UserFilter 的 redirectToLogin() 当登陆错误时返回一个登陆失败状态
 * @Author: QR
 * @Date: 2021/7/31-16:09
 */
public class LoginFilter extends UserFilter {

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));
    }
}
