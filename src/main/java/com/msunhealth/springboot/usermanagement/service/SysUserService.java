package com.msunhealth.springboot.usermanagement.service;

import com.msunhealth.springboot.usermanagement.entity.SysUserEntity;

/**
 * @Description:系统用户相关业务
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 15:03
 * @Version 1.0
 */
public interface SysUserService {


    SysUserEntity selectSysUserByUserName(String username);



}
