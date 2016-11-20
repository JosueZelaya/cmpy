/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.dto.GridResponse;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author arch
 */

@Service
public class PublicacionService extends BaseService<Publicacion , Long>{
    
    public PublicacionService(){}
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Override
    public BaseRepository<Publicacion, Long> getRepository() {
        return publicacionRepository;
    }
    
    public void eliminar(Long publicacionId) throws Exception{
        Publicacion publicacion = getRepository().findOne(publicacionId);
        
        Usuario loggedUser = usuarioRepository.getLoggedUser();
        if(!loggedUser.equals(publicacion.getUsuario())){
            throw new Exception("ERROR: No es el propietario de la publicacion");
        }
        
        publicacion.desactivar();
        getRepository().save(publicacion);
    }
    
    public List<Publicacion> getPublicacionesMixtas(int page){
        Iterable<Publicacion> publicacionesGratis = 
                getPublicaciones(page, 
                                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR, 
                                TipoPublicacionEnum.GRATIS);
        
        Iterable<Publicacion> publicacionesPagadas = 
                getAnunciosAleatorios(Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR, 
                                TipoPublicacionEnum.PAGADA);   
        
        return mezclarPublicaciones(publicacionesGratis, publicacionesPagadas);
    }
    
    public List<Publicacion> mezclarPublicaciones(Iterable<Publicacion> pubPrioritarias, Iterable<Publicacion> pubSecundarias){
        List<Publicacion> publicaciones = new ArrayList<>();
        int cont=0;
        Iterator itr = pubSecundarias.iterator();
        for(Publicacion pubPrioritaria : pubPrioritarias){
            cont++;
            if(cont % (Constantes.PRIORITARIA + 1) == 0){
                if(itr.hasNext()){
                    publicaciones.add((Publicacion) itr.next());
                }                                
            }else{
                publicaciones.add(pubPrioritaria);
            }            
        }
        return publicaciones;
    }
    
    public Iterable<Publicacion> getAnunciosAleatorios(int total,TipoPublicacionEnum tipo)
    {
        int pageZise = total;
        Long totalPublicaciones = getTotalPublicaciones(tipo);        
        int totalPages = Utilidades.calculateTotalPages(totalPublicaciones.intValue(), pageZise);
        int page = Utilidades.randomInt(0,totalPages-1); //dado que la primer pagina en la BD es cero.        
        Iterable<Publicacion> publicaciones = getPublicaciones(page, pageZise, tipo);
        return publicaciones;
    }     
    
    public Long getTotalPublicaciones(TipoPublicacionEnum tipo)
    {        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        return (esPagada)?
                publicacionRepository.getTotalPublicacionesPagadas():
                publicacionRepository.getTotalPublicacionesGratis();        
    }
    
    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage,TipoPublicacionEnum tipo, long categoria_id, int nivel)
    {
        Iterable<Publicacion> publicaciones = new ArrayList();
        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        if(esPagada)
        {
           publicaciones = publicacionRepository.getPublicacionesPagadas(page, itemsByPage, categoria_id);
           
           
        }
        else
        {
  
            switch (nivel) {
                case 1:  publicaciones = publicacionRepository.getPublicacionesGratisCat(page, itemsByPage, categoria_id);
                         break;
                case 2:  publicaciones = publicacionRepository.getPublicacionesGratisSubCat(page, itemsByPage, categoria_id);
                         break;
                case 3:  publicaciones = publicacionRepository.getPublicacionesGratisSubSubCat(page, itemsByPage, categoria_id);
                         break;
            }
        }
        
        
        
        return publicaciones;
    }   
    
    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage,TipoPublicacionEnum tipo)
    {
        Iterable<Publicacion> publicaciones = new ArrayList();
        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        publicaciones = (esPagada)?
                publicacionRepository.getPublicacionesPagadas(page, itemsByPage):
                publicacionRepository.getPublicacionesGratis(page, itemsByPage);
        
        //Replace backslashes by forward slashes in order to work well with firefox in windows
//        for(Publicacion publicacion : publicaciones){
//            publicacion.getUbicacionList().size();
//            publicacion.getComentarioList().size();
//            publicacion.getProductoList().size();
//            for(Recurso recurso : publicacion.getRecursoList()){                                      
//                recurso.setRuta(recurso.getRuta().replace('\\', '/'));
//            }
//        }

//            for(Publicacion publicacion : publicaciones){
//                publicacion.getRecursoList().size();
//            }
        
        return publicaciones;
    }
    
    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage,TipoPublicacionEnum tipo, Usuario usuario)
    {        
        return publicacionRepository.getPublicacionesByUsuario(page, itemsByPage, tipo, usuario);
    }
    
    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage,TipoPublicacionEnum tipo,String match)
    {        
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);
        
        Iterable<Publicacion> publicaciones = (esPagada)?
                publicacionRepository.getPublicacionesPagadas(page, itemsByPage):
                publicacionRepository.getPublicacionesGratisByMatch(page, itemsByPage,match);
       
        
        return publicaciones;
    }  
    
    public GridResponse getPublicacionesGrid(int page, int pageZise, TipoPublicacionEnum tipo)
    { 
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);        
        grid.setRows(getPublicaciones(page, pageZise, tipo));
        grid.setTotal(getTotalPublicaciones(tipo).intValue());
        return grid;
    }
    
    public GridResponse getPublicacionesGrid(int page, int pageZise, Sort sort, TipoPublicacionEnum tipo)
    { 
        int totalPublicaiones = getTotalPublicaciones(tipo).intValue();
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);        
        grid.setTotal(totalPublicaiones);
        grid.setTotalPages(Utilidades.calculateTotalPages(totalPublicaiones, pageZise));
        return grid;
    }
    
}
