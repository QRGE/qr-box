package com.qr.blog.advice;

import com.qr.blog.exception.BlogException;
import com.qr.blog.pojo.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * - @ControllerAdvice
 * 全局异常处理
 * @Author: QR
 * @Date: 2021/7/30-16:55
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    /**
     * 统一处理 BlogException
     * @param exception 异常
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException exception) {
        log.error("统一异常处理：", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }
}
