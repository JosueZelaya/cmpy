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
import org.springframework.beans.factory.annotation.Autowired;
import com.tecnogeek.comprameya.repositories.SuscripcionUsuarioRepository;
import com.tecnogeek.comprameya.repositories.custom.SuscripcionUsuarioRepositoryCustom;

/**
 *
 * @author alexander
 */
public class SuscripcionUsuarioRepositoryImpl implements SuscripcionUsuarioRepositoryCustom {
    
    @Autowired
    SuscripcionUsuarioRepository suscriptorRepository;
    
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
