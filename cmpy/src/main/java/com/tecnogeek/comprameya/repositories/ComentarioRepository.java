/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnobitz.core.entidad.Comentario;
import com.tecnogeek.comprameya.repositories.custom.ComentarioRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface ComentarioRepository 
        extends BaseRepository<Comentario, Long>, ComentarioRepositoryCustom {}
