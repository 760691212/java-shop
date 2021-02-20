package com.shop.auth.client;

import com.shop.system.api.SysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "system-shop")
public interface SysUserClient extends SysUserApi {
}
