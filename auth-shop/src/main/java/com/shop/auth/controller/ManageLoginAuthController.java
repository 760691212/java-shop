package com.shop.auth.controller;

import com.shop.auth.client.SysUserClient;
import com.shop.auth.service.ManageLoginAuthService;
import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import com.shop.common.utils.CaptchaUtils;
import com.shop.userInterface.domain.LoginUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@Api(value = "后台管理系统登录授权中心模块", tags = "后台管理系统登录授权中心模块")
public class ManageLoginAuthController {
    @Autowired
    private ManageLoginAuthService authService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CaptchaUtils captchaUtils;

    @Autowired
    private SysUserClient sysUserApi;

    /**
     * 获取验证码
     * @param request 请求
     * @param response 响应
     * @param width 宽度
     * @param height 高度
     * @param imgType 图片类型
     * @return 图片名称
     */
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

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ApiOperation("校验登录")
    public Result checkLogin(@RequestBody LoginUserInfo loginUserInfo){
        return this.sysUserApi.checkUser(loginUserInfo);
    }
}
