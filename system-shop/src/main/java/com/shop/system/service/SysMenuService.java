package com.shop.system.service;

import com.common.entity.PageResult;
import com.shop.system.domain.SysMenu;
import com.shop.system.domain.SysMenuSvg;

import java.util.List;

public interface SysMenuService {
    PageResult<SysMenu> query(String key, Integer page, Integer size, String sort, Boolean desc);

    void add(SysMenu sysMenu);

    void del(List<String> ids);

    void handleUploadSvg(SysMenuSvg sysMenuSvg);

    void edit(SysMenu sysMenu);
}