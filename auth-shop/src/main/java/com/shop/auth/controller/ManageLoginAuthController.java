package com.shop.auth.controller;

import com.shop.auth.client.SysUserClient;
import com.shop.auth.config.JwtProperties;
import com.shop.auth.service.ManageLoginAuthService;
import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import com.shop.common.utils.CookieUtils;
import com.shop.common.utils.JwtUtils;
import com.shop.common.utils.UserInfo;
import com.shop.userInterface.domain.LoginUserInfo;
import com.shop.userInterface.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@Api(value = "后台管理系统登录授权中心模块", tags = "后台管理系统登录授权中心模块")
@EnableConfigurationProperties(JwtProperties.class)
public class ManageLoginAuthController {
    @Autowired
    private ManageLoginAuthService authService;

    @Autowired
    private SysUserClient sysUserApi;

    @Autowired
    private JwtProperties prop;

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
    public Result checkLogin(@RequestBody LoginUserInfo loginUserInfo,HttpServletRequest request,HttpServletResponse response){
        SysUser sysUser = this.sysUserApi.checkUser(loginUserInfo);
        if (!ObjectUtils.isEmpty(sysUser)){
            String token = null;
            try {
                token = JwtUtils.generateToken(new UserInfo(sysUser.getUserId(), sysUser.getUsername()), prop.getPrivateKey(), prop.getExpire());
            } catch (Exception e) {
                e.printStackTrace();
            }
            CookieUtils.setCookie(request,response,prop.getCookieName(),token,prop.getCookieMaxAge(),null,true);
            return new Result(ResultCode.SUCCESS,sysUser);
        }
        return Result.FAIL_LOGIN_CHECK();
    }

    /**
     * 验证用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public Result verifyUser(@CookieValue("SHOP_AUTH")String token){
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.prop.getPublicKey());
            return new Result(ResultCode.SUCCESS,userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.FAIL_LOGIN_CHECK();
    }
}
