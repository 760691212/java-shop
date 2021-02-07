package com.shop.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.common.doman.PageResult;
import com.shop.system.doman.SystemMenu;
import com.shop.system.mapper.SystemMenuMapper;
import com.shop.system.service.SystemMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuMapper menuMapper;

    @Override
    public PageResult<SystemMenu> querySysMenus(String key, Integer page, Integer size, String sort, Boolean desc) {
        // 初始化 example 对象
        Example example = new Example(SystemMenu.class);
        // 根据name模糊查询，或者根据首字母查询
        if(StringUtils.isNotBlank(key)){
            System.out.println(key);
            example.createCriteria().orLike("title", "%" + key + "%").orEqualTo("name", key);
        }
        // 添加分页条件
        PageHelper.startPage(page, size);
        // 添加排序条件
        if (StringUtils.isNotBlank(sort)) {
            System.out.println(sort);
            example.setOrderByClause(sort + " " + (desc ? "desc" : "asc"));
        }
        List<SystemMenu> menuList = this.menuMapper.selectByExample(example);
        PageInfo<SystemMenu> pageInfo = new PageInfo<>(menuList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
