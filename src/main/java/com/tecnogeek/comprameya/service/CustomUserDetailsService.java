/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.service;


import com.tecnogeek.comprameya.dto.SocialSecurityUser;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Usuario;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.PerfilRepository;
import com.tecnogeek.comprameya.repositories.UserRepository;

/**
 *
 * @author jzelaya
 */

@Service
@Log
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    
    public CustomUserDetailsService(){}
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = userRepository.findByLogin(username);
        Perfil perfil = perfilRepository.getPerfilUsuario(username);        
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
