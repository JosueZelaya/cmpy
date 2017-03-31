/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.SuscripcionUsuario;
import com.tecnogeek.comprameya.repositories.custom.SuscripcionUsuarioRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface SuscripcionUsuarioRepository 
        extends BaseRepository<SuscripcionUsuario,Long>, 
        SuscripcionUsuarioRepositoryCustom {}
