/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.google.common.collect.Iterables;
import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnobitz.cmpy.entidad.Ubicacion;
import com.tecnobitz.cmpy.repositories.PublicacionRepository;
import com.tecnobitz.cmpy.repositories.UbicacionRepository;
import com.tecnogeek.comprameya.dto.pojoUbicacion;
import java.util.ArrayList;
import java.util.Iterator;
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
    UbicacionRepository ubicacionRepository;
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @RequestMapping(value = "/publicacion/get/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<pojoUbicacion> getUbicacionPublicacion(@PathVariable("id") long id)  {          
        Iterable<Ubicacion> listaUbicacion;
        listaUbicacion = getUbicacionPublicacionI(id);
         return getUbicacionPojo(listaUbicacion);   
    }
    @RequestMapping(value = "/publicacion/set/{id}", method = RequestMethod.POST)
    public @ResponseBody String setUbicacionPublicacion(@PathVariable("id") long id, @RequestBody List<pojoUbicacion> listaUbicacion )  {
        
        Iterable<Ubicacion> listaUbicacionTmp;
   
        listaUbicacionTmp = getUbicacionPublicacionI(id);

        //Actualizar
        if(listaUbicacion.size() == Iterables.size(listaUbicacionTmp))
        {
            Iterator it = listaUbicacionTmp.iterator();
            int i = 0;
            while (it.hasNext()) {
              Iterables.get(listaUbicacionTmp, i).setGmLatitud(listaUbicacion.get(i).getLatitud());
              Iterables.get(listaUbicacionTmp, i).setGmLongitud(listaUbicacion.get(i).getLongitud());
              i++;
            }
            
//            for(int i=0;i < Iterables.size(listaUbicacionTmp);i++ )
//            {
//                listaUbicacionTmp.get(i).setGmLatitud(listaUbicacion.get(i).getLatitud());
//                listaUbicacionTmp.get(i).setGmLongitud(listaUbicacion.get(i).getLongitud());
//            }
            
            ubicacionRepository.save(listaUbicacionTmp);
        } 
        //Agregar        
        else if(!listaUbicacion.isEmpty() && Iterables.isEmpty(listaUbicacionTmp))
        {
            List<Ubicacion> ubicacionesToSave = new ArrayList<>();
            for(pojoUbicacion p: listaUbicacion)
            {
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(p.getLatitud());
                ubi.setGmLongitud(p.getLongitud());
                ubi.setPublicacion(publicacionRepository.getPublicacion(id));
                
                ubicacionesToSave.add(ubi);
            }
            
            ubicacionRepository.save(ubicacionesToSave);
            
        }
        //Eliminar y Actualizar
        else if(listaUbicacion.size() < Iterables.size(listaUbicacionTmp))
        {
            for(int i=0;i<listaUbicacion.size();i++)
            {
                Iterables.get(listaUbicacionTmp, i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                Iterables.get(listaUbicacionTmp, i).setGmLongitud(listaUbicacion.get(i).getLongitud());
                
                ubicacionRepository.save(Iterables.get(listaUbicacionTmp, i));
            }
            for(int i =listaUbicacion.size()-1; i< Iterables.size(listaUbicacionTmp); i++ )
            {
                ubicacionRepository.delete(Iterables.get(listaUbicacionTmp, i));
            }
        }
        //Agregar y Actualizar
        else if(listaUbicacion.size() > Iterables.size(listaUbicacionTmp))
        {
            for(int i=0;i<Iterables.size(listaUbicacionTmp); i++)
            {
                Iterables.get(listaUbicacionTmp, i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                Iterables.get(listaUbicacionTmp, i).setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubicacionRepository.save(Iterables.get(listaUbicacionTmp, i));
            }
            for(int i =Iterables.size(listaUbicacionTmp)-1; i<listaUbicacion.size(); i++ )
            {
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(listaUbicacion.get(i).getLatitud());
                ubi.setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubi.setPublicacion(publicacionRepository.getPublicacion(id));
                ubicacionRepository.save(ubi);
            }
        }
        //Eliminar
        else if(listaUbicacion.isEmpty())
        {
            ubicacionRepository.delete(listaUbicacionTmp);
        }
        
        
        return "";   
    }  

    public String setUbicacionPublicacionI( long id, List<pojoUbicacion> listaUbicacion )  {
        
        Iterable<Ubicacion> listaUbicacionTmp;
   
        listaUbicacionTmp = getUbicacionPublicacionI(id);

        //Actualizar
        if(listaUbicacion.size() == Iterables.size(listaUbicacionTmp))
        {
            for(int i=0;i < Iterables.size(listaUbicacionTmp);i++ )
            {
                Iterables.get(listaUbicacionTmp, i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                Iterables.get(listaUbicacionTmp, i).setGmLongitud(listaUbicacion.get(i).getLongitud());
            }
            
            ubicacionRepository.save(listaUbicacionTmp);
        } 
        //Agregar
        else if(!listaUbicacion.isEmpty() && Iterables.isEmpty(listaUbicacionTmp))
        {
            List<Ubicacion> ubicacionesToSave = new ArrayList();
            for(pojoUbicacion p: listaUbicacion)
            {
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(p.getLatitud());
                ubi.setGmLongitud(p.getLongitud());
                ubi.setPublicacion(publicacionRepository.getPublicacion(id));
                ubicacionesToSave.add(ubi);
            }
            
            ubicacionRepository.save(ubicacionesToSave);
            
        }
        //Eliminar y Actualizar
        else if(listaUbicacion.size() < Iterables.size(listaUbicacionTmp))
        {
            for(int i=0;i<listaUbicacion.size();i++)
            {
                Iterables.get(listaUbicacionTmp, i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                Iterables.get(listaUbicacionTmp, i).setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubicacionRepository.save(Iterables.get(listaUbicacionTmp, i));
            }
            for(int i =listaUbicacion.size()-1;i<Iterables.size(listaUbicacionTmp);i++ )
            {
                ubicacionRepository.save(Iterables.get(listaUbicacionTmp, i));
            }
        }
        //Agregar y Actualizar
        else if(listaUbicacion.size() > Iterables.size(listaUbicacionTmp))
        {
            for(int i=0;i<Iterables.size(listaUbicacionTmp);i++)
            {
                Iterables.get(listaUbicacionTmp, i).setGmLatitud(listaUbicacion.get(i).getLatitud());
                Iterables.get(listaUbicacionTmp, i).setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubicacionRepository.save(Iterables.get(listaUbicacionTmp, i));
            }
            for(int i =Iterables.size(listaUbicacionTmp)-1;i<listaUbicacion.size();i++ )
            {
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(listaUbicacion.get(i).getLatitud());
                ubi.setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubi.setPublicacion(publicacionRepository.getPublicacion(id));
                ubicacionRepository.save(ubi);
            }
        }
        //Eliminar
        else if(listaUbicacion.isEmpty())
        {
            ubicacionRepository.delete(listaUbicacionTmp);
        }
        
        
        return "";   
    }    
        
    private Iterable<pojoUbicacion> getUbicacionPojo(Iterable<Ubicacion> ubicaciones)
    {
        List<pojoUbicacion> listapojo = new ArrayList<>();
        for(Ubicacion ubi : ubicaciones)
        {
            pojoUbicacion pojo = new pojoUbicacion();
            pojo.setId(ubi.getId());
            pojo.setLatitud(ubi.getGmLatitud());
            pojo.setLongitud(ubi.getGmLongitud());
            
            listapojo.add(pojo);
        }
        
        return listapojo;
    }
    
    private Iterable<Ubicacion> getUbicacionPublicacionI(long publicacion_id)
    {
        Publicacion publicacion = publicacionRepository.getPublicacion(publicacion_id);
        
        Iterable<Ubicacion> listaUbicacion = new ArrayList<>();
        
        if(publicacion != null)
        {
            listaUbicacion = ubicacionRepository.getUbicacionPublicacion(publicacion);
        }
        
        return listaUbicacion;
    }
    

    
}
