/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.dto.GridResponse;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;

/**
 *
 * @author arch
 */

@Service
public class PublicacionService extends BaseService<Publicacion , Long>{
    
    public PublicacionService(){}
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Override
    public BaseRepository<Publicacion, Long> getRepository() {
        return publicacionRepository;
    }
    
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
                publicacionRepository.getTotalPublicacionesPagadas():
                publicacionRepository.getTotalPublicacionesGratis();        
    }
    
    public Iterable<Publicacion> getPublicaciones(PageRequest pageRequest,TipoPublicacionEnum tipo)
    {
        Iterable<Publicacion> publicaciones = new ArrayList();
        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        publicaciones = (esPagada)?
                publicacionRepository.getPublicacionesPagadas(pageRequest):
                publicacionRepository.getPublicacionesGratis(pageRequest);
        
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
    
}
