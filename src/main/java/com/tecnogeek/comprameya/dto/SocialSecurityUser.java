/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import com.tecnogeek.comprameya.enums.Role;
import com.tecnogeek.comprameya.enums.SocialMediaService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

/**
 *
 * @author josue.zelaya
 */

@Getter
@Setter
public class SocialSecurityUser extends SocialUser {

    private Long id;
    
    private Role role;
    
    private SocialMediaService socialSignInProvider;
    
    private int puntaje;
    
    private String login;
    
    private String firstName;
    
    private String lastName;
    
    public SocialSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder {
 
        private Long id;
 
        private String username;
 
        private String password;
        
        private String firstName;
        
        private String lastName;
 
        private Role role;
 
        private SocialMediaService socialSignInProvider;
 
        private Set<GrantedAuthority> authorities;
 
        public Builder() {
            this.authorities = new HashSet<>();
        }
 
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
 
        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }
 
            this.password = password;
            return this;
        }
 
        public Builder role(Role role) {
            this.role = role;
 
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);
 
            return this;
        }
 
        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }
 
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
 
        public SocialSecurityUser build() {
            SocialSecurityUser user = new SocialSecurityUser(username, password, authorities);
 
            user.id = id;            
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;
            user.login = username;            
            user.firstName = firstName;
            user.lastName = lastName;
            return user;
        }
    }
    
}
