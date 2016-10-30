/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 *
 * @author jzelaya
 */

@Configuration
@ComponentScan(basePackages={"com.tecnogeek.comprameya.config","com.tecnogeek.comprameya.service"})
@PropertySource(value = {"/WEB-INF/application.properties"})
public class AppConfig {
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 
        messageSource.setBasename("i18n/messages");
//        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
 
        return messageSource;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    
}
