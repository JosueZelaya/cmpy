/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;


import javax.servlet.Filter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author jzelaya
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

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
    
}
