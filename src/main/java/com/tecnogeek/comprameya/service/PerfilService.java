/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.QPerfil;
import com.tecnogeek.comprameya.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander
 */

@Service
public class PerfilService {
    
    @Autowired
    private PerfilRepository perfilRepository;

    private final QPerfil qPerfil = QPerfil.perfil;
    
    public Perfil findByNombre(String name) {
        Predicate byNombre = qPerfil.nombre.eq(name);
        return perfilRepository.findOne(byNombre);
    }
    
}
