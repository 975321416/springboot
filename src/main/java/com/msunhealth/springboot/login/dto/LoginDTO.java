package com.msunhealth.springboot.login.dto;

/**
 * @Description:
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/25 10:07
 * @Version 1.0
 */
public class LoginDTO {
    /**
     * 〈账号〉
     * @author : songhuanhao
     * @date : 2020/5/26 9:56
     */
    private String username;
    /**
     * 〈密码〉
     * @author : songhuanhao
     * @date : 2020/5/26 9:56
     */
    private String password;
    /**
     * 〈验证码〉
     * @author : songhuanhao
     * @date : 2020/5/26 9:56
     */
    private String captcha;
    /**
     * 〈记住我〉
     * @author : songhuanhao
     * @date : 2020/5/26 9:56
     */
    private boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
