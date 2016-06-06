/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
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
     DestinatarioRepository destinatarioService;
     
     public List<Destinatario> getDestinarioUsuario(Usuario usuario1, Usuario usuario2)
     {
         return destinatarioService.findByfkUsuarioDesEmi(usuario1,usuario2);
     }
     
     public List<Destinatario> getDestinario(Usuario usuario)
     {
         return destinatarioService.findByfkUsuarioDes(usuario);
     }
    
}
