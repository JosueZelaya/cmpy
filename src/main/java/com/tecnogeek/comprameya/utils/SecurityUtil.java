/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.utils;

import com.tecnogeek.comprameya.dto.SocialSecurityUserDTO;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.enums.Role;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author alexander
 */
public class SecurityUtil {
 
    public static void logInUser(Usuario user) {
        
        Role role = user.getFkPerfil().getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);
        if(user.getPass() == null){
            user.setPass("SocialUser");
        }
        SocialSecurityUserDTO socialUser = new SocialSecurityUserDTO(user.getLogin(), user.getPass(), authorities);
        socialUser.setSocialSignInProvider(user.getSignInProvider());
        socialUser.setApellido(user.getFkPersona().getApellido());
        socialUser.setNombre(user.getFkPersona().getNombre());
        socialUser.setRole(role);
        socialUser.setLogin(user.getLogin());
        socialUser.setCorreo(user.getFkPersona().getCorreo());        
        socialUser.setRutaImagen(user.getRutaImagen());
        socialUser.setId(user.getId());
        socialUser.setUsername(user.getLogin());
        socialUser.setPassword(user.getPass());
        
//        SocialSecurityUser userDetails = SocialSecurityUser.getBuilder()
//                .firstName(user.getFkPersona().getNombre())
//                .id(user.getId())
//                .lastName(user.getFkPersona().getApellido())
//                .password(user.getPass())
//                .role(user.getFkPerfil().getRole())
//                .socialSignInProvider(user.getSignInProvider())
//                .username(user.getLogin())
//                .build();
 
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser, null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
}
