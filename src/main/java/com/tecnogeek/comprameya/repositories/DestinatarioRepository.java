/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.Destinatario;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author genaro
 */
public interface DestinatarioRepository extends CrudRepository<Destinatario, Long>, 
        QueryDslPredicateExecutor<Destinatario> {}
