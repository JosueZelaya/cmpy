/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.SuscripcionPublicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.ComentarioRepository;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Autowired
    NotificacionService notificacionService;
    
    @Autowired
    SuscripcionService suscripcionService;
    
    @Override
    public BaseRepository<Comentario, Long> getRepository() {
        return comentarioRepository;
    }
    
    public Iterable<Comentario> getComentarios(Long publicacionId, int page, int itemsByPage){        
        return comentarioRepository.getComentarios(publicacionId, page, itemsByPage);
    }
    
    public Comentario save(Long publicacionId, String texto){
        Usuario loggedUser = usuarioRepository.getLoggedUser();
        
        Publicacion publicacion = publicacionRepository.getPublicacion(publicacionId);
                
        Comentario comentario = new Comentario();
        comentario.setTexto(texto);
        comentario.setPublicacion(publicacion);
        comentario.setUsuario(loggedUser);
        
        comentario = comentarioRepository.save(comentario);
        
        List<Usuario> suscriptores = suscripcionService.getSuscriptores(publicacion);
        
        notificacionService.informarASuscriptores(suscriptores, loggedUser, publicacion);
        
        if( !yaEstaEnLista(suscriptores, loggedUser) ){
            SuscripcionPublicacion suscripcionPublicacion = new SuscripcionPublicacion();
            suscripcionPublicacion.setPublicacion(publicacion);
            suscripcionPublicacion.setUsuario(loggedUser);
            suscripcionService.save(suscripcionPublicacion);
        }
        
        return comentario;
    }
    
    public static boolean yaEstaEnLista(List<Usuario> usuarioList, Usuario usuario){
        
        //Forma número 1 (Uso de Maps).
        Map<Long, Usuario> mapUsuarios = new HashMap<>(usuarioList.size());

        //Aquí está la magia
        for(Usuario u : usuarioList) {
            mapUsuarios.put(u.getId(), u);
        }

        mapUsuarios.put(usuario.getId(), usuario);
        
        return (usuarioList.size() == mapUsuarios.size());
    }
    
}
