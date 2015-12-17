/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author genaro
 */
public interface MensajeService extends CrudRepository<Mensaje,Long> {
    
    List<Mensaje> findByfkUsuarioEmisor(Usuario usuario);
    
    List<Mensaje> findBydestinatarioList(Destinatario destinatario);
    
}
