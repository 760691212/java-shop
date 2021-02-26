package com.shop.auth.client;


import com.shop.userInterface.api.SysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service-shop")
public interface SysUserClient extends SysUserApi {
}
