/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.tecnobitz.cmpy.entidad.Comentario;
import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnobitz.cmpy.entidad.QComentario;
import com.tecnobitz.cmpy.repositories.ComentarioRepository;
import com.tecnobitz.cmpy.repositories.custom.ComentarioRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QSort;

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
        Predicate byPublicacion = qComentario.publicacion.eq(publicacion);
        return comentarioRepository.findAll(byPublicacion);
    }

    @Override
    public Iterable<Comentario> getComentario(long publicacionId) {
        Predicate byPublicacion = qComentario.publicacion.id.eq(publicacionId);
        return comentarioRepository.findAll(byPublicacion);
    }

    @Override
    public Iterable<Comentario> getComentarios(long publicacionId, int page, int itemsByPage) {
        BooleanExpression byPublicacion = qComentario.publicacion.id.eq(publicacionId);
        return comentarioRepository.findAll(byPublicacion, new PageRequest(page, itemsByPage, new QSort(qComentario.id.desc()))).getContent();
    }
    
}
