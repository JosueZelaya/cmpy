/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;


import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author genaro
 */
public interface UbicacionService extends CrudRepository<Ubicacion,Long> {
    
    
    List<Ubicacion> findByfkPublicacion(Publicacion publicacion);
    
}
