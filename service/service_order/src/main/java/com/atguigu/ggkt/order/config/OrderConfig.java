package com.atguigu.ggkt.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Qiao
 * @Create 2022/10/12 12:49
 */

@Configuration
@MapperScan("com.atguigu.ggkt.order.mapper")
public class OrderConfig {

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
