/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import com.tecnobitz.cmpy.enums.Role;
import com.tecnobitz.cmpy.enums.SocialMediaService;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private int puntaje;
    
    private Role role;
    
    private String rutaImagen;
    
    private SocialMediaService socialSignInProvider;
    
    public SocialSecurityUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);        
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder {
        
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
 
        private Set<GrantedAuthority> authorities;
        
        public Builder() {
            this.authorities = new HashSet<>();
        }
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }
        
        public Builder fechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }
        
        public Builder genero(Boolean genero) {
            this.genero = genero;
            return this;
        }
        
        public Builder telefono(BigInteger telefono) {
            this.telefono = telefono;
            return this;
        }
        
        public Builder celular(BigInteger celular) {
            this.celular = celular;
            return this;
        }
        
        public Builder correo(String correo) {
            this.correo = correo;
            return this;
        }
        
        public Builder dui(BigInteger dui) {
            this.dui = dui;
            return this;
        }
 
        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }
        
        public Builder login(String login) {
            this.login = login;
            return this;
        }
        
        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }
 
            this.password = password;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder puntaje(int puntaje) {
            this.puntaje = puntaje;
            return this;
        }
        
        public Builder role(Role role) {
            this.role = role;
 
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            this.authorities.add(authority);
 
            return this;
        }
        
        public Builder rutaImagen(String rutaImagen) {
            this.rutaImagen = rutaImagen;
            return this;
        }
        
        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }
        
        public SocialSecurityUserDTO build() {
            SocialSecurityUserDTO user = new SocialSecurityUserDTO(username, password, authorities);
            user.id = id;
            user.nombre = nombre;
            user.apellido = apellido;
            user.fechaNacimiento = fechaNacimiento;
            user.genero = genero;
            user.telefono = telefono;
            user.celular = celular;
            user.correo = correo;
            user.dui = dui;
            user.direccion = direccion;
            user.login = login;
            user.puntaje = puntaje;
            user.role = role;
            user.rutaImagen = rutaImagen;
            user.socialSignInProvider = socialSignInProvider;
            return user;
        }
        
    }
    
}
