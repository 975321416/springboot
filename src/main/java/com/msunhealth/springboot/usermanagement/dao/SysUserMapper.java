package com.msunhealth.springboot.usermanagement.dao;

import com.msunhealth.springboot.usermanagement.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:系统用户Mapper
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 15:24
 * @Version 1.0
 */
@Mapper
public interface SysUserMapper {

    SysUserEntity selectSysUserByUserName(String userName);

}
