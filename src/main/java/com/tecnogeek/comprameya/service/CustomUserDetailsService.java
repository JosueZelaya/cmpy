/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.service;


import com.tecnogeek.comprameya.dto.SocialSecurityUserDTO;
import com.tecnogeek.comprameya.entidad.Usuario;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
//    @Autowired
//    private PerfilRepository perfilRepository;
    
    public CustomUserDetailsService(){}
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = userRepository.findByLogin(username);
//        Perfil perfil = perfilRepository.getPerfilUsuario(username);        
        if(usuario==null)
        {
            throw new UsernameNotFoundException("Login "+username+" not found");
        }
//        usuario.setFkPerfil(perfil);    
//        Role role = perfil.getRole();
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(authority);
//        SocialSecurityUserDTO socialUser = new SocialSecurityUserDTO(username, usuario.getPass(), authorities);
//        socialUser.setSocialSignInProvider(usuario.getSignInProvider());
//        socialUser.setApellido(usuario.getFkPersona().getApellido());
//        socialUser.setNombre(usuario.getFkPersona().getNombre());
//        socialUser.setRole(role);
//        socialUser.setLogin(usuario.getLogin());
//        socialUser.setCorreo(usuario.getFkPersona().getCorreo());        
//        socialUser.setRutaImagen(usuario.getRutaImagen());
//        socialUser.setId(usuario.getId());
//        socialUser.setUsername(usuario.getLogin());
//        socialUser.setPassword(usuario.getPass());
        
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

//        SocialSecurityUser socialSecUser = SocialSecurityUser.getBuilder()                
//                .id(usuario.getId())                
//                .password(usuario.getPass())
//                .role(usuario.getFkPerfil().getRole())                
//                .username(usuario.getLogin())
//                .socialSignInProvider(usuario.getSignInProvider())
//                .build();
        
        return socialSecUser;        
        
    }
    
}
