/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.managers.ManejadorUbicacion;
import com.tecnogeek.comprameya.managers.ManejadorPublicacion;
import com.tecnogeek.comprameya.repositories.UbicacionService;
import com.tecnogeek.comprameya.pojo.pojoUbicacion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author genaro
 */
@Controller
@RequestMapping("/ubicacion")
public class UbicacionController {
    
    @Autowired
    UbicacionService ubicacionService;
    @Autowired
    ManejadorUbicacion uManager;
    @Autowired
    ManejadorPublicacion pManager;
       
    
    @RequestMapping(value = "/publicacion/get/{id}", method = RequestMethod.GET)
    public @ResponseBody List<pojoUbicacion> getUbicacionPublicacion(@PathVariable("id") long id)  {          
        List<Ubicacion> listaUbicacion;
        listaUbicacion = getUbicacionPublicacionI(id);
         return getUbicacionPojo(listaUbicacion);   
    }
    
    @RequestMapping(value = "/publicacion/set/{id}", method = RequestMethod.POST)
    public @ResponseBody String setUbicacionPublicacion(@PathVariable("id") long id, @RequestBody List<pojoUbicacion> listaUbicacion )  {
        
        List<Ubicacion> listaUbicacionTmp;
   
        listaUbicacionTmp = getUbicacionPublicacionI(id);

        //Actualizar
        if(listaUbicacion.size() == listaUbicacionTmp.size())
        {
            for(int i=0;i < listaUbicacionTmp.size();i++ )
            {
                listaUbicacionTmp.get(i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                listaUbicacionTmp.get(i).setGmLongitud(listaUbicacion.get(i).getLongitud());
            }
            
            uManager.setGuardarTodos(listaUbicacionTmp);
        } 
        //Agregar
        else if(!listaUbicacion.isEmpty() && listaUbicacionTmp.isEmpty())
        {
            
            for(pojoUbicacion p: listaUbicacion)
            {
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(p.getLatitud());
                ubi.setGmLongitud(p.getLongitud());
                ubi.setFkPublicacion(pManager.getPublicacion(id));
                
                listaUbicacionTmp.add(ubi);
            }
            
            uManager.setGuardarTodos(listaUbicacionTmp);
            
        }
        //Eliminar y Actualizar
        else if(listaUbicacion.size() < listaUbicacionTmp.size())
        {
            for(int i=0;i<listaUbicacion.size();i++)
            {
                listaUbicacionTmp.get(i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                listaUbicacionTmp.get(i).setGmLongitud(listaUbicacion.get(i).getLongitud());
                uManager.setGuardar(listaUbicacionTmp.get(i));
            }
            for(int i =listaUbicacion.size()-1;i<listaUbicacionTmp.size();i++ )
            {
                uManager.setEliminar(listaUbicacionTmp.get(i));
            }
        }
        //Agregar y Actualizar
        else if(listaUbicacion.size() > listaUbicacionTmp.size())
        {
            for(int i=0;i<listaUbicacionTmp.size();i++)
            {
                listaUbicacionTmp.get(i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                listaUbicacionTmp.get(i).setGmLongitud(listaUbicacion.get(i).getLongitud());
                uManager.setGuardar(listaUbicacionTmp.get(i));
            }
            for(int i =listaUbicacionTmp.size()-1;i<listaUbicacion.size();i++ )
            {
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(listaUbicacion.get(i).getLatitud());
                ubi.setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubi.setFkPublicacion(pManager.getPublicacion(id));
                uManager.setGuardar(ubi);
            }
        }
        //Eliminar
        else if(listaUbicacion.isEmpty())
        {
            uManager.setEliminarTodo(listaUbicacionTmp);
        }
        
        
        return "";   
    }    
    
    
    private List<pojoUbicacion> getUbicacionPojo(List<Ubicacion> ubicaciones)
    {
        List<pojoUbicacion> listapojo = new ArrayList<>();
        for(Ubicacion ubi : ubicaciones)
        {
            pojoUbicacion pojo = new pojoUbicacion();
            pojo.setId(ubi.getUbicacionId());
            pojo.setLatitud(ubi.getGmLatitud());
            pojo.setLongitud(ubi.getGmLongitud());
            
            listapojo.add(pojo);
        }
        
        return listapojo;
    }
    
    private List<Ubicacion> getUbicacionPublicacionI(long publicacion_id)
    {
        Publicacion publicacion = pManager.getPublicacion(publicacion_id);
        
        List<Ubicacion> listaUbicacion = new ArrayList<>();
        
        if(publicacion != null)
        {
            listaUbicacion = uManager.getUbicacionPublicacion(publicacion);
        }
        
        return listaUbicacion;
    }
    

    
}
