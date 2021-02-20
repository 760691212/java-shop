package com.shop.system;

import com.shop.common.utils.CaptchaUtils;
import com.shop.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EntityScan("com.shop.system.domain")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public CaptchaUtils captchaUtils() {
        return  new CaptchaUtils();
    }
}
