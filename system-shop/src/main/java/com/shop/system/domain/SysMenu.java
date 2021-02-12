package com.shop.system.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_menu")
public class SysMenu {
    private static final long serialVersionUID = 594829320797158219L;
    @Id
    private String menuId; // 菜单id
    private String title; // 菜单标题
    private String icon; // 图标
    private Integer type; // 类型(目录、菜单、按钮)
    private Integer nodeType; // 节点类型 （根节点、父节点、子节点）
    private String path; // 路由地址
    private String pid; // 上级菜单ID
    private Integer subCount; // 子菜单个数
    private Integer sort; // 菜单排序
    private String permission; // 权限标识
    private String component; // 组件
    private String framePath; // 外链地址
    private String name; // 组件名称
    private Integer isFrame; // 是否为外链菜单
    private Integer isCache; //  是否缓存
    private Integer isHidden; //  是否隐藏
    private Integer isDelete; //  是否删除
    @CreatedDate
    private Date createTime; //创建日期
    @LastModifiedDate
    private Date updateTime; // 修改日期
    private String createBy; // 创建者
    private String updateBy; // 修改者


    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setFramePath(String framePath) {
        this.framePath = framePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    public Integer getIsCache() {
        return isCache;
    }

    public void setIsCache(Integer isCache) {
        this.isCache = isCache;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFramePath() {
        return framePath;
    }
}
