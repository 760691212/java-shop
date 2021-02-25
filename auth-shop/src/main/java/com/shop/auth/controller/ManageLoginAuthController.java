package com.shop.auth.controller;

import com.shop.auth.service.ManageLoginAuthService;
import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@Api(value = "后台管理系统登录授权中心模块", tags = "后台管理系统登录授权中心模块")
public class ManageLoginAuthController {
    @Autowired
    private ManageLoginAuthService authService;

    @RequestMapping(value = "/getCaptcha" , method = RequestMethod.GET)
    @ApiOperation("获取验证码")
    public Result getCaptcha(HttpServletRequest request,
                             HttpServletResponse response,
                            @RequestParam(value = "width",defaultValue = "150") Integer width,
                            @RequestParam(value = "height",defaultValue = "26") Integer height,
                         @RequestParam(value = "imgType", defaultValue = "jpg") String imgType){
        String sessionName = null;
        try {
            sessionName = this.authService.getCaptcha(request,response,width,height,imgType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, sessionName);
    }
}
