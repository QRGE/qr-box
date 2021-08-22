package com.qr.blog.config.shiro;

import com.qr.blog.config.shiro.realm.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: QR
 * @Date: 2021/7/31-16:38
 */
@Configuration
public class ShiroConfig {

    @Resource
    private UserRealm userRealm;

    @Bean("securityManager")
    public SecurityManager securityManager(SessionDaoConfig sessionDaoConfig){
        // 注意这里是 new DefaultWebSecurityManager()
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        TokenWebSessionManager sessionManager = new TokenWebSessionManager();
        // 设置 securityManager
        securityManager.setRealm(userRealm);
        sessionManager.setSessionDAO(sessionDaoConfig);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        // 设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> shiroFilters = shiroFilterFactoryBean.getFilters();
        shiroFilters.put("authc", new LoginFilter());
        // 设置需要认证或者需要放行的接口
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 放行一些接口无需权限即可访问的接口, 放行的接口要放在 authc 前面
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/user/getQRInfo", "anon");
        filterMap.put("/user/save", "anon");
        filterMap.put("/user/getAdminInfo", "anon");
        filterMap.put("/link/list", "anon");
        filterMap.put("/tag/getHotTag", "anon");
        filterMap.put("/music/getAllEnable", "anon");
        filterMap.put("/blog/getByPage", "anon");
        filterMap.put("/blog/getInfo/**", "anon");
        filterMap.put("/blog/getTimeLine/**", "anon");
        filterMap.put("/type/list", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 加入注解的使用，不加入注解不生效
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
