/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Perfil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jzelaya
 */
public interface PerfilRepository extends CrudRepository<Perfil,Long> {
    
//    SELECT usr.login,pfl.nombre FROM usuario usr,perfil pfl WHERE usr.fk_perfil=pfl.perfil_id
//                    AND usr.login= ?
    
    @Query("select p from Perfil p,Usuario u where u.fkPerfil=p.perfilId and u.login=?1")
    Perfil getPerfilUsuario(String login);
    
}
