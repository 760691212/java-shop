package com.shop.userService.service;

import com.shop.common.entity.PageResult;
import com.shop.userInterface.domain.SysUser;

public interface SysUserService {
    void saveSysUser(SysUser sysUser);

    void delSysUser(String sysUserId);

    PageResult<SysUser> querySysUserList(String username, String sex, String phone, String email, String deptId, String jobId, Integer page, Integer size, String sort, Boolean desc);

    SysUser checkUser(String username, String password);

    SysUser queryUserByUserName(String username);
}
