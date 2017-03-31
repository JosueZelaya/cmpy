/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author josue.zelaya
 */

@Service
public class SocialCustomUserDetailsService implements SocialUserDetailsService{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    public SocialCustomUserDetailsService(){}
    
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
        return (SocialUserDetails) userDetails;
    }    
    
}
