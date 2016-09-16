/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QPublicacion;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.custom.PublicacionRepositoryCustom;
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
    PublicacionRepository publicacionRepository;
    
    private final QPublicacion qPublicacion = QPublicacion.publicacion;
    
    @Override
    public Publicacion getPublicacion(long publicacion_id) {
        Predicate porId = qPublicacion.id.eq(publicacion_id);
        return publicacionRepository.findOne(porId);
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
