package com.qr.blog.aop;

import com.alibaba.fastjson.JSON;
import com.qr.blog.annotation.LogIgnore;
import com.qr.blog.context.SystemContext;
import com.qr.blog.enums.StateEnums;
import com.qr.blog.pojo.SysLog;
import com.qr.blog.service.interfaces.SysLogService;
import com.qr.blog.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: QR
 * @Date: 2021/7/30-18:43
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Resource
    private SysLogService sysLogService;

    /**
     * 声明切点
     */
    @Pointcut("execution( * com.qr.blog.controller..*(..))")
    public void logPointCut() {
    }

    /**
     * 环绕通知
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        // 获取request
        HttpServletRequest request = attributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // 忽略掉一些不必显示的日志, 例如再注解页面中显示日志的操作即在日志中操作日志记录等
        boolean logIgnore = method.isAnnotationPresent(LogIgnore.class);
        SysLog sysLog = SystemContext.get().getLogger();
        // 需要记录的日志
        if (!logIgnore){
            // 获取URI
            String uri = request.getRequestURI();
            log.info("请求地址: {}", uri);
            // 获取请求方式
            String requestMethod = request.getMethod();
            log.info("请求方式: {}", requestMethod);
            // 获取请求IP
            String ip = StringUtils.getRemoteIp(request);
            log.info("请求IP: {}", ip);
            // 获取请求的controller
            String controller = methodSignature.getDeclaringTypeName();
            log.info("请求方法: {}.{}", controller, methodSignature.getName());
            // 获取参数
            Object[] args = pjp.getArgs();
            // 参数不为空，且第一个参数不是Request也不是MultipartFile
            boolean logParamFlag = args != null && args.length > 0 && !(args[0] instanceof ServletRequest) && !(args[0] instanceof MultipartFile);
            // 获取线程上下文
            if(sysLog == null) {
                sysLog = new SysLog();
                SystemContext.get().setLogger(sysLog);
            }
            if (logParamFlag) {
                String params = JSON.toJSONString(args[0]);
                log.info("请求参数: {}", params);
                sysLog.setLogParams(params);
            }
            // 设置记录日志对象
            sysLog.setLogUrl(uri);
            sysLog.setLogStatus(StateEnums.REQUEST_SUCCESS.getCode());
            sysLog.setLogMethod(requestMethod);
            sysLog.setLogIp(ip);
            sysLog.setLogUa(request.getHeader("user-Agent"));
            sysLog.setLogController(controller);
        }
        // 记录方法执行时间
        long start = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        if (!logIgnore){
            log.info("方法执行耗时: {}ms", time);
            sysLog.setLogTime(time);
            // 记录返回值
            String result = JSON.toJSONString(ob);
            log.info("返回值: {}", result);
            sysLog.setLogResult(result);

            // 记录日志到数据库
            sysLogService.save(sysLog);
        }
        SystemContext.get().remove();
        return ob;
    }

    /**
     * 异常通知
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "throwable")
    public void doException(JoinPoint joinPoint, Throwable throwable) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(LogIgnore.class)) {
            SysLog sysLog = SystemContext.get().getLogger();
            sysLog.setLogStatus(StateEnums.REQUEST_ERROR.getCode());
            sysLog.setLogMessage(throwable.getMessage());
            // 错误时间
            sysLog.setLogTime(-1L);
            sysLogService.save(sysLog);
            SystemContext.get().remove();
        }
    }
}
