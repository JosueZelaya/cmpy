/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.NotificacionUsuario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.NotificacionRepository;
import com.tecnogeek.comprameya.repositories.NotificacionUsuarioRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander
 */

@Service
public class NotificacionService {
    
    @Autowired
    NotificacionUsuarioRepository notificacionUsuarioRepository;
    
    @Autowired
    NotificacionRepository notificacionRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    private SimpMessagingTemplate template;
    
    public List<NotificacionUsuario> getNotificaciones(){
        Usuario usuario = usuarioRepository.getLoggedUser();
        
        if(usuario==null){
            return null;
        }
        
        return notificacionUsuarioRepository.getNotificaciones(usuario);
    }
    
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void informarASuscriptores(List<Usuario> suscriptores, Usuario emisor, Publicacion publicacion){
        
        suscriptores = getUsuariosExeptoUno(suscriptores, emisor); //no se notifica al emisor del comentario
        
        if(suscriptores.isEmpty()){
            return;
        }
        
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(emisor.getPersona().getNombre() + " ha comentado el anuncio: " + publicacion.getTitulo());
        notificacion.setLink(publicacion.getId()+"");
        
        notificacion = notificacionRepository.save(notificacion);
        
        List<NotificacionUsuario> notificaciones = new ArrayList<>();
        for(Usuario usuario : suscriptores){
            NotificacionUsuario nt = new NotificacionUsuario();
            nt.setUsuario(usuario);
            nt.setNotificacion(notificacion);
            notificaciones.add(nt);            
        }  
        
        notificacionUsuarioRepository.save(notificaciones);
        sendToClient(notificaciones);
    }
    
    public List<Usuario> getUsuariosExeptoUno(List<Usuario> usuarioList, Usuario usuarioExcluido){
        List<Usuario> newList = new ArrayList<>();
        
        for(Usuario usuario : usuarioList){
            if(!usuario.equals(usuarioExcluido)){
                newList.add(usuario);
            }
        }
        
        return newList;
    }
    
    public void quitarVisibilidad(Long idNotificacion){
        NotificacionUsuario not = notificacionUsuarioRepository.findOne(idNotificacion);
        
        List<NotificacionUsuario> notificaciones = notificacionUsuarioRepository.findBy(not.getNotificacion().getLink(), not.getNotificacion().getMensaje());
        
        for(NotificacionUsuario noti : notificaciones){
            noti.setVisto(true);
        }
        
        notificacionUsuarioRepository.save(notificaciones);    
    }
    
    public void sendToClient(List<NotificacionUsuario> ntList){
        for(NotificacionUsuario nt : ntList){
            template.convertAndSendToUser(nt.getUsuario().getLogin(), "/queue/greetings",nt);
        }
    }
    
    public void sendToClient(NotificacionUsuario nt){
        template.convertAndSendToUser(nt.getUsuario().getLogin(), "/queue/greetings",nt);
    }
    
}
