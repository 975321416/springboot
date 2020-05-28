package com.msunhealth.springboot.usermanagement.entity;

/**
 * @Description:用户表sys_user实体
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 15:12
 * @Version 1.0
 */
public class SysUserEntity {

    /**
     * 用户ID
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String userID;
    /**
     * 账户
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String userName;
    /**
     * 中文名
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String cnName;
    /**
     * 密码
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String password;
    /**
     * 邮箱
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String email;
    /**
     * 手机号
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String mobile;
    /**
     * 状态
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String status;
    /**
     * 创建人ID
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String createUserID;
    /**
     * 创建时间
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String createTime;
    /**
     * 更新时间
     * @author : songhuanhao
     * @date : 2020/5/13 15:20
     */
    private String updateTime;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
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
