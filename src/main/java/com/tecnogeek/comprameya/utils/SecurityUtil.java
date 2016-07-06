/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.utils;

import com.tecnogeek.comprameya.dto.SocialSecurityUser;
import com.tecnogeek.comprameya.entidad.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author alexander
 */
public class SecurityUtil {
 
    public static void logInUser(Usuario user) {
        SocialSecurityUser userDetails = SocialSecurityUser.getBuilder()
                .firstName(user.getFkPersona().getNombre())
                .id(user.getId())
                .lastName(user.getFkPersona().getApellido())
                .password(user.getPass())
                .role(user.getFkPerfil().getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getLogin())
                .build();
 
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
}
