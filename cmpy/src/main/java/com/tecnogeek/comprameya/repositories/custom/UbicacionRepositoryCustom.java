/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnobitz.cmpy.entidad.Ubicacion;

/**
 *
 * @author alexander
 */
public interface UbicacionRepositoryCustom {
    
    public Iterable<Ubicacion> getUbicacionPublicacion(Publicacion publicacion);
    
}
