/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnobitz.core.entidad.Destinatario;
import com.tecnogeek.comprameya.repositories.custom.DestinatarioRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface DestinatarioRepository 
        extends BaseRepository<Destinatario, Long>,
        DestinatarioRepositoryCustom {
    
}