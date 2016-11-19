/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.GlassFishRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 *
 * @author alexander
 */
@Configuration
@EnableWebSocketMessageBroker
//public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/cmpyWebSocket")
                .setHandshakeHandler(new DefaultHandshakeHandler(new GlassFishRequestUpgradeStrategy()))
                .setAllowedOrigins("*")
//                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .withSockJS()
                .setStreamBytesLimit(512 * 1024)
                .setHttpMessageCacheSize(1000)
                .setDisconnectDelay(30 * 1000);
//                .setClientLibraryUrl("http://localhost:8080/myapp/js/sockjs-client.js"); //for iframe-based clients like IE browsers
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/queue");
    }
    
}
