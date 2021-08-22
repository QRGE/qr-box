package com.qr.blog.util;

import org.junit.Test;

/**
 * @Author: QR
 * @Date: 2021/8/9-15:35
 */
public class StringUtilsTest {

    @Test
    public void isBlankTest() {
        System.out.println(StringUtils.isBlank("你好哇 你好哇"));
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.isBlank(" 你好哇"));
    }
}
