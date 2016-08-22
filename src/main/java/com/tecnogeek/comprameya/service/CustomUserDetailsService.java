/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.service;


import com.tecnogeek.comprameya.dto.SocialSecurityUserDTO;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jzelaya
 */

@Service
@Log
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public CustomUserDetailsService(){}
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository.findActiveUserByLogin(username);

        if(usuario==null)
        {
            throw new UsernameNotFoundException("Login "+username+" not found");
        }
        
          SocialSecurityUserDTO socialSecUser = SocialSecurityUserDTO.getBuilder()
                  .id(usuario.getId())
                  .nombre(usuario.getFkPersona().getNombre())
                  .apellido(usuario.getFkPersona().getApellido())
                  .fechaNacimiento(usuario.getFkPersona().getFechaNacimiento())
                  .genero(usuario.getFkPersona().getGenero())
                  .telefono(usuario.getFkPersona().getTelefono())
                  .celular(usuario.getFkPersona().getCelular())
                  .correo(usuario.getFkPersona().getCorreo())
                  .dui(usuario.getFkPersona().getDui())
                  .direccion(usuario.getFkPersona().getDireccion())
                  .login(usuario.getLogin())                  
                  .password(usuario.getPass())
                  .username(usuario.getLogin())
                  .puntaje(usuario.getPuntaje())
                  .role(usuario.getFkPerfil().getRole())
                  .rutaImagen(usuario.getRutaImagen())
                  .socialSignInProvider(usuario.getSignInProvider())
                  .build();
          
        return socialSecUser;        
        
    }
    
}
