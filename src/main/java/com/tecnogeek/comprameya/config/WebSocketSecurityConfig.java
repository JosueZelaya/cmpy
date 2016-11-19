/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 *
 * @author alexander
 */
@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
            .simpMessageDestMatchers("/queue/**","/topic/**").denyAll()
            .simpSubscribeDestMatchers("/queue/**/*-user*","/topic/**/*-user*").denyAll()
            .anyMessage().authenticated();
//            .anyMessage().hasRole("USER");

    }
    
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
