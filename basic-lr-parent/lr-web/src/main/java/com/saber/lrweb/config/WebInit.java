package com.saber.lrweb.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer
{


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVCConfig.class, BeansConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        //编码过滤器 强制UTF-8
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[] {encodingFilter};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
