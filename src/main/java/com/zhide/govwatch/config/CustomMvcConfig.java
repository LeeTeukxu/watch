package com.zhide.govwatch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CustomMvcConfig implements WebMvcConfigurer {

    @Autowired
    private com.zhide.govwatch.config.CompanyInterceptor companyInterceptor;
    @Autowired
    private IndexActionInterceptor indexInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(companyInterceptor);
        registry.addInterceptor(indexInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:d:/Upload/Images/");
        registry.addResourceHandler("/appImages/**").addResourceLocations("classpath:/static/images/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addRedirectViewController("/", "/index");
    }
}
