/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.types.Predicate;
import com.tecnobitz.cmpy.entidad.Perfil;
import com.tecnobitz.cmpy.entidad.QPerfil;
import com.tecnobitz.cmpy.repositories.PerfilRepository;
import com.tecnobitz.cmpy.repositories.custom.PerfilRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class PerfilRepositoryImpl implements PerfilRepositoryCustom {
    
    @Autowired
    private PerfilRepository perfilRepository;

    private final QPerfil qPerfil = QPerfil.perfil;
    
    @Override
    public Perfil findByNombre(String name) {
        Predicate byNombre = qPerfil.nombre.eq(name);
        return perfilRepository.findOne(byNombre);
    }
    
}
