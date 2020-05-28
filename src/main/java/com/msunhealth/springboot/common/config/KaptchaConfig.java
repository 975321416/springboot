package com.msunhealth.springboot.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Description:
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/25 13:39
 * @Version 1.0
 */
@Configuration
public class KaptchaConfig {

    @Bean("defaultKaptcha")
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha =new DefaultKaptcha();
        Properties properties = new Properties();
        //验证码的设置
        //验证码边框
        properties.put("kaptcha.border", "yes");
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        properties.put("kaptcha.textproducer.font.color", "yellow");
        //验证码个数
        properties.put("kaptcha.textproducer.char.length", "1");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
