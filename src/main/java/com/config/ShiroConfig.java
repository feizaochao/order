package com.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.modules.sys.oauth2.OAuth2Filter;
import com.modules.sys.oauth2.OAuth2Realm;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-20 18:33
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/druid/**", "anon");
//        filterMap.put("/api/**", "anon");
//        filterMap.put("/sys/login", "anon");
//        filterMap.put("/**/*.css", "anon");
//        filterMap.put("/**/*.js", "anon");
//        filterMap.put("/**/*.html", "anon");
//        filterMap.put("/fonts/**", "anon");
//        filterMap.put("/plugins/**", "anon");
//        filterMap.put("/swagger/**", "anon");
//        filterMap.put("/favicon.ico", "anon");
//        filterMap.put("/captcha.jpg", "anon");
//        filterMap.put("/", "anon");
//        filterMap.put("/**", "oauth2");
        filterMap.put("/login", "anon");
        filterMap.put("/wxLogin", "anon");
        filterMap.put("/order_mapping/add", "oauth2");
        filterMap.put("/order_mapping/edit", "oauth2");
        filterMap.put("/order_mapping/delete", "oauth2");
        filterMap.put("/order_mapping/list", "oauth2");
        filterMap.put("/order_mapping/one", "oauth2");
        filterMap.put("/customer/add", "oauth2");
        filterMap.put("/customer/edit", "oauth2");
        filterMap.put("/customer/delete", "oauth2");
        filterMap.put("/customer/list", "oauth2");
        filterMap.put("/customer/one", "oauth2");
        filterMap.put("/contract/add", "oauth2");
        filterMap.put("/contract/edit", "oauth2");
        filterMap.put("/contract/delete", "oauth2");
        filterMap.put("/contract/list", "oauth2");
        filterMap.put("/contract/one", "oauth2");
        filterMap.put("/user/**", "oauth2");
        filterMap.put("/role/**", "oauth2");
        filterMap.put("/menu/**", "oauth2");
        filterMap.put("/dict/**", "oauth2");
        filterMap.put("/area/**", "oauth2");

        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
