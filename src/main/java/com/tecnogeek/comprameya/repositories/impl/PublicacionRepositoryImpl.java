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
import com.tecnogeek.comprameya.entidad.Usuario;
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
        BooleanExpression sonPagadas = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.findAll(sonPagadas.and(disponibles).and(estanActivas));
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis() {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.findAll(sonGratis.and(disponibles).and(estanActivas));
    }

    @Override
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage) {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        BooleanExpression sonPagadas = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.findAll(sonPagadas.and(disponibles).and(estanActivas), new PageRequest(page, itemsByPage)).getContent();
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratis(int page, int itemsByPage) {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.findAll(sonGratis.and(disponibles).and(estanActivas), new PageRequest(page, itemsByPage, new QSort(qPublicacion.id.desc()))).getContent();
    }
    
    @Override
    public Iterable<Publicacion> getPublicacionesByUsuario(int page, int itemsByPage, TipoPublicacionEnum tipo, Boolean vendidas, Usuario usuario){
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        BooleanExpression lePertenecen = qPublicacion.usuario.id.eq(usuario.getId());
        BooleanExpression filtradasPorTipo = qPublicacion.tipo.id.eq(tipo.getCodigo());
        BooleanExpression cumpleTodasCondiciones = sonGratis.and(estanActivas).and(lePertenecen).and(filtradasPorTipo);
        PageRequest paginacion = new PageRequest(page, itemsByPage, new QSort(qPublicacion.id.desc()));
        return publicacionRepository.findAll(cumpleTodasCondiciones, paginacion).getContent();
    }
    
    @Override
    public Iterable<Publicacion> getPublicacionesPagadas(int page, int itemsByPage,long categoria_id) {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        qCategoria.id.eq(categoria_id);
        BooleanExpression sonPagadas = qPublicacion.tipo.id.eq(id);
        BooleanExpression matchCat = qPublicacion.productoList.get(0).categoria.eq(qCategoria);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.findAll(sonPagadas.and(estanActivas).and(disponibles).and(matchCat), new PageRequest(page, itemsByPage)).getContent();
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratisSubSubCat(int page, int itemsByPage, long categoria_id) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression porCategoria = qProducto.categoria.id.eq(categoria_id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto)
            .where(sonGratis.and(disponibles).and(porCategoria).and(estanActivas)).orderBy(qPublicacion.id.desc()).list(qPublicacion);
        
    }

    @Override
    public Iterable<Publicacion> getPublicacionesGratisSubCat(int page, int itemsByPage, long categoria_id) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto)
            .leftJoin(qProducto.categoria,qCategoria)
            .where(sonGratis.and(disponibles)
            .and(qCategoria.in(newJpaQuery()
                                    .from(qCategoria)
                                        .where(qCategoria.categoriaPadre.id.eq(categoria_id)).list(qCategoria)).or(qCategoria.id.eq(categoria_id))
            )
            .and(estanActivas)).orderBy(qPublicacion.id.desc()).list(qPublicacion);
        
    }  

    @Override
    public Iterable<Publicacion> getPublicacionesGratisCat(int page, int itemsByPage, long categoria_id) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto)
            .leftJoin(qProducto.categoria,qCategoria)
            .leftJoin(qCategoria.categoriaPadre,qCategoriaPadre)
            .where(sonGratis.and(disponibles).and(qCategoriaPadre.categoriaPadre.in(newJpaQuery()
                                                .from(qCategoria)
                                                    .where(qCategoria.id.eq(categoria_id)).list(qCategoria))
                                                .or(qCategoriaPadre.in(newJpaQuery()
                                                .from(qCategoria)
                                                    .where(qCategoria.id.eq(categoria_id)).list(qCategoria)))
                                                .or(qCategoria.id.eq(categoria_id))
            ).and(estanActivas)).orderBy(qPublicacion.id.desc()).list(qPublicacion);
        
    }   
    
    @Override
    public Iterable<Publicacion> getPublicacionesGratisByMatch(int page, int itemsByPage, String match) {
        
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression Match = qProducto.nombre.toUpperCase().like("%"+match.toUpperCase()+"%");
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        
        return newJpaQuery().from(qPublicacion)
            .leftJoin(qPublicacion.productoList,qProducto) 
            .where(sonGratis.and(disponibles).and(Match).and(estanActivas))
            .orderBy(qPublicacion.id.desc()).list(qPublicacion);
        
    }  

    @Override
    public long getTotalPublicacionesPagadas() {
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        BooleanExpression sonPagadas = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.count(sonPagadas.and(disponibles).and(estanActivas));
    }

    @Override
    public long getTotalPublicacionesGratis() {
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        BooleanExpression sonGratis = qPublicacion.tipo.id.eq(id);
        BooleanExpression disponibles = qPublicacion.vendido.eq(false);
        BooleanExpression estanActivas = qPublicacion.sisActivo.eq(true);
        return publicacionRepository.count(sonGratis.and(disponibles).and(estanActivas));
    }
    
}
