/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QUbicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.UbicacionRepository;

/**
 *
 * @author genaro
 */

@Service
public class UbicacionService {
    
    public UbicacionService(){}
    
    @Autowired
    UbicacionRepository ubicacionRepository;
    
    private final QUbicacion qUbicacion = QUbicacion.ubicacion;
    
    public Iterable<Ubicacion> getUbicacionPublicacion(Publicacion publicacion)
    {
        Predicate byPublicacion = qUbicacion.fkPublicacion.eq(publicacion);
        return ubicacionRepository.findAll(byPublicacion);
    }
    
    public void setGuardarTodos(Iterable<Ubicacion> lista)
    {
        ubicacionRepository.save(lista);
    }
    
    public void setGuardar(Ubicacion ubicacion)
    {
        ubicacionRepository.save(ubicacion);
    }
    
    public void setEliminarTodo(Iterable<Ubicacion> lista)
    {
        ubicacionRepository.delete(lista);
    }
    
    public void setEliminar(Ubicacion ubicacion)
    {
        ubicacionRepository.delete(ubicacion);
    }
    

    
}
