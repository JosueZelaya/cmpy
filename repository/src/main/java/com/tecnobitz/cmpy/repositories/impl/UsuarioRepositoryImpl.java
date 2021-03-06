/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnobitz.cmpy.entidad.QUsuario;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.repositories.PerfilRepository;
import com.tecnobitz.cmpy.repositories.PersonaRepository;
import com.tecnobitz.cmpy.repositories.UsuarioRepository;
import com.tecnobitz.cmpy.repositories.custom.UsuarioRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

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
    
}
