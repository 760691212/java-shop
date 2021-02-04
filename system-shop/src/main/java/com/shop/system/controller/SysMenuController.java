package com.shop.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(value = "菜单管理模块", tags = "菜单管理模块")
@RequestMapping("/sysMenu")
public class SysMenuController {

    @ApiOperation("获取全部菜单信息")
    @GetMapping("/getList")
    public String getSysMenuList(){
        return "这是菜单列表";
    }
}
