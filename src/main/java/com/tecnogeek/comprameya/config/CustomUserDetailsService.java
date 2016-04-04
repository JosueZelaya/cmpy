/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;


import com.tecnogeek.comprameya.config.SocialSecurityUser;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.PerfilService;
import com.tecnogeek.comprameya.repositories.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author jzelaya
 */

@Service
@Log
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PerfilService perfilService;
    
    public CustomUserDetailsService(){}
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = userService.findByLogin(username);
        Perfil perfil = perfilService.getPerfilUsuario(username);        
        if(usuario==null)
        {
            throw new UsernameNotFoundException("Login "+username+" not found");
        }
        usuario.setFkPerfil(perfil);    
        
        SocialSecurityUser socialSecUser = SocialSecurityUser.getBuilder()                
                .id(usuario.getUsuarioId())                
                .password(usuario.getPass())
                .role(usuario.getFkPerfil().getRole())                
                .username(usuario.getLogin())
                .socialSignInProvider(usuario.getSignInProvider())
                .build();
        
        return socialSecUser;        
        
    }
    
}
