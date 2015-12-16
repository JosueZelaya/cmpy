/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.repositories.ComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author genaro
 */
public class ManejadorComentario {
    public ManejadorComentario(){}
    
    @Autowired
    ComentarioService comentarioService;
    
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
