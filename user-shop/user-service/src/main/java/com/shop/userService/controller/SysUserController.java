package com.shop.userService.controller;


import com.shop.common.entity.PageResult;
import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import com.shop.common.utils.CaptchaUtils;
import com.shop.common.utils.IdWorker;
import com.shop.userInterface.domain.LoginUserInfo;
import com.shop.userInterface.domain.SysUser;
import com.shop.userService.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("sysUser")
@Api(value = "用户中心模块", tags = "用户中心模块")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CaptchaUtils captchaUtils;

    /**
     * 查询系统管理员列表
     * @param username 用户名查询
     * @param sex 性别
     * @param phone 电话
     * @param email 邮箱
     * @param deptId 单位
     * @param jobId 职务
     * @param page 页数
     * @param size 每页数据条数
     * @param sort 排序
     * @param desc 是否倒序
     * @return 分页结果集
     */
    @ApiOperation("查询系统管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名查询", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "电话", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deptId", value = "单位", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "jobId", value = "职务", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页数据条数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "desc", value = "是否倒序", required = false, dataType = "Boolean", paramType = "query"),
    })
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result querySysUser(
            @RequestParam(value = "username", required = false)String username,
            @RequestParam(value = "sex", required = false)String sex,
            @RequestParam(value = "phone", required = false)String phone,
            @RequestParam(value = "email", required = false)String email,
            @RequestParam(value = "deptId", required = false)String deptId,
            @RequestParam(value = "jobId", required = false)String jobId,
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "20")Integer size,
            @RequestParam(value = "sort", required = false)String sort,
            @RequestParam(value = "desc", required = false)Boolean desc
            ){
        PageResult<SysUser> sysUsers = this.userService.querySysUserList(username, sex, phone, email, deptId, jobId, page, size, sort, desc);
        return new Result(ResultCode.SUCCESS,sysUsers);
    }
    /**
     * 新增系统管理员
     * @param sysUser
     * @return
     */
    @ApiOperation("新增系统管理员")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Result createSysUser(@RequestBody SysUser sysUser){
        String id = idWorker.nextId() + "";
        sysUser.setCreateTime(new Date());
        sysUser.setUserId(id);
        // spring提供了BCryptPasswordEncoder工具底层封装了MD5盐值加密,并且无需在数据库中维持salt字段
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(bCryptPasswordEncoder.encode("123456"));
        this.userService.saveSysUser(sysUser);
        return Result.SUCCESS();
    }

    /**
     * 编辑系统管理员
     * @param sysUser
     * @return
     */
    @ApiOperation("编辑系统管理员")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUser.setUpdateTime(new Date());
        this.userService.saveSysUser(sysUser);
        return  Result.SUCCESS();
    }

    /**
     * 删除系统管理员
     * @param ids
     * @return
     */
    @ApiOperation("删除系统管理员")
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Result delSysUser(@RequestBody List<String> ids){
        this.userService.delSysUser(ids);
        return  Result.SUCCESS();
    }

    /**
     * 验证系统用户
     * @param loginUserInfo 登陆时 用户信息
     * @return sysUser
     */
    @ApiOperation("验证系统用户")
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public SysUser checkUser(@RequestBody LoginUserInfo loginUserInfo){
        String captcha = this.redisTemplate.opsForValue().get(this.captchaUtils.GET_CAPTCHA_SESSION());
        System.out.println(StringUtils.isNotBlank(captcha));
        System.out.println(loginUserInfo.getCode());
        System.out.println(captcha.toUpperCase());
        System.out.println(loginUserInfo.getCode().toUpperCase().equals(captcha.toUpperCase()));
        if (StringUtils.isNotBlank(captcha) && loginUserInfo.getCode().toUpperCase().equals(captcha.toUpperCase())){
            SysUser sysUser = this.userService.checkUser(loginUserInfo.getUsername(), loginUserInfo.getPassword());
            if (sysUser != null && !ObjectUtils.isEmpty(sysUser)){
                return sysUser;
            }
        }
        return null;
    }

    @ApiOperation("根据用户名查询用户")
    @RequestMapping(value = "/queryUserByUserName")
    public Result queryUserByUserName(@RequestParam(value = "username") String username){
        SysUser sysUser = this.userService.queryUserByUserName(username);
        return new Result(ResultCode.SUCCESS, sysUser);
    }

}
