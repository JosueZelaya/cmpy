/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author jzelaya
 */

@Configuration
@ComponentScan(basePackages={"com.tecnogeek.comprameya"})
@PropertySource(value = {"/WEB-INF/application.properties"})
public class AppConfig {
    
//    @Autowired
//    private Environment env;
    
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }
    
}
