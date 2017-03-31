/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnobitz.cmpy.entidad.NotificacionUsuario;
import com.tecnogeek.comprameya.repositories.custom.NotificacionUsuarioRepositoryCustom;

/**
 *
 * @author alexander
 */
public interface NotificacionUsuarioRepository 
        extends BaseRepository<NotificacionUsuario, Long>,
        NotificacionUsuarioRepositoryCustom {
}
