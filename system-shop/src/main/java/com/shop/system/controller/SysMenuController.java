package com.shop.system.controller;


import com.shop.common.domain.SysMenu;
import com.shop.common.entity.PageResult;
import com.shop.common.entity.Result;
import com.shop.common.entity.ResultCode;
import com.shop.common.domain.SysMenuSvg;
import com.shop.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@Api(value = "菜单管理模块", tags = "菜单管理模块")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("获取菜单数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "查询关键字", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "string",  paramType = "query"),
            @ApiImplicitParam(name = "desc", value = "是否降序排列", dataType = "string", required = true,  paramType = "query")
    })
    @RequestMapping(value = "/query" , method = RequestMethod.GET)
    public Result querySysMenus(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "20")Integer size,
            @RequestParam(value = "sort", required = false)String sort,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<SysMenu> menus = this.sysMenuService.query(key, page, size, sort, desc);
        return new Result(ResultCode.SUCCESS, menus);
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ApiOperation("新增菜单数据")
    public Result addSysMenu(@RequestBody SysMenu sysMenu){

        this.sysMenuService.add(sysMenu);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除菜单数据(事务删除)")
    public Result delSysMenu(@RequestBody List<String> ids){
        this.sysMenuService.del(ids);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ApiOperation(value = "修改菜单数据")
    public Result editSysMenu(@RequestBody SysMenu sysMenu){
        this.sysMenuService.edit(sysMenu);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/saveSvgIcon", method = RequestMethod.POST)
    @ApiOperation(value = "生成并保存svg文件")
    public Result handleUploadSvg(@RequestBody SysMenuSvg sysMenuSvg){
        this.sysMenuService.handleUploadSvg(sysMenuSvg);
        return Result.SUCCESS();
    }


}
