package com.shop.system.service.impl;

import com.common.doman.IdWorker;
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

    /**
     * 系统菜单数据查询
     * @param key 关键字查询 可查内容为 菜单名称（模糊查询）  组件名称
     * @param page 当前页
     * @param size 每页条数
     * @param sort 排序 规则
     * @param desc 是否 倒序排序
     * @return
     */
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

    /**
     * 新增系统菜单
     * @param systemMenu 传入参数
     */
    @Override
    public void addSysMenu(SystemMenu systemMenu) {

        systemMenu.setMenuId(new IdWorker().nextId() + "");
        this.menuMapper.insertSelective(systemMenu);
    }
}
