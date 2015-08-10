/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.repositories.PublicacionService;
import com.tecnogeek.comprameya.utils.GridResponse;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 *
 * @author arch
 */

public class ManejadorPublicacion {
    
    public ManejadorPublicacion(){}
    
    @Autowired
    PublicacionService publicacionService;
    
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
        if(tipo==Constantes.PUBLICACION_PAGADA)
        {
            return publicacionService.getTotalPublicacionesPagadas();
        }else
        {
            return publicacionService.getTotalPublicacionesGratis();
        }
    }
    
    public List<Publicacion> getPublicaciones(PageRequest pageRequest,int tipo)
    {
        if(tipo==Constantes.PUBLICACION_PAGADA)
        {
            return publicacionService.getPublicacionesPagadas(pageRequest);
        }else
        {
            return publicacionService.getPublicacionesGratis(pageRequest);
        }
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
    
}