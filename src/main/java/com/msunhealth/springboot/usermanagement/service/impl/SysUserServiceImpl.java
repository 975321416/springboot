package com.msunhealth.springboot.usermanagement.service.impl;

import com.msunhealth.springboot.usermanagement.dao.SysUserMapper;
import com.msunhealth.springboot.usermanagement.entity.SysUserEntity;
import com.msunhealth.springboot.usermanagement.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 15:45
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 功能描述:
     * 〈根据账户名查询用户信息〉
     *
     * @param username 1
     * @return : com.msunhealth.springboot.usermanagement.entity.SysUserEntity
     * @author : songhuanhao
     * @date : 2020/5/13 15:45
     */
    @Override
    public SysUserEntity selectSysUserByUserName(String username) {
        return sysUserMapper.selectSysUserByUserName(username);
    }
}
