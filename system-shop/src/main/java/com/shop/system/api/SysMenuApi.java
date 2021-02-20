package com.shop.system.api;

import com.shop.common.entity.Result;
import com.shop.system.domain.SysMenu;
import com.shop.system.domain.SysMenuSvg;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/menus")
public interface SysMenuApi {
    @RequestMapping(value = "/query" , method = RequestMethod.GET)
    Result querySysMenus(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "20")Integer size,
            @RequestParam(value = "sort", required = false)String sort,
            @RequestParam(value = "desc", required = false)Boolean desc
    );

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    Result addSysMenu(@RequestBody SysMenu sysMenu);

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    Result delSysMenu(@RequestBody List<String> ids);

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    Result editSysMenu(@RequestBody SysMenu sysMenu);

    @RequestMapping(value = "/saveSvgIcon", method = RequestMethod.POST)
    Result handleUploadSvg(@RequestBody SysMenuSvg sysMenuSvg);

}
