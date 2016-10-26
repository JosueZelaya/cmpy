/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QComentario;
import com.tecnogeek.comprameya.repositories.ComentarioRepository;
import com.tecnogeek.comprameya.repositories.custom.ComentarioRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author alexander
 */
public class ComentarioRepositoryImpl implements ComentarioRepositoryCustom{
    
    @Autowired
    ComentarioRepository comentarioRepository;
    
    private final QComentario qComentario = QComentario.comentario;
    
    @Override
    public Iterable<Comentario> getComentario(Publicacion publicacion)
    {
        Predicate byPublicacion = qComentario.fkPublicacion.eq(publicacion);
        return comentarioRepository.findAll(byPublicacion);
    }

    @Override
    public Iterable<Comentario> getComentario(long publicacionId) {
        Predicate byPublicacion = qComentario.fkPublicacion.id.eq(publicacionId);
        return comentarioRepository.findAll(byPublicacion);
    }

    @Override
    public Iterable<Comentario> getComentarios(long publicacionId, int page, int itemsByPage) {
        BooleanExpression byPublicacion = qComentario.fkPublicacion.id.eq(publicacionId);
        return comentarioRepository.findAll(byPublicacion, new PageRequest(page, itemsByPage)).getContent();
    }
    
}
