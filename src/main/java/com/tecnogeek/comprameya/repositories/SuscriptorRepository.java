/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author genaro
 */
public interface SuscriptorRepository extends CrudRepository<Suscriptor,Long> {
 
    List<Suscriptor> findByfkUsuarioProveedor(Usuario usuario);
    
    List<Suscriptor> findByfkUsuarioSuscriptor(Usuario usuario);
    
}
