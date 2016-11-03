/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.NotificacionRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    NotificacionRepository notificacionRepository;
    
    @RequestMapping(value="/getNotificaciones/",method=RequestMethod.GET)  
    public List<Notificacion> getNotificaciones(){
        Usuario usuario = usuarioRepository.getLoggedUser();
        
        if(usuario==null){
            return null;
        }
        
        return usuario.getNotificacionesList();
    }
    
    @RequestMapping(value="/getTotalNotificaciones/",method=RequestMethod.GET)  
    public Integer getTotalNotificaciones(){
        Usuario usuario = usuarioRepository.getLoggedUser();
        
        if(usuario==null){
            return null;
        }
        
        return usuario.getNotificacionesList().size();
    }
    
}
