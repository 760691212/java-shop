package com.shop.system.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
public class SysUserExtend extends SysUser{
    private String rolesId; // 角色
    private String sex; // 性别
    private String idcard;  // 身份证
    private String phone;   // 电话
    private String email;   // 邮箱
    private String deptId;  // 单位id
    private String jobId;   // 职务id
    private String image;   // 头像
    private String isDelete;    // 是否删除
    private String createTime;  // 创建日期
    private String updateTime;  // 修改日期

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
