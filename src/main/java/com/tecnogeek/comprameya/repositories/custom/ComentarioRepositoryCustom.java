/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;

/**
 *
 * @author alexander
 */
public interface ComentarioRepositoryCustom {
    
    public Iterable<Comentario> getComentario(Publicacion publicacion);
    
}
