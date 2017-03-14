/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnobitz.core.entidad.QRecurso;
import com.tecnobitz.core.entidad.Recurso;
import com.tecnogeek.comprameya.repositories.RecursoRepository;
import com.tecnogeek.comprameya.repositories.custom.RecursoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author alexander
 */
public class RecursoRepositoryImpl implements RecursoRepositoryCustom{

    @Autowired
    private RecursoRepository recursoRepository;
    
    private QRecurso qRecurso = QRecurso.recurso;
    
    @Override
    public Iterable<Recurso> getRecursos(Long publicacionId, int page, int itemsByPage) {
        BooleanExpression byPublicacion = qRecurso.publicacion.id.eq(publicacionId);
        return recursoRepository.findAll(byPublicacion, new PageRequest(page, itemsByPage)).getContent();
    }

    
    
}
