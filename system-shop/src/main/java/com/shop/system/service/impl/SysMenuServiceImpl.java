package com.shop.system.service.impl;

import com.shop.common.utils.IdWorker;
import com.shop.common.entity.PageResult;
import com.shop.system.dao.SysMenuDao;
import com.shop.common.domain.SysMenu;
import com.shop.common.domain.SysMenuSvg;
import com.shop.system.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private IdWorker idWorker;

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
    public PageResult<SysMenu> query(String key, Integer page, Integer size, String sort, Boolean desc) {
        Specification<SysMenu> specification = new Specification<SysMenu>() {
            @Override
            public Predicate toPredicate(Root<SysMenu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //criteriaBuilder:构建查询，添加查询方式   like：模糊匹配
                //root：从实体Customer对象中按照custName属性进行查询
                if (StringUtils.isNotBlank(key)){
                    return  criteriaBuilder.like(root.get("title").as(String.class), key);
                }
                return null;
            }
        };
        Pageable pageable = null;
        if (StringUtils.isNotBlank(sort)){
            Sort newSort = new Sort(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sort);
            pageable = new PageRequest(page, size, newSort);
        }else {
            pageable = new PageRequest(page, size);
        }
        Page<SysMenu> sysMenus = this.sysMenuDao.findAll(specification, pageable);
        return  new PageResult(sysMenus.getTotalElements(), sysMenus.getContent());
    }



    /**
     * 新增系统菜单
     * @param sysMenu 传入参数
     */
    @Override
    @Transactional
    public void add(SysMenu sysMenu) {
        String id = idWorker.nextId() + "";
        // 判断是否存在父节点
        if (StringUtils.isNotBlank(sysMenu.getPid())){
            sysMenu.setNodeType(1); // 子节点
            SysMenu parent = this.sysMenuDao.findById(sysMenu.getPid()).get();
            if (parent.getSubCount() == null){
                parent.setSubCount(1);
            }else {
                parent.setSubCount(parent.getSubCount() + 1);
            }
            this.sysMenuDao.save(parent); // 保存设置
        }else {
            sysMenu.setNodeType(0); // 根节点
            sysMenu.setSubCount(0); // 设置子菜单个数
        }
        sysMenu.setCreateTime(new Date());
        sysMenu.setMenuId(id);
        this.sysMenuDao.save(sysMenu);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional
    public void del(List<String> ids) {
        List<String> delIds = new ArrayList<>();
        ids.forEach(id -> {
            // 查询
            SysMenu menu = this.sysMenuDao.findById(id).get();
            // 获取菜单节点不为子节点
            if (menu.getType() != 2 && menu.getNodeType() != 1){
                List<SysMenu> subMenus = this.sysMenuDao.findByPid(menu.getMenuId());
                List<String> idList = this.handleDeleteId(subMenus);
                delIds.addAll(idList);
            }
        });
        delIds.addAll(ids);
        this.sysMenuDao.deleteByIds(delIds);
    }

    /**
     * 创建svg文件
     * @param sysMenuSvg
     */
    @Override
    public void handleUploadSvg(SysMenuSvg sysMenuSvg) {
        String path = "D:\\e-commerceProject\\web procedure\\shop\\management-shop\\src\\assets\\icons\\svg\\" + sysMenuSvg.getName() + ".svg";
        FileWriter writer = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            //目录不存在 则创建
            if (!file.getParentFile().exists()) {
                boolean mkdir = file.getParentFile().mkdirs();
                if (!mkdir) {
                    throw new RuntimeException("创建目标文件所在目录失败！");
                }
            }
            file.createNewFile();
            writer = new FileWriter(file);
            if (null != sysMenuSvg.getSvg()) {
                writer.write(sysMenuSvg.getSvg());
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("文件写入异常", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw  new RuntimeException("文件写入时流关闭异常", e);
                }
            }
        }
    }

    /**
     * 菜单修改
     * @param sysMenu
     */
    @Override
    public void edit(SysMenu sysMenu) {
        sysMenu.setUpdateTime(new Date());
        this.sysMenuDao.save(sysMenu);
    }

    /**
     * 递归获取 id
     * @param subMenus
     * @return
     */
    private List<String> handleDeleteId(List<SysMenu> subMenus){
        List<String> ids = new ArrayList<>();
        if (subMenus.size() > 0){
            subMenus.forEach( sub -> {
                if (sub.getNodeType() != 2){
                    List<SysMenu> menuList = this.sysMenuDao.findByPid(sub.getMenuId());
                    List<String> newSubMenus = this.handleDeleteId(menuList);
                    ids.addAll(newSubMenus);
                }
                ids.add(sub.getMenuId());
            });
        }
        return ids;
    }
}
