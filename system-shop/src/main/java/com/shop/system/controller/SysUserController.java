package com.shop.system.controller;

import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import com.shop.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Api(value = "系统管理员模块", tags = "系统管理员模块")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/getCaptcha" , method = RequestMethod.GET)
    @ApiOperation("获取验证码")
    public Result getCaptcha(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "width", defaultValue = "150")Integer width,
            @RequestParam(value = "height", defaultValue = "26")Integer height,
            @RequestParam(value = "imgType", defaultValue = "jpg")String imgType){
        String captchaImage = null;
        try {
            captchaImage = this.sysUserService.getCaptcha(request,response,width,height,imgType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(captchaImage)){
            return Result.FAIL_GET_CAPTCH();
        }else {
            return new Result(ResultCode.SUCCESS, captchaImage);
        }
    }
}
