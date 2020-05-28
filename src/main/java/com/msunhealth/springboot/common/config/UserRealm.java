package com.msunhealth.springboot.common.config;

import com.msunhealth.springboot.usermanagement.entity.SysUserEntity;
import com.msunhealth.springboot.usermanagement.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:shiro用户认证授权realm，加入spring容器
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 13:36
 * @Version 1.0
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    /**
     * 功能描述:
     * 〈shiro认证，认证用户账号密码〉
     *
     * @param token 1
     * @return : org.apache.shiro.authc.AuthenticationInfo
     * @author : songhuanhao
     * @date : 2020/5/13 13:38
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //将LoginController中的login方法中的传递UsernamePasswordToken 取出来
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //账号
        String username = usernamePasswordToken.getUsername();
        //密码
        String password = new String(usernamePasswordToken.getPassword());
        //根据账号查询用户
        SysUserEntity sysUserEntity = sysUserService.selectSysUserByUserName(username);
        if (sysUserEntity==null){
            throw new UnknownAccountException("账号不存在！");
        }
        if (!password.equals(sysUserEntity.getPassword())){
            throw new IncorrectCredentialsException("密码不正确！");
        }
        if (sysUserEntity.getStatus().equals("0")){
            throw new LockedAccountException("账号被冻结！");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(sysUserEntity,password,this.getName());

        return simpleAuthenticationInfo;
    }


    /**
     * 功能描述:
     * 〈shiro鉴权。为用户角色赋予权限〉
     *
     * @param principals 1
     * @return : org.apache.shiro.authz.AuthorizationInfo
     * @author : songhuanhao
     * @date : 2020/5/13 14:40
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }


}
