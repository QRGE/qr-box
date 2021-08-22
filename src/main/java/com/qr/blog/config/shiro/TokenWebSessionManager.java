package com.qr.blog.config.shiro;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Author: QR
 * @Date: 2021/7/31-16:05
 */
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            return token;
        }
        return UUID.randomUUID().toString();
    }
}
