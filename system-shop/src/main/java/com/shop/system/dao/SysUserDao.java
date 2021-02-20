package com.shop.system.dao;

import com.shop.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
}
