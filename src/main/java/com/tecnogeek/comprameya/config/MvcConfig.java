/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author jzelaya
 */

@Configuration
@ComponentScan(basePackages = { "com.tecnogeek.comprameya.controlador"})
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {    
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/login").setViewName("login");
    }
    
    @Bean
    public ViewResolver resolver(){
        InternalResourceViewResolver url = new InternalResourceViewResolver();
        url.setPrefix("/vista/");
        url.setSuffix(".jsp");
        url.setContentType("text/html;charset=UTF-8");
        return url;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String userDir = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");                
        userDir = "file:"+userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator;
        System.out.println("PATH TO IMAGES: "+userDir);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");               
        registry.addResourceHandler("/images/**").addResourceLocations(userDir);
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
      return new StandardServletMultipartResolver();
    }
    
}
