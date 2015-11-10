/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author jzelaya
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
    
    @Override
    protected Class<?>[] getRootConfigClasses() {        
          return new Class[] {AppConfig.class, SecurityConfig.class};
//        return new Class[] {AppConfig.class};
//        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
    
    @Override
    protected Filter[] getServletFilters() // Hacer que spring security filtre las url
    {
        return new Filter[]{new DelegatingFilterProxy("springSecurityFilterChain"), new OpenEntityManagerInViewFilter()};
    }
    
    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);

        servletContext.addListener(new HttpSessionEventPublisher());

    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        String userDir = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");        
        userDir += fileSeparator+"src"+fileSeparator+"images"+fileSeparator;        
        Path directory = Paths.get(userDir);                
        
        MultipartConfigElement multipartConfigElement = 
            new MultipartConfigElement(directory.toString(),
                maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }
    
}
