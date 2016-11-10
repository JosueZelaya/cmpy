/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;


/**
 *
 * @author alexander
 */
public interface MensajeRepositoryCustom {
    
    Iterable<Mensaje> getMensajeUsuario(long usuario_id,Usuario usuarioLocal);
    Iterable<Usuario> getUsuarios(Usuario usuariolocal);
    
}
