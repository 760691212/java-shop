package com.shop.system.domain;

import java.io.Serializable;

public class SysMenuSvg implements Serializable {
    private String name; // svg 的名称
    private String svg; // svg 内容

    public SysMenuSvg() {
    }

    public SysMenuSvg(String name, String svg) {
        this.name = name;
        this.svg = svg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
