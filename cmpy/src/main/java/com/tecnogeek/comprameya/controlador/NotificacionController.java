/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnobitz.cmpy.entidad.NotificacionUsuario;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.repositories.NotificacionUsuarioRepository;
import com.tecnobitz.cmpy.repositories.UsuarioRepository;
import com.tecnogeek.comprameya.service.NotificacionService;
import com.tecnogeek.comprameya.service.UsuarioService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class NotificacionController {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    NotificacionUsuarioRepository notificacionUsuarioRepository;
    
    @Autowired
    NotificacionService notificacionService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value="/getTodasNotificaciones/",method=RequestMethod.GET)  
    public List<NotificacionUsuario> getTodasNotificaciones(){        
        return notificacionService.getTodasNotificaciones();
    }
    
    @RequestMapping(value="/getNotificaciones/",method=RequestMethod.GET)  
    public List<NotificacionUsuario> getNotificaciones(){
        return notificacionService.getNotificaciones();
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
    
    @RequestMapping(value="/solicitarNotificacionesPush/",method=RequestMethod.GET)  
    public void enviarNotificaciones(){
        Usuario usuario = notificacionService.getLoggedUser();
        
        if(usuario==null){
            return;
        }
        List<NotificacionUsuario> ntList = notificacionUsuarioRepository.getNotificaciones(usuario);
        
        notificacionService.sendToClient(ntList);
    }
    
    @RequestMapping(value="/ocultar/{idNotificacion}") 
    public void ocultar(@PathVariable Long idNotificacion){
       notificacionService.quitarVisibilidad(idNotificacion);
    }
    
}
