/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;


import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.PerfilService;
import com.tecnogeek.comprameya.repositories.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author jzelaya
 */

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PerfilService perfilService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Autenticando usuario: "+username);
        Usuario usuario = userService.findByLogin(username);
        Perfil perfil = perfilService.getPerfilUsuario(username);
        usuario.setFkPerfil(perfil);
        if(usuario==null)
        {
            System.out.println("No se encontró al usuario: "+username);
            throw new UsernameNotFoundException("Login "+username+" not found");
        }
        System.out.println("login: "+usuario.getLogin()+" clave: "+usuario.getPass());
        return new SecurityUser(usuario);
    }
    
}
