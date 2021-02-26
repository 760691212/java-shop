package com.shop.userInterface.api;

import com.shop.common.entity.Result;
import com.shop.userInterface.domain.LoginUserInfo;
import com.shop.userInterface.domain.SysUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SysUserApi {
    @RequestMapping(value = "/sysUser/query", method = RequestMethod.GET)
    List<SysUser> querySysUser(
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
    );

    @RequestMapping(value = "/sysUser/add" , method = RequestMethod.POST)
    void createSysUser(@RequestBody SysUser sysUser);

    @RequestMapping(value = "/sysUser/edit", method = RequestMethod.PUT)
    void updateSysUser(@RequestBody SysUser sysUser);

    @RequestMapping(value = "/sysUser/del", method = RequestMethod.DELETE)
    void delSysUser(@RequestBody List<String> ids);

    @RequestMapping(value = "/sysUser/check", method = RequestMethod.POST)
    Result checkUser(@RequestBody LoginUserInfo loginUserInfo);

    @RequestMapping(value = "/sysUser/queryUserByUserName")
    SysUser queryUserByUserName(@RequestParam(value = "username") String username);
}
