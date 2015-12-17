/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.DestinatarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author genaro
 */
public class ManejadorDestinatario {
    
     @Autowired
     DestinatarioService destinatarioService;
     
     public List<Destinatario> getDestinarioUsuario(Usuario usuario1, Usuario usuario2)
     {
         return destinatarioService.findByfkUsuarioDesEmi(usuario1,usuario2);
     }
     
     public List<Destinatario> getDestinario(Usuario usuario)
     {
         return destinatarioService.findByfkUsuarioDes(usuario);
     }
    
}
