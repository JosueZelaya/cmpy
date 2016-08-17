/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Publicacion;
import java.util.List;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author jzelaya
 */
public interface PublicacionRepository extends PagingAndSortingRepository<Publicacion, Long>, QueryDslPredicateExecutor<Publicacion> {
    
    List<Publicacion> findByTitulo(String titulo);
    
    List<Publicacion> findBySisActivo(String activo);

}
