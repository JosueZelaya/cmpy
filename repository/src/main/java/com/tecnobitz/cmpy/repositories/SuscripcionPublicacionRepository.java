/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.SuscripcionPublicacion;
import com.tecnogeek.comprameya.repositories.custom.SuscripcionPublicacionRepositoryCustom;

/**
 *
 * @author alexander
 */
public interface SuscripcionPublicacionRepository 
        extends BaseRepository<SuscripcionPublicacion, Long>,
        SuscripcionPublicacionRepositoryCustom
    {
    
}
