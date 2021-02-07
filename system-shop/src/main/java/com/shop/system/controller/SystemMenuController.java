package com.shop.system.controller;

import com.common.doman.PageResult;
import com.shop.system.doman.SystemMenu;
import com.shop.system.service.SystemMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/menus")
@Api(value = "菜单管理模块", tags = "菜单管理模块")
public class SystemMenuController {

    @Autowired
    private SystemMenuService menuService;

    @GetMapping("/query")
    @ApiOperation("获取菜单数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "查询关键字", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "string",  paramType = "query"),
            @ApiImplicitParam(name = "desc", value = "是否降序排列", dataType = "string", required = true,  paramType = "query")
    })
    public ResponseEntity<PageResult<SystemMenu>> querySysMenus(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "20")Integer size,
            @RequestParam(value = "sort", required = false)String sort,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<SystemMenu> menus = menuService.querySysMenus(key, page, size, sort, desc);
        return ResponseEntity.ok(menus);
    }


}
