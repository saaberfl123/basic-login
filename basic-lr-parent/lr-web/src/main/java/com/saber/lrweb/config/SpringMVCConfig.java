package com.saber.lrweb.config;

import com.saber.lrweb.interceptor.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc//开启注解支持
@ComponentScan("com.saber.lrweb.controller")//父容器扫描controller包
public class SpringMVCConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //别忘了在这添加个拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
    }
}
