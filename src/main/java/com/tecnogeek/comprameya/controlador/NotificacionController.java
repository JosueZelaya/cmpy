/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.NotificacionUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.NotificacionUsuarioRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import com.tecnogeek.comprameya.service.NotificacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author alexander
 */

@Controller
@RequestMapping("/notificacion")
@ResponseBody
public class NotificacionController {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    NotificacionUsuarioRepository notificacionUsuarioRepository;
    
    @Autowired
    NotificacionService notificacionService;
    
    @RequestMapping(value="/getTodasNotificaciones/",method=RequestMethod.GET)  
    public List<NotificacionUsuario> getTodasNotificaciones(){
        Usuario usuario = usuarioRepository.getLoggedUser();
        
        if(usuario==null){
            return null;
        }
        
        return notificacionUsuarioRepository.getTodasNotificaciones(usuario);
    }
    
    @RequestMapping(value="/getNotificaciones/",method=RequestMethod.GET)  
    public List<NotificacionUsuario> getNotificaciones(){
        Usuario usuario = usuarioRepository.getLoggedUser();
        
        if(usuario==null){
            return null;
        }
        
        return notificacionUsuarioRepository.getNotificaciones(usuario);
    }
    
    @RequestMapping(value="/getTotalNotificaciones/",method=RequestMethod.GET)  
    public Integer getTotalNotificaciones(){
        return getNotificaciones().size();
    }
    
    @RequestMapping(value="/quitarVisibilidad/{idNotificacion}") 
    public List<NotificacionUsuario> setVisto(@PathVariable Long idNotificacion){
       notificacionService.quitarVisibilidad(idNotificacion);
       return getNotificaciones();
    }
    
}
