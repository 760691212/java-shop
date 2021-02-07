package com.shop.system.service;

import com.common.doman.PageResult;
import com.shop.system.doman.SystemMenu;

public interface SystemMenuService {
    PageResult<SystemMenu> querySysMenus(String key, Integer page, Integer size, String sort, Boolean desc);

    void addSysMenu(SystemMenu systemMenu);

}
