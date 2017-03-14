/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnobitz.core.entidad.Comentario;
import com.tecnobitz.core.entidad.Publicacion;

/**
 *
 * @author alexander
 */
public interface ComentarioRepositoryCustom {
    
    public Iterable<Comentario> getComentario(Publicacion publicacion);
    public Iterable<Comentario> getComentario(long publicacionId);
    public Iterable<Comentario> getComentarios(long publicacionId, int page, int itemsByPage);
    
}
