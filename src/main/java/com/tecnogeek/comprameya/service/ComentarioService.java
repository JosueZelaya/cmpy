/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.ComentarioRepository;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;

/**
 *
 * @author genaro
 */

@Service
public class ComentarioService extends BaseService<Comentario, Long>{
    public ComentarioService(){}
    
    @Autowired
    ComentarioRepository comentarioRepository;
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Override
    public BaseRepository<Comentario, Long> getRepository() {
        return comentarioRepository;
    }
    
    public Iterable<Comentario> getComentarios(Long publicacionId, int page, int itemsByPage){        
        return comentarioRepository.getComentarios(publicacionId, page, itemsByPage);
    }
    
    public Comentario save(Long publicacionId, String comentario){
        Usuario u = usuarioRepository.getLoggedUser();
        
        Publicacion p = publicacionRepository.getPublicacion(publicacionId);
        
        Comentario c = new Comentario();
        c.setTexto(comentario);
        c.setPublicacion(p);
        c.setUsuario(u);
        
        
       
        return comentarioRepository.save(c);
    }
    
}
