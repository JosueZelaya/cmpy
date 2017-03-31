/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnogeek.comprameya.repositories.custom.PublicacionRepositoryCustom;

/**
 *
 * @author jzelaya
 */
public interface PublicacionRepository 
        extends BaseRepository<Publicacion, Long>, PublicacionRepositoryCustom {}
