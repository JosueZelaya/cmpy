/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.MensajeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author genaro
 */

@Service
public class ManejadorMensaje {
    public ManejadorMensaje(){}
    
    @Autowired
    MensajeService mensajeService;
    
        
    public List<Mensaje> getMensajeUsuario(Destinatario destinatario)
    {
         List<Mensaje> mensajes =  mensajeService.findBydestinatarioList(destinatario);
       
         return mensajes;
    }
    
    public Object setMensajes(Mensaje mensaje)
    {
        
        mensajeService.save(mensaje);
        
        return null;
        
    }
    
     
    
}
