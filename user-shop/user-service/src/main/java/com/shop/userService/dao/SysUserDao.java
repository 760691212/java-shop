package com.shop.userService.dao;

import com.shop.userInterface.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
    SysUser findByUsername(String username);
}
