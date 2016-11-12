/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;
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
    
        
    public Iterable<Mensaje> getMensajeUsuario(long usuario_id,Usuario usuarioLocal,int page)
    {
         return mensajeRepository.getMensajeUsuario(usuario_id,usuarioLocal,page);
    }

    public Iterable<Usuario> getUsuarios(Usuario usuariolocal)
    {
        
        return mensajeRepository.getUsuarios(usuariolocal);
        
    }
    
    public Boolean setMensaje(String titulo,String texto,List<Long> destinatarios){
           
        Usuario usuarioLocal = usuarioRepository.getLoggedUser();
        
        Mensaje mensaje = new Mensaje();
        mensaje.setTexto(texto);
        mensaje.setTitulo(titulo);
        mensaje.setUsuarioEmisor(usuarioLocal);
        
        mensajeRepository.save(mensaje);
        
        List<Destinatario> listades = new ArrayList();
        
        for(Long id : destinatarios)
        {
            Usuario usrdes = new Usuario();
            usrdes.setId(id);
            
            Destinatario destinatario = new  Destinatario(); 
            destinatario.setUsuarioDestinatario(usrdes);
            destinatario.setMensaje(mensaje);
            listades.add(destinatario);
        }
        
        destinatarioRepository.save(listades);
        
        
        return true;
    }
    
     
    
}
