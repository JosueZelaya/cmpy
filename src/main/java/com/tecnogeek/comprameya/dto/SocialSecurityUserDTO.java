/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import com.tecnogeek.comprameya.enums.Role;
import com.tecnogeek.comprameya.enums.SocialMediaService;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

/**
 *
 * @author alexander
 */

@Getter
@Setter
public class SocialSecurityUserDTO extends SocialUser {
    
    private Long id;
    
    private String nombre;
    
    private String apellido;
    
    private Date fechaNacimiento;
    
    private Boolean genero;
    
    private BigInteger telefono;
    
    private BigInteger celular;
    
    private String correo;
    
    private BigInteger dui;
    
    private String direccion;       
    
    private String login;
    
    private String password;
    
    private String username;

    private int puntaje;
    
    private Role role;
    
    private String rutaImagen;
    
    private SocialMediaService socialSignInProvider;
    
    public SocialSecurityUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);        
    }
    
    public void setPassword(String password){
        if (password == null) {
            password = "SocialUser";
        }
        this.password = password;
    }
    
}
