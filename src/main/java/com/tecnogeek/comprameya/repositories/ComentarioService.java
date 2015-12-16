/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author genaro
 */
public interface ComentarioService extends CrudRepository<Comentario,Long>{
    
    List<Comentario> findByfkPublicacion(Publicacion publicacion);
    
}
