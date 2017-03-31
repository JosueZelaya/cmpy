/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnobitz.cmpy.entidad.Destinatario;
import com.tecnobitz.cmpy.entidad.Mensaje;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.enums.TipoNotificacionEnum;
import com.tecnogeek.comprameya.repositories.DestinatarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.MensajeRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.ArrayList;

/**
 *
 * @author genaro
 */

@Service
public class MensajeService {
    public MensajeService(){}
    
    @Autowired
    MensajeRepository mensajeRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired 
    DestinatarioRepository destinatarioRepository;
    @Autowired
    NotificacionService notificacionService;
        
    public Iterable<Mensaje> getMensajeUsuario(long usuario_id,Usuario usuarioLocal,int page)
    {
         return mensajeRepository.getMensajeUsuario(usuario_id,usuarioLocal,page);
    }

    public Iterable<Usuario> getUsuarios(Usuario usuariolocal)
    {
        
        return mensajeRepository.getUsuarios(usuariolocal);
        
    }
    
    public Boolean setMensaje(String titulo, String texto, List<Long> destinatarios){
           
        Usuario emisor = usuarioRepository.getLoggedUser();
        
        Mensaje mensaje = new Mensaje();
        mensaje.setTexto(texto);
        mensaje.setTitulo(titulo);
        mensaje.setUsuarioEmisor(emisor);
        mensaje.setVisto(false);
        
        mensajeRepository.save(mensaje);
        
        List<Destinatario> listades = new ArrayList();
        List<Usuario> usuariosNotificar = new ArrayList();
        
        for(Long id : destinatarios)
        {
            Usuario usrdes = usuarioRepository.findOne(id);
            usuariosNotificar.add(usrdes);
            Destinatario destinatario = new  Destinatario(); 
            destinatario.setUsuarioDestinatario(usrdes);
            destinatario.setMensaje(mensaje);
            listades.add(destinatario);
        }
        
        destinatarioRepository.save(listades);
        
        String strMensaje = mensaje.getUsuarioEmisor().getPersona().getNombre() + ": " + texto;
        
        strMensaje = (strMensaje.length() > 50 )?
                strMensaje.substring(0, 45) + "...":
                strMensaje;
        
        String link = "{\"usuarioId\": \""+mensaje.getUsuarioEmisor().getId()
                +"\" ,\"usuarioNombre\": \""+mensaje.getUsuarioEmisor().getPersona().getNombre()
                +"\" ,\"asunto\": \""+mensaje.getTitulo() + "\"}";
        notificacionService.enviarNotificaciones(emisor, usuariosNotificar, strMensaje, link, TipoNotificacionEnum.MENSAJE);
        
        return true;
    }
    
     public Iterable<Mensaje> getMensajeNoLeido(Usuario usuarioLocal,int page) {
     
         return mensajeRepository.getMensajeNoLeido(usuarioLocal, page);
     
     }
     
     public Long getMensajeUsuarioNoleidoTotal(Long usuarioId,Usuario usuarioLocal){
         return mensajeRepository.getMensajeUsuarioNoleidoTotal(usuarioId, usuarioLocal);
     }
     
     public Long getMensajeNoLeidoTotal(Usuario usuarioLocal){
         return mensajeRepository.getMensajeNoLeidoTotal(usuarioLocal);
     }
     
     public Boolean setMensajeUsuarioLeido(Long usuarioId,Usuario usuarioLocal){
         Iterable<Mensaje> mensajes = mensajeRepository.getMensajeUsuarioNoleido(usuarioId, usuarioLocal);
         
         for(Mensaje m: mensajes)
         {
             m.setVisto(true);
            mensajeRepository.save(m);
         }
         
         return true;
         
     }
    
}
