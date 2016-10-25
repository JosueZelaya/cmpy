/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QComentario;
import com.tecnogeek.comprameya.entidad.QPublicacion;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.custom.PublicacionRepositoryCustom;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;

/**
 *
 * @author alexander
 */
public class PublicacionRepositoryImpl implements PublicacionRepositoryCustom{
    
    @Autowired
    EntityManager em;
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    private final QPublicacion qPublicacion = QPublicacion.publicacion;
    private final QComentario qComentario = QComentario.comentario;
    private final QUsuario qUsuario = QUsuario.usuario;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }
    
    @Override
    public Publicacion getPublicacion(long publicacion_id) {
        Predicate filtradaPorId = qPublicacion.id.eq(publicacion_id);
        
        return newJpaQuery().from(qPublicacion,qComentario,qUsuario)
                .leftJoin(qPublicacion.comentarioList,qComentario)
                .fetch()
                .leftJoin(qComentario.fkUsuario,qUsuario)
                .fetch()
                .where(filtradaPorId).uniqueResult(qPublicacion);
//        return publicacionRepository.findOne(filtradaPorId);
    }

    @Override
    public Iterable<Publicacion> getPublicacionesPagadas() {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonPagadas);
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis() {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonGratis);
    }

    @Override
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage) {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonPagadas, new PageRequest(page, itemsByPage)).getContent();
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis(int page, int itemsByPage) {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonGratis, new PageRequest(page, itemsByPage, new QSort(qPublicacion.id.desc()))).getContent();
    }

    @Override
    public long getTotalPublicacionesPagadas() {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.count(sonPagadas);
    }

    @Override
    public long getTotalPublicacionesGratis() {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.count(sonGratis);
    }
    
}
