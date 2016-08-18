/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Publicacion;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author jzelaya
 */

public interface PublicacionRepositoryCustom {
    
    public Publicacion getPublicacion(long publicacion_id);
    
    public Iterable<Publicacion> getPublicacionesPagadas();
    
    public Iterable<Publicacion> getPublicacionesGratis();
    
    public Iterable<Publicacion> getPublicacionesPagadas(PageRequest pageRequest);
    
    public Iterable<Publicacion> getPublicacionesGratis(PageRequest pageRequest);
    
    public long getTotalPublicacionesPagadas();
    
    public long getTotalPublicacionesGratis();
    
}
