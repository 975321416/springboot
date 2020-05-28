package com.msunhealth.springboot.common.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:shiro配置类
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2020/5/13 11:08
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    @Resource
    UserRealm userRealm;

    /**
     * 功能描述:
     * 〈创建sessionManager会话管理，使用shiro管理session〉
     *
     * @param
     * @return : org.apache.shiro.session.mgt.SessionManager
     * @author : songhuanhao
     * @date : 2019/11/21 13:33
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        defaultWebSessionManager.setGlobalSessionTimeout(60*1000*60);
        //扫描session线程,负责清理超时会话
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //去掉URL中的JSESSIONID
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }

    /**
     * 功能描述:
     * 〈核心securityManager，用于管理所有的subject的所有操作〉
     *
     * @param sessionManager 2
     * @return : org.apache.shiro.mgt.SecurityManager
     * @author : songhuanhao
     * @date : 2020/5/25 13:59
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(SessionManager sessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置Realm
        defaultWebSecurityManager.setRealm(userRealm);
        //设置sessionManager
        defaultWebSecurityManager.setSessionManager(sessionManager);
        //cookie管理 记住我
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        Cookie cookie = rememberMeManager.getCookie();
        cookie.setMaxAge(60000);//秒
        cookie.setPath("/");
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);

        //缓存管理
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
    }

    /**
     * 功能描述:
     * 〈shiro拦截器配置〉
     *
     * @param securityManager 1
     * @return : org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author : songhuanhao
     * @date : 2020/5/25 14:00
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilter.setSecurityManager(securityManager);
        //登录
        shiroFilter.setLoginUrl("/login.html");
        //认证成功
        shiroFilter.setSuccessUrl("/index.html");
        //未授权
        shiroFilter.setUnauthorizedUrl("/unauthorized.html");

        //拦截路径的详细设置，LinkedHash里面有顺序，配置也需要有顺序，匿名访问放前面，范围大的放后面，不然全被拦截了，/**要放在最后
        //anon：它对应的过滤器里面是空的,什么都没做,后面的*表示参数 -->
        //authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        Map<String,String> filterMap = new LinkedHashMap<>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经实现了
        filterMap.put("/sys/logout", "logout");
        //filterMap.put("/public/**", "anon");
        //匿名访问过滤器,下方地址放行放行
        filterMap.put("/login.html", "anon");
        filterMap.put("/public/**", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/sys/getcaptcha", "anon");
        //认证访问过滤器，下方请求拦截
        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
    /**
     * 功能描述:
     * shiro1.4版本之后,在spring-bean config包下的配置有关于shiro的配置,但并未被springboot扫描,不能装配.
     * 如果要使用@Resource注入其他实例,必须将lifecycleBeanPostProcessor这个bean  static 或者移到新的配置文件.
     * 〈创建lifecycleBeanPostProcessor对象生命周期〉
     * 如果在@Configuration注解的类中，如果出现了诸如实现了BeanPostProcessor的子类实例化配置，那么此@Configuration中类的依赖注入可能会失效
     * Also, be particularly careful with BeanPostProcessor and BeanFactoryPostProcessor definitions through @Bean.
     * Those should usually be declared as static @Bean methods, not triggering the instantiation of their containing configuration class.
     * Otherwise, @Autowired and @Value do not work on the configuration class itself, since it is being created as a bean instance too early.
     * 另外，要特别注意通过@Bean定义的BeanPostProcessor和BeanFactoryPostProcessor。
     * 这些方法通常应该声明为静态@Bean方法，而不是触发其包含的配置类的实例化。
     * 否则，@Autowired和@Value不会在配置类本身上工作，因为它是作为bean实例创建的太早了
     * 如果存在BeanPostProcessor的子类会导致当前@Configuration类会提前实例化,那么@Resource注入的实例还没被注册到spring中,为null,出现问题.
     * @param
     * @return : org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @author : songhuanhao
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 功能描述:
     * 〈开启Shiro的注解,需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证〉
     * 〈配置以下两个bean即可实现此功能〉
     *
     * @param
     * @return : org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @author : songhuanhao
     * @date : 2019/11/21 14:44
     */
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        //代理层的对象的创建，cglib这种方式创建，controller类不是接口，要在类中使用需要用cglib方式创建代理对象
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 功能描述:
     * 〈配置shiro注解生效〉
     *
     * @param securityManager 1
     * @return : org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @author : songhuanhao
     * @date : 2019/11/21 14:47
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
