/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.repositories.UbicacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author genaro
 */

@Service
public class ManejadorUbicacion {
    
    public ManejadorUbicacion(){}
    
    @Autowired
    UbicacionService ubicacionService;
    
    public List<Ubicacion> getUbicacionPublicacion(Publicacion publicacion)
    {
        return (List<Ubicacion>) ubicacionService.findByfkPublicacion(publicacion);
    }
    
    public void setGuardarTodos(List<Ubicacion> lista)
    {
        ubicacionService.save(lista);
    }
    
    public void setGuardar(Ubicacion ubicacion)
    {
        ubicacionService.save(ubicacion);
    }
    
    public void setEliminarTodo(List<Ubicacion> lista)
    {
        ubicacionService.delete(lista);
    }
    
    public void setEliminar(Ubicacion ubicacion)
    {
        ubicacionService.delete(ubicacion);
    }
    

    
}
