package com.shop.common.domain;

import java.util.List;

public class SysMenuExtend extends SysMenu{
    private List<SysMenu> Children;
    private Boolean hasChildren;

    public SysMenuExtend() {
        super();
    }

    public List<SysMenu> getChildren() {
        return Children;
    }

    public void setChildren(List<SysMenu> children) {
        Children = children;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
