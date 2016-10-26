/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.PerfilRepository;
import com.tecnogeek.comprameya.repositories.PersonaRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import com.tecnogeek.comprameya.repositories.custom.UsuarioRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author alexander
 */
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    private PerfilRepository perfilRepository;
    
    private final QUsuario qUsuario = QUsuario.usuario;
    
    @Override
    public Usuario findActiveUserByLogin(String login) {
        BooleanExpression byLogin = qUsuario.login.eq(login);
        BooleanExpression isActivo = qUsuario.sisActivo.eq(true);

        return repository.findOne(byLogin.and(isActivo));
    }

    @Override
    public Usuario getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return repository.findActiveUserByLogin(userName);
    }

    
    
}
