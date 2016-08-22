/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;


import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.repositories.custom.UbicacionRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface UbicacionRepository 
        extends BaseRepository<Ubicacion,Long>, 
        UbicacionRepositoryCustom {}
