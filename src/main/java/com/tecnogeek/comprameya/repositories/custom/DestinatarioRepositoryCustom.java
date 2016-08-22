/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Usuario;

/**
 *
 * @author alexander
 */
public interface DestinatarioRepositoryCustom {
    
    public Iterable<Destinatario> getDestinarioUsuario(Usuario destinatario, Usuario emisor);
     
     public Iterable<Destinatario> getDestinario(Usuario usuario);
    
}
