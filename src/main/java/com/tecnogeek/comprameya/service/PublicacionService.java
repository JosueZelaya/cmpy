/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.dto.GridResponse;
import com.tecnogeek.comprameya.entidad.QPublicacion;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import org.springframework.data.domain.Page;

/**
 *
 * @author arch
 */

@Service
public class PublicacionService {
    
    private final QPublicacion qPublicacion = QPublicacion.publicacion;
    
    public PublicacionService(){}
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    public Iterable<Publicacion> getAnunciosAleatorios(int total,TipoPublicacionEnum tipo)
    {
        int pageZise = total;
        Long totalPublicaciones = getTotalPublicaciones(tipo);        
        int totalPages = Utilidades.calculateTotalPages(totalPublicaciones.intValue(), pageZise);
        int page = Utilidades.randomInt(0,totalPages-1); //dado que la primer pagina en la BD es cero.        
        Iterable<Publicacion> publicaciones = getPublicaciones(new PageRequest(page, pageZise), tipo);
        return publicaciones;
    }       
    
    
    
    public Long getTotalPublicaciones(TipoPublicacionEnum tipo)
    {        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        return (esPagada)?
                getTotalPublicacionesPagadas():
                getTotalPublicacionesGratis();        
    }
    
    public Iterable<Publicacion> getPublicaciones(PageRequest pageRequest,TipoPublicacionEnum tipo)
    {
        Iterable<Publicacion> publicaciones = new ArrayList();
        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        publicaciones = (esPagada)?
                getPublicacionesPagadas(pageRequest):
                getPublicacionesGratis(pageRequest);
        
        //Replace backslashes by forward slashes in order to work well in firefox.
        for(Publicacion publicacion : publicaciones){
            publicacion.getUbicacionList().size();
            publicacion.getComentarioList().size();
            publicacion.getProductoList().size();
            for(Recurso recurso : publicacion.getRecursoList()){                                      
                recurso.setRuta(recurso.getRuta().replace('\\', '/'));
            }
        }
        
        return publicaciones;
    }   
    
    public GridResponse getPublicacionesGrid(int page, int pageZise, TipoPublicacionEnum tipo)
    { 
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);        
        grid.setRows(getPublicaciones( new PageRequest(page, pageZise), tipo));
        grid.setTotal(getTotalPublicaciones(tipo).intValue());
        return grid;
    }
    
    public GridResponse getPublicacionesGrid(int page, int pageZise, Sort sort, TipoPublicacionEnum tipo)
    { 
        int totalPublicaiones = getTotalPublicaciones(tipo).intValue();
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);        
        grid.setRows(getPublicaciones( new PageRequest(page, pageZise,sort), tipo));        
        grid.setTotal(totalPublicaiones);
        grid.setTotalPages(Utilidades.calculateTotalPages(totalPublicaiones, pageZise));
        return grid;
    }
    
    public Publicacion getPublicacion(long publicacion_id)
    {
        Predicate porId = qPublicacion.publicacionId.eq(publicacion_id);
        return publicacionRepository.findOne(porId);
    }
    
    public Iterable<Publicacion> getPublicacionesPagadas(){
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.tipoPublicacionId.eq(id);
        return publicacionRepository.findAll(sonPagadas);
    }
    
    public Iterable<Publicacion> getPublicacionesGratis(){
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.tipoPublicacionId.eq(id);
        return publicacionRepository.findAll(sonGratis);
    }
    
    public Iterable<Publicacion> getPublicacionesPagadas(PageRequest pageRequest){
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.tipoPublicacionId.eq(id);
        Page page = publicacionRepository.findAll(sonPagadas, pageRequest);
        return page.getContent();
    }
    
    public Iterable<Publicacion> getPublicacionesGratis(PageRequest pageRequest){
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.tipoPublicacionId.eq(id);
        return publicacionRepository.findAll(sonGratis, pageRequest).getContent();
    }
    
    public long getTotalPublicacionesPagadas(){
        Long id = TipoPublicacionEnum.PAGADA.getCodigo();
        Predicate sonPagadas = qPublicacion.fkTipoPublicacion.tipoPublicacionId.eq(id);
        return publicacionRepository.count(sonPagadas);
    }
    
    public long getTotalPublicacionesGratis(){
        Long id = TipoPublicacionEnum.GRATIS.getCodigo();
        Predicate sonGratis = qPublicacion.fkTipoPublicacion.tipoPublicacionId.eq(id);
        return publicacionRepository.count(sonGratis);
    }
}
