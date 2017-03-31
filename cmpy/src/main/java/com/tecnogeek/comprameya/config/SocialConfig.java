/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.config.support.FacebookApiHelper;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

/**
 *
 * @author josue.zelaya
 */

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer{

    @Autowired
    private DataSource dataSource;
    
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfc, Environment e) {
        
        final FacebookConnectionFactory fbcf = new FacebookConnectionFactory(
                e.getProperty("facebook.app.id"),
                e.getProperty("facebook.app.secret")
        );
        fbcf.setScope("public_profile,email");      
        
        cfc.addConnectionFactory(new TwitterConnectionFactory(
                e.getProperty("twitter.consumer.key"),
                e.getProperty("twitter.consumer.secret")
        ));
        
        cfc.addConnectionFactory(fbcf);
        
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    //LOS DETALLES DE LA AUTORIZACION ENTRE EL SaaS API y nuestra aplicación están en texto plano
    //Favor corregir esto antes de pasar a producción, cambiar el Encrytors.noOpText() por otro.
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator cfl) {
        return new JdbcUsersConnectionRepository(
                dataSource,
                cfl,
                Encryptors.noOpText() 
        );
    }
 
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }    
    
    @Bean
    public FacebookApiHelper facebookApiHelper(ConnectionFactoryLocator cfl){
        return new FacebookApiHelper(getUsersConnectionRepository(cfl), getUserIdSource());
    }
    
}
