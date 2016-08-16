/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.dto.GridResponse;
import com.tecnogeek.comprameya.entidad.QPublicacion;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.util.ArrayList;
import java.util.List;
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
public class PublicacionService {
    
    public PublicacionService(){}
    private QPublicacion qPublicacion = QPublicacion.publicacion;
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    public List<Publicacion> getAnunciosAleatorios(int total,int tipo)
    {
        int pageZise = total;
        int totalPublicaciones = getTotalPublicaciones(tipo);        
        int totalPages = Utilidades.calculateTotalPages(totalPublicaciones, pageZise);
        int page = Utilidades.randomInt(0,totalPages-1); //dado que la primer pagina en la BD es cero.        
        List<Publicacion> publicaciones = getPublicaciones(new PageRequest(page, pageZise), tipo);
        return publicaciones;
    }       
    
    
    
    public int getTotalPublicaciones(int tipo)
    {        
        return (tipo==Constantes.PUBLICACION_PAGADA)?
                publicacionRepository.getTotalPublicacionesPagadas():
                publicacionRepository.getTotalPublicacionesGratis();        
    }
    
    public List<Publicacion> getPublicaciones(PageRequest pageRequest,int tipo)
    {
        List<Publicacion> publicaciones = new ArrayList();
        
        publicaciones = (tipo==Constantes.PUBLICACION_PAGADA)?
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
    
    public GridResponse getPublicacionesGrid(int page,int pageZise,int tipo)
    { 
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);        
        grid.setRows(getPublicaciones( new PageRequest(page, pageZise), tipo));
        grid.setTotal(getTotalPublicaciones(tipo));
        return grid;
    }
    
    public GridResponse getPublicacionesGrid(int page,int pageZise,Sort sort,int tipo)
    { 
        int totalPublicaiones = getTotalPublicaciones(tipo);
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
//        return publicacionRepository.findOne(publicacion_id);
    }
    
}
