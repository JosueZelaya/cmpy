/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnobitz.cmpy.entidad.BaseEntity;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.repositories.BaseRepository;
import com.tecnobitz.cmpy.repositories.UsuarioRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jzelaya
 * @param <T>
 * @param <ID>
 */
public abstract class BaseService <T extends BaseEntity<ID>,  ID extends Serializable> {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public abstract BaseRepository<T, ID> getRepository();
    
    public Usuario getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return usuarioRepository.findActiveUserByLogin(userName);
    }
    
}
