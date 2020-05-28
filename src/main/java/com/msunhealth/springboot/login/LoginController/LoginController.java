package com.msunhealth.springboot.login.LoginController;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.msunhealth.springboot.login.dto.LoginDTO;
import com.msunhealth.springboot.utils.AjaxRusult;
import com.msunhealth.springboot.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description:
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 11:04
 * @Version 1.0
 */
@Controller
@RequestMapping("/sys")
public class LoginController {

    //注入验证码配置
    @Resource
    DefaultKaptcha defaultKaptcha;

    /**
     * 功能描述:
     * 〈获得验证码〉
     *
     * @param response 1
     * @return : void
     * @author : songhuanhao
     * @date : 2020/5/26 9:34
     */
    @RequestMapping("/getcaptcha")
    public void getCaptcha(HttpServletResponse response){
        try {
        //根据配置文件生成动态验证文字
        String text = defaultKaptcha.createText();
        Session session = SecurityUtils.getSubject().getSession();
        //将动态文字设入会话中。用于登录验证
        session.setAttribute("KAPTCHA", text);
        //根据文字生成图片
        BufferedImage image = defaultKaptcha.createImage(text);
        //Java Image I/O API 的主要包。使用 ImageIO 类的静态方法可以执行许多常见的图像 I/O 操作。
        ImageIO.write(image, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述:
     * 〈登录接口〉
     *
     * @param loginDTO 1
     * @return : com.msunhealth.springboot.utils.AjaxRusult
     * @author : songhuanhao
     * @date : 2020/5/26 9:34
     */
    @RequestMapping("/login")
    @ResponseBody
    public AjaxRusult login(@RequestBody LoginDTO loginDTO) {
        //先判断验证码
        //shiro先获取当前操作对象
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String kaptcha = (String)session.getAttribute("KAPTCHA");
        if (!kaptcha.equals(loginDTO.getCaptcha())){
            return AjaxRusult.error("验证码错误");
        }
        try {
            loginDTO.setPassword(MD5Utils.MD5(loginDTO.getPassword(),loginDTO.getUsername() , 1024));
            //把用户名和密码封装成Token对象
            UsernamePasswordToken token=new UsernamePasswordToken(loginDTO.getUsername(),loginDTO.getPassword());
            //记住我,如果设置为记住我
            if (loginDTO.isRememberMe()){
                token.setRememberMe(true);
            }
            //调用subject的login方法，login方法底层又会调用自定义的realm，UserRealm
            subject.login(token);
            return AjaxRusult.ok();
        } catch (AuthenticationException e) {
            return  AjaxRusult.error(e.getMessage());
        }
    }







}
