package com.shop.system.doman;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_menu")
public class SystemMenu {
    private static final long serialVersionUID = 594829320797158219L;
    @Id
    private String menuId;
    private String title;
    private Integer type;
    private Integer nodeType;
    private String path;
    private String pid;
    private Integer subCount;
    private Integer sort;
    private String permission;
    private String component;
    private String framePath;
    private String name;
    private Integer isFrame;
    private Integer isCache;
    private Integer isHidden;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;

    public SystemMenu() {
    }

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

    public String getFramePath() {
        return framePath;
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

    public void setIsFrame(Integer is_frame) {
        isFrame = is_frame;
    }

    public Integer getIsCache() {
        return isCache;
    }

    public void setIsCache(Integer is_cache) {
        isCache = is_cache;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer is_hidden) {
        isHidden = is_hidden;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer is_delete) {
        isDelete = is_delete;
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

    @Override
    public String toString() {
        return "SystemMenu{" +
                "menuId='" + menuId + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", nodeType=" + nodeType +
                ", path='" + path + '\'' +
                ", pid='" + pid + '\'' +
                ", subCount=" + subCount +
                ", sort=" + sort +
                ", permission='" + permission + '\'' +
                ", component='" + component + '\'' +
                ", framePath='" + framePath + '\'' +
                ", name='" + name + '\'' +
                ", isFrame=" + isFrame +
                ", isCache=" + isCache +
                ", isHidden=" + isHidden +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
