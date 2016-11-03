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
    
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage);
    
    public Iterable<Publicacion> getPublicacionesGratis(int page, int itemsByPage);
    
    public long getTotalPublicacionesPagadas();
    
    public long getTotalPublicacionesGratis();
    
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage, long categoria_id);
    
    public Iterable<Publicacion> getPublicacionesGratisSubSubCat(int page, int itemsByPage, long categoria_id);
    
    public Iterable<Publicacion> getPublicacionesGratisSubCat(int page, int itemsByPage, long categoria_id);
    
    public Iterable<Publicacion> getPublicacionesGratisCat(int page, int itemsByPage, long categoria_id);
    
     public Iterable<Publicacion> getPublicacionesGratisByMatch(int page, int itemsByPage, String match);
    
}
