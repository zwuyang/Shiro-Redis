package hnu.boot.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ShiroConfiguration
 * @Description TODO
 * @Date 2019/10/23 15:32
 * @Created by yz
 */

@Configuration
public class ShiroConfiguration {


    //1.realm

    @Bean
    public CustomRealm getRealm(){
        return new CustomRealm();
    }

    //2.securityManagerment
    @Bean
    public SecurityManager getSecurityManager(CustomRealm realm){

        DefaultSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        //将自定义的sessionmanager注册到securitymanager中
        securityManager.setSessionManager(sessionManager());
        //将自定义的cachemanager注册到securitymanager
        securityManager.setCacheManager(redisCacheManager());

        return securityManager;
    }


    //3.shiro过滤器工厂
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/autherror?code=1");
        filterFactoryBean.setUnauthorizedUrl("/autherror?code=2");


        Map<String,String> filterMap=new HashMap<>();
        //当前请求可以匿名访问
        filterMap.put("/home","anon");
        //认证后才能访问
        filterMap.put("/user/**","authc");


        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return filterFactoryBean;
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;


    /**
     * redis控制器 操作redis
     * @return
     */

    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }


    /**
     * sessionDao
     * @return
     */

    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    /**sessionManager*/

    public DefaultWebSessionManager sessionManager(){
        CustomSessionManager sessionManager=new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());

        return sessionManager;
    }

    /**
     * 缓存管理器cacha manager (redis)
     * @return
     */

    public RedisCacheManager redisCacheManager(){
        RedisCacheManager cacheManager=new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }


//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
//        return new LifecycleBeanPostProcessor();
//    }


//    @DependsOn({"lifecycleBeanPostProcessor"})
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    //4.开启对注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}