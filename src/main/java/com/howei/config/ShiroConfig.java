package com.howei.config;

import com.howei.realm.LoginRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${shiro.loginUrl}")
    private String masterLoginUrl;

    @Value("${shiro.jessionid}")
    private String jessionId;

    //将验证方式加入容器
    @Bean
    @DependsOn(value="lifecycleBeanPostProcessor")
    public LoginRealm loginRealm(){
        LoginRealm userRealm = new LoginRealm();
        //userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    /**
     * FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    /**
     * @see ShiroFilterFactoryBean
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new MShiroFilterFactoryBean(); //指向自定义过滤器，自定义过滤器对js/css等忽略
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl(masterLoginUrl);
        Map<String, Filter>filters = new LinkedHashMap<>();
        filters.put("anon", new AnonymousFilter());
        bean.setFilters(filters);
        //shiro配置过滤规则少量的话可以用hashMap,数量多了要用LinkedHashMap,保证有序，原因未知
        Map<String,String> map1=new HashMap<>();
        map1.put("/logout","logout");
        //首页
        bean.setSuccessUrl("/home");
        bean.setFilterChainDefinitionMap(map1);
        return bean;
    }


    @Bean
    public RedisSessionDao getRedisSessionDao(){
        return new RedisSessionDao();
    }
    /**
     * @see DefaultWebSessionManager
     * @return
     */
    @Bean(name="sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(RedisSessionDao redisSessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(900000); //15分钟
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionDAO(redisSessionDao);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(getSessionIdCookie());
        sessionManager.setSessionIdCookie(new SimpleCookie("sessionUser"));
        return sessionManager;
    }

    /**
     * @see org.apache.shiro.mgt.SecurityManager
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(LoginRealm userRealm, RedisSessionDao redisSessionDao) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        manager.setSessionManager(defaultWebSessionManager(redisSessionDao));
        return manager;
    }

    /**
     * 给shiro的sessionId默认的JSSESSIONID名字改掉
     * @return
     */
    @Bean(name="sessionIdCookie")
    public SimpleCookie getSessionIdCookie(){
        SimpleCookie simpleCookie = new SimpleCookie(jessionId);
        return simpleCookie;
    }

    /**
     * 该类如果不设置为static，@Value注解就无效，原因未知
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
