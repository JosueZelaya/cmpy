/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.QSuscriptor;
import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.SuscriptorRepository;

/**
 *
 * @author genaro
 */

@Service
public class SuscriptorService {
    
    public SuscriptorService(){}
    
    @Autowired
    SuscriptorRepository suscriptorRepository;
    
    private final QSuscriptor qSuscriptor = QSuscriptor.suscriptor;
    
    public Iterable<Suscriptor> getSuscriptor(Usuario usuario)
    {
         Predicate bySuscriptor = qSuscriptor.fkUsuarioSuscriptor.eq(usuario);
         return suscriptorRepository.findAll(bySuscriptor);
    }
    
    public Iterable<Suscriptor> getProveedor(Usuario usuario)
    {
         Predicate byProveedor = qSuscriptor.fkUsuarioProveedor.eq(usuario);
         return suscriptorRepository.findAll(byProveedor);
    }
    
    public String setSuscriptor(Suscriptor sus)
    {
        suscriptorRepository.save(sus);
        return null;
    }
    
}
