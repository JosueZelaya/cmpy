/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QUbicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.repositories.UbicacionRepository;
import com.tecnogeek.comprameya.repositories.custom.UbicacionRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class UbicacionRepositoryImpl implements UbicacionRepositoryCustom{
    
    @Autowired
    UbicacionRepository ubicacionRepository;
    
    private final QUbicacion qUbicacion = QUbicacion.ubicacion;
    
    @Override
    public Iterable<Ubicacion> getUbicacionPublicacion(Publicacion publicacion)
    {
        Predicate byPublicacion = qUbicacion.fkPublicacion.eq(publicacion);
        return ubicacionRepository.findAll(byPublicacion);
    }
    
}
