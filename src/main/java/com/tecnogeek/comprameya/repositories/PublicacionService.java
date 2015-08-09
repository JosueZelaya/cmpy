/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Publicacion;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author jzelaya
 */
public interface PublicacionService extends PagingAndSortingRepository<Publicacion, Long> {
    
    List<Publicacion> findByTitulo(String titulo);
    
    List<Publicacion> findBySisActivo(String activo);
    
    @Query("select p from Publicacion p where p.fkTipoPublicacion='1'")
    List<Publicacion> getPublicacionesPagadas();
    
    @Query("select p from Publicacion p where p.fkTipoPublicacion='1'")
    List<Publicacion> getPublicacionesPagadas(Pageable pageable);        
    
    @Query("select p from Publicacion p where p.fkTipoPublicacion='2'")
    List<Publicacion> getPublicacionesGratis();
    
    @Query("select p from Publicacion p where p.fkTipoPublicacion='2'")
    List<Publicacion> getPublicacionesGratis(Pageable pageable);        
    
    @Query("select count(p) from Publicacion p where p.fkTipoPublicacion='1'")
    Integer getTotalPublicacionesPagadas();
    
    @Query("select count(p) from Publicacion p where p.fkTipoPublicacion='2'")
    Integer getTotalPublicacionesGratis();

}
