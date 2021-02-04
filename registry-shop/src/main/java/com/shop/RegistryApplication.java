package com.shop;

import com.shop.config.NginxStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {
    public static void main(String[] args) {
        NginxStart.startProc();
        SpringApplication.run(RegistryApplication.class,args);
    }
}


