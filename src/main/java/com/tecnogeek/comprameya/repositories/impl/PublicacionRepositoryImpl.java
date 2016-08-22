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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
    public Iterable<Publicacion> getPublicacionesPagadas(PageRequest pageRequest) {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.id.eq(id);
        Page page = publicacionRepository.findAll(sonPagadas, pageRequest);
        return page.getContent();
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis(PageRequest pageRequest) {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonGratis, pageRequest).getContent();
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
