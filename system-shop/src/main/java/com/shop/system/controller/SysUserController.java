package com.shop.system.controller;

import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import com.shop.system.domain.SysUser;
import com.shop.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
        String sessionName = null;
        try {
            sessionName = this.sysUserService.getCaptcha(request,response,width,height,imgType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, sessionName);
    }
    @ApiOperation("校验系统用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result checkUser(
        @RequestParam("username") String username,
        @RequestParam("password") String password
    ){
        SysUser sysUser =  this.sysUserService.checkUser(username,password);
        return new Result(ResultCode.SUCCESS, sysUser);
    }


//    @ApiOperation("查询系统用户")
//    @RequestMapping(value = "/query", method = RequestMethod.GET)
//    private Result querySysUser(
//            @RequestParam(value = "username", required = false)String username,
//            @RequestParam(value = "phone", required = false)String phone,
//            @RequestParam(value = "idcard", required = false)String idcard,
//            @RequestParam(value = "email", required = false)String email,
//            @RequestParam(value = "deptId", required = false)String deptId,
//            @RequestParam(value = "jobId", required = false)String jobId,
//            @RequestParam(value = "page", defaultValue = "0")Integer page,
//            @RequestParam(value = "size", defaultValue = "20")Integer size,
//            @RequestParam(value = "sort", required = false)String sort,
//            @RequestParam(value = "desc", required = false)Boolean desc
//    ){
//
//    }
}
