/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.QSuscripcionUsuario;
import com.tecnogeek.comprameya.entidad.SuscripcionUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.SuscriptorRepository;
import com.tecnogeek.comprameya.repositories.custom.SuscriptorRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class SuscriptorRepositoryImpl implements SuscriptorRepositoryCustom {
    
    @Autowired
    SuscriptorRepository suscriptorRepository;
    
    private final QSuscripcionUsuario qSuscripcionUsuario = QSuscripcionUsuario.suscripcionUsuario;
    
    @Override
    public Iterable<SuscripcionUsuario> getSuscriptor(Usuario usuario)
    {
         Predicate bySuscriptor = qSuscripcionUsuario.usuarioSuscriptor.eq(usuario);
         return suscriptorRepository.findAll(bySuscriptor);
    }
    
    @Override
    public Iterable<SuscripcionUsuario> getProveedor(Usuario usuario)
    {
         Predicate byProveedor = qSuscripcionUsuario.usuarioProveedor.eq(usuario);
         return suscriptorRepository.findAll(byProveedor);
    }
    
    @Override
    public String setSuscriptor(SuscripcionUsuario sus)
    {
        suscriptorRepository.save(sus);
        return null;
    }
    
}
