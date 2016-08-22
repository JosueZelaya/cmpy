/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.QSuscriptor;
import com.tecnogeek.comprameya.entidad.Suscriptor;
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
    
    private final QSuscriptor qSuscriptor = QSuscriptor.suscriptor;
    
    @Override
    public Iterable<Suscriptor> getSuscriptor(Usuario usuario)
    {
         Predicate bySuscriptor = qSuscriptor.fkUsuarioSuscriptor.eq(usuario);
         return suscriptorRepository.findAll(bySuscriptor);
    }
    
    @Override
    public Iterable<Suscriptor> getProveedor(Usuario usuario)
    {
         Predicate byProveedor = qSuscriptor.fkUsuarioProveedor.eq(usuario);
         return suscriptorRepository.findAll(byProveedor);
    }
    
    @Override
    public String setSuscriptor(Suscriptor sus)
    {
        suscriptorRepository.save(sus);
        return null;
    }
    
}
