package com.shop.gateway.filter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        // 添加 cors 配置信息
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 允许的域，不要写 * ，负责cookie无法使用
        corsConfig.addAllowedOrigin("http://manage.shop.com");
        corsConfig.addAllowedOrigin("http://localhost:5425");
        // 是否发送 cookie 信息
        corsConfig.setAllowCredentials(true);
        // 允许的请求方式
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.addAllowedMethod("HEAD");
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedMethod("PATCH");
        // 允许头信息
        corsConfig.addAllowedHeader("*");
        // 添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);

        // 返回新的corsFilter
        return new CorsFilter(corsConfigSource);
    }
}
