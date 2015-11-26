/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import com.tecnogeek.comprameya.managers.ManejadorCategoria;
import com.tecnogeek.comprameya.managers.ManejadorDestinatario;
import com.tecnogeek.comprameya.managers.ManejadorMensaje;
import com.tecnogeek.comprameya.managers.ManejadorPublicacion;
import com.tecnogeek.comprameya.managers.ManejadorUbicacion;
import com.tecnogeek.comprameya.managers.ManejadorUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author jzelaya
 */

@Configuration
@ComponentScan(basePackages={"com.tecnogeek.comprameya.config"})
@PropertySource(value = {"/WEB-INF/application.properties"})
public class AppConfig {
    
//    @Autowired
//    private Environment env;
    
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }
    
    
    @Bean
    public ManejadorPublicacion manejadorPublicacion(){
        return new ManejadorPublicacion();
    }
    
    @Bean
    public ManejadorCategoria manejadorCategoria(){
        return new ManejadorCategoria();
    }
    
    @Bean
        public ManejadorUbicacion manejadorUbicacion(){
        return new ManejadorUbicacion();
    }
        
        
    @Bean
        public ManejadorUsuario manejadorUsuario(){
        return new ManejadorUsuario();
    }

    @Bean
        public ManejadorDestinatario manejadorDestinatario(){
        return new ManejadorDestinatario();
    }
        
    @Bean
        public ManejadorMensaje manejadorMensaje(){
        return new ManejadorMensaje();
    }
    
}
