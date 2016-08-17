/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.QComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.ComentarioRepository;

/**
 *
 * @author genaro
 */

@Service
public class ComentarioService {
    public ComentarioService(){}
    
    @Autowired
    ComentarioRepository comentarioRepository;
    
    private final QComentario qComentario = QComentario.comentario;
    
    public String setComentario(Comentario c)
    {
        comentarioRepository.save(c);
        return "";
    }
    
    public Iterable<Comentario> getComentario(Publicacion publicacion)
    {
        Predicate byPublicacion = qComentario.fkPublicacion.eq(publicacion);
        return comentarioRepository.findAll(byPublicacion);
    }
    
}
