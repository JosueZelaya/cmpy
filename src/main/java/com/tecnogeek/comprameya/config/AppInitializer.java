/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import com.github.greengerong.PreRenderSEOFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author jzelaya
 */

@Import({SecurityConfig.class, WebSocketConfig.class})
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        com.github.greengerong.PreRenderSEOFilter seoFilter = new com.github.greengerong.PreRenderSEOFilter();
        FilterRegistration.Dynamic filter =  servletContext.addFilter("prerender", seoFilter);
        //filter.setInitParameter("prerenderToken", "bWTX3wnGG43lSldWSr09");
        filter.setInitParameter("prerenderServiceUrl", "https://comprameya.com:3000/");
        filter.addMappingForUrlPatterns(null , true, "/");        
        super.onStartup(servletContext);
    }
    
    @Override
    protected Class<?>[] getRootConfigClasses() {        
          return new Class[] {AppConfig.class, SecurityConfig.class, WebSocketConfig.class};
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
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        
        return new Filter[]
        { 
            characterEncodingFilter,
            new DelegatingFilterProxy("springSecurityFilterChain"), 
            new OpenEntityManagerInViewFilter()
        };
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
