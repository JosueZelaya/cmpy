/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;

import com.tecnobitz.core.entidad.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author jzelaya
 */
@Deprecated
public class SecurityUser_original extends Usuario implements UserDetails{

    private static final long serialVersionUID = 1L;
    
    public SecurityUser_original(Usuario user)
    {
        if(user!=null)
        {
            this.setId(user.getId());
            this.setLogin(user.getLogin());
            this.setPass(user.getPass());
            this.setPuntaje(user.getPuntaje());             
//            this.setSisSesionActiva(user.isSisSesionActiva());
//            this.setSisActivo(user.isSisActivo());            
            this.setPerfil(user.getPerfil());
        }         
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<GrantedAuthority> authorities = new ArrayList<>(); 
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(super.getPerfil().getNombre()); 
        
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPass();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
