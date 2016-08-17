/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.QDestinatario;
import com.tecnogeek.comprameya.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.DestinatarioRepository;

/**
 *
 * @author genaro
 */

@Service
public class DestinatarioService {
    
     @Autowired
     DestinatarioRepository destinatarioRepository;
     
     private final QDestinatario qDestinatario = QDestinatario.destinatario;
     
     public Iterable<Destinatario> getDestinarioUsuario(Usuario destinatario, Usuario emisor)
     {
         BooleanExpression byDestinatario = qDestinatario.fkUsuarioDestinatario.eq(destinatario);
         BooleanExpression byEmisor = qDestinatario.fkMensaje.fkUsuarioEmisor.eq(emisor);
         return destinatarioRepository.findAll(byDestinatario.and(byEmisor));
     }
     
     public Iterable<Destinatario> getDestinario(Usuario usuario)
     {
         BooleanExpression byDestinatario = qDestinatario.fkUsuarioDestinatario.eq(usuario);
         BooleanExpression byEmisor = qDestinatario.fkMensaje.fkUsuarioEmisor.eq(usuario);
         return destinatarioRepository.findAll(byDestinatario.or(byEmisor));
     }
    
}
