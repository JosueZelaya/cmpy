/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import com.tecnogeek.comprameya.repositories.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander
 */

@Service
public class RecursoService extends BaseService<Recurso, Long>{

    @Autowired
    RecursoRepository recursoRepository;
    
    @Override
    public BaseRepository<Recurso, Long> getRepository() {
        return recursoRepository;
    }
    
    public Iterable<Recurso> getRecursos(Long idPublicacion, int page, int recursosByPage){
        return recursoRepository.getRecursos(idPublicacion, page, recursosByPage);
    }
    
}
