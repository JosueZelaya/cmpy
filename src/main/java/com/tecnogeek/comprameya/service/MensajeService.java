/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.MensajeRepository;

/**
 *
 * @author genaro
 */

@Service
public class MensajeService {
    public MensajeService(){}
    
    @Autowired
    MensajeRepository mensajeRepository;
    
        
    public List<Mensaje> getMensajeUsuario(Destinatario destinatario)
    {
         return null;
    }
    
    public Object setMensajes(Mensaje mensaje)
    {
        
        mensajeRepository.save(mensaje);
        
        return null;
        
    }
    
     
    
}
