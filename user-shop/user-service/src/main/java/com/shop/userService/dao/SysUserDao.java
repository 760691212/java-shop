package com.shop.userService.dao;

import com.shop.userInterface.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
    SysUser findByUsername(String username);

    @Modifying
    @Query(value = "delete from sys_user where user_id in (:id)", nativeQuery = true)
    void deleteByIds(@Param("id") List<String> id);
}
