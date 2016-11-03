/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QCategoria;
import com.tecnogeek.comprameya.entidad.QComentario;
import com.tecnogeek.comprameya.entidad.QProducto;
import com.tecnogeek.comprameya.entidad.QPublicacion;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.custom.PublicacionRepositoryCustom;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    private final QCategoria qCategoria = QCategoria.categoria;
    private final QCategoria qCategoriaPadre = QCategoria.categoria;
    private final QProducto qProducto = QProducto.producto;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }
    
    @Override
    public Publicacion getPublicacion(long publicacion_id) {
        Predicate filtradaPorId = qPublicacion.id.eq(publicacion_id);
        return publicacionRepository.findOne(filtradaPorId);
    }

    @Override
    public Iterable<Publicacion> getPublicacionesPagadas() {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.tipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonPagadas);
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis() {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonGratis);
    }

    @Override
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage) {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.tipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonPagadas, new PageRequest(page, itemsByPage)).getContent();
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis(int page, int itemsByPage) {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
        return publicacionRepository.findAll(sonGratis, new PageRequest(page, itemsByPage, new QSort(qPublicacion.id.desc()))).getContent();
    }
    
    @Override
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage,long categoria_id) {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        qCategoria.id.eq(categoria_id);
        BooleanExpression sonPagadas = qPublicacion.tipoPublicacion.id.eq(id);
        BooleanExpression matchCat = qPublicacion.productoList.get(0).categoria.eq(qCategoria);
        return publicacionRepository.findAll(sonPagadas.and(matchCat), new PageRequest(page, itemsByPage)).getContent();
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratisSubSubCat(int page, int itemsByPage, long categoria_id) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
        BooleanExpression porCategoria = qProducto.categoria.id.eq(categoria_id);
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto)
            .where(sonGratis.and(porCategoria)).list(qPublicacion);
        
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratisSubCat(int page, int itemsByPage, long categoria_id) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
       
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto)
            .leftJoin(qProducto.categoria,qCategoria)
            .where(sonGratis.and(qCategoria.in( newJpaQuery()
                                                .from(qCategoria)
                                                    .where(qCategoria.categoriaPadre.id.eq(categoria_id)).list(qCategoria))
            )).list(qPublicacion);
        
    }  

    @Override
    public Iterable<Publicacion> getPublicacionesGratisCat(int page, int itemsByPage, long categoria_id) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
        
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto)
            .leftJoin(qProducto.categoria,qCategoria)
            .leftJoin(qCategoria.categoriaPadre,qCategoriaPadre)
            .where(sonGratis.and(qCategoriaPadre.categoriaPadre.in(newJpaQuery()
                                                .from(qCategoria)
                                                    .where(qCategoria.id.eq(categoria_id)).list(qCategoria))
            )).list(qPublicacion);
        
    }   
    
    @Override
    public Iterable<Publicacion> getPublicacionesGratisByMatch(int page, int itemsByPage, String match) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
        BooleanExpression Match = qProducto.nombre.toUpperCase().like("%"+match.toUpperCase()+"%");

        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto) 
            .where(sonGratis.and(Match))
            .list(qPublicacion);
        

        
    }  

    @Override
    public long getTotalPublicacionesPagadas() {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.tipoPublicacion.id.eq(id);
        return publicacionRepository.count(sonPagadas);
    }

    @Override
    public long getTotalPublicacionesGratis() {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.tipoPublicacion.id.eq(id);
        return publicacionRepository.count(sonGratis);
    }
    
}
