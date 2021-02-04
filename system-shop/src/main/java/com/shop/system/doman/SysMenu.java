package com.shop.system.doman;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
 * TABLE：使用一个特定的数据库表格来保存主键。
 * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
 * IDENTITY：主键由数据库自动生成（主要是自动增长型）
 * AUTO：主键由程序控制。
 */
@Table(name="sys_menu")
public class SysMenu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
    private Boolean isFrame;
    private Boolean isCache;
    private Boolean isHidden;
    private Boolean isAlwaysShow;
    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;

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

    public Boolean getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Boolean is_frame) {
        isFrame = is_frame;
    }

    public Boolean getIsCache() {
        return isCache;
    }

    public void setIsCache(Boolean is_cache) {
        isCache = is_cache;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean is_hidden) {
        isHidden = is_hidden;
    }

    public Boolean getIsAlwaysShow() {
        return isAlwaysShow;
    }

    public void setIsAlwaysShow(Boolean is_alwaysShow) {
        isAlwaysShow = is_alwaysShow;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean is_delete) {
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
}
