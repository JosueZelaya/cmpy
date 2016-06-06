/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import java.util.List;
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
    ComentarioRepository comentarioService;
    
    public String setComentario(Comentario c)
    {
        comentarioService.save(c);
        return "";
    }
    
    public List<Comentario> getComentario(Publicacion publicacion)
    {
        return comentarioService.findByfkPublicacion(publicacion);
    }
    
}
