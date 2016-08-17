/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;
import com.tecnogeek.comprameya.entidad.Categoria;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author genaro
 */
public interface CategoriaRepository extends CrudRepository<Categoria,Long>, 
        QueryDslPredicateExecutor<Categoria> {}
