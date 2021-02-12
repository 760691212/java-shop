package com.shop.system.dao;

import com.shop.system.domain.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysMenuDao extends JpaRepository<SysMenu, String>, JpaSpecificationExecutor<SysMenu> {
    List<SysMenu> findByPid(String menuId);

    @Modifying
    @Query(value = "delete from sys_menu where menu_id in (:id)", nativeQuery = true)
    void deleteByIds(@Param("id") List<String> id);

//    @Modifying
//    @Query(value = "select COUNT(pid) from sys_menu WHERE pid = id", nativeQuery = true)
//    int findSubMenusCount(@Param("id") String id);
}

