/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author genaro
 */
public interface DestinatarioService extends CrudRepository<Destinatario,Long> { 
    
    @Query("select d from Destinatario d where d.fkUsuarioDestinatario= ?1 and d.fkMensaje.fkUsuarioEmisor = ?2")
    List<Destinatario> findByfkUsuarioDesEmi(Usuario usuario1, Usuario usuario2);
    
    @Query("select d from Destinatario d where d.fkUsuarioDestinatario = ?1 or d.fkMensaje.fkUsuarioEmisor = ?1")
    List<Destinatario> findByfkUsuarioDes(Usuario usuario);

}
