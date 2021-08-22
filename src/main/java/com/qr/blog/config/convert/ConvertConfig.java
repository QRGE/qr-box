package com.qr.blog.config.convert;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: QR
 * @Date: 2021/8/20-22:04
 */
@Configuration
public class ConvertConfig {

    /**
     * 处理Long类型的精度丢失
     * 表中的主键基本上为 bigInt 对应 Java 的 Long 类型, Long 类型在 JS 中有精度丢失, 需要序列化成字符串
     * @return Long 类型数据转换成 String 后的结果
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }
}
