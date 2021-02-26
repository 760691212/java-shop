package com.shop.userService.service.impl;

import com.shop.common.entity.PageResult;
import com.shop.userInterface.domain.SysUser;
import com.shop.userService.dao.SysUserDao;
import com.shop.userService.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Override
    @Transactional
    public void saveSysUser(SysUser sysUser) {
        this.userDao.save(sysUser);
    }

    @Override
    @Transactional
    public void delSysUser(List<String> ids) {
        this.userDao.deleteByIds(ids);
    }

    @Override
    public PageResult<SysUser> querySysUserList(String username, String sex, String phone, String email, String deptId, String jobId, Integer page, Integer size, String sort, Boolean desc) {
        Specification<SysUser> specification = new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(username)) {
                    predicateList.add(cb.like(cb.lower(r.get("username").as(String.class)), "%" + username + "%"));
                }
                if (StringUtils.isNotBlank(sex)) {
                    predicateList.add(cb.like(cb.lower(r.get("sex").as(String.class)), "%" + sex + "%"));
                }
                if (StringUtils.isNotBlank(phone)) {
                    predicateList.add(cb.like(cb.lower(r.get("phone").as(String.class)), "%" + phone + "%"));
                }
                if (StringUtils.isNotBlank(email)) {
                    predicateList.add(cb.like(cb.lower(r.get("email").as(String.class)), "%" + email + "%"));
                }
                if (StringUtils.isNotBlank(deptId)) {
                    predicateList.add(cb.like(cb.lower(r.get("deptId").as(String.class)), "%" + deptId + "%"));
                }
                if (StringUtils.isNotBlank(jobId)) {
                    predicateList.add(cb.like(cb.lower(r.get("jobId").as(String.class)), "%" + jobId + "%"));
                }
                Predicate[] andPredicate = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(andPredicate));
            }
        };
        Pageable pageable = null;
        if (StringUtils.isNotBlank(sort)){
            Sort newSort = new Sort(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sort);
            pageable = new PageRequest(page, size, newSort);
        }else {
            pageable = new PageRequest(page, size);
        }
        Page<SysUser> sysUsers = this.userDao.findAll(specification, pageable);
        return new PageResult(sysUsers.getTotalElements(), sysUsers.getContent());
    }

    @Override
    public SysUser checkUser(String username, String password) {
        SysUser sysUser = this.queryUserByUserName(username);
            // spring提供了BCryptPasswordEncoder工具底层封装了MD5盐值加密,并且无需在数据库中维持salt字段
        if (!ObjectUtils.isEmpty(sysUser)){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(password, sysUser.getPassword());
            if (matches) return sysUser;
        }
        return null;
    }

    @Override
    public SysUser queryUserByUserName(String username) {
        SysUser sysUser = this.userDao.findByUsername(username);
        return sysUser;
    }

}
