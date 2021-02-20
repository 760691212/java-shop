package com.shop.system.api;

import com.shop.system.domain.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface SysUserApi {

    @RequestMapping(value = "/getCaptcha" , method = RequestMethod.GET)
    String getCaptcha();

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    SysUser checkUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
}
