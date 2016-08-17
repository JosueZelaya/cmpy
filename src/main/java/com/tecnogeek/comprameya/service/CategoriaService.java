/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Categoria;
import com.tecnogeek.comprameya.entidad.QCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.CategoriaRepository;

/**
 *
 * @author genaro
 */

@Service
public class CategoriaService {
    
    
    public CategoriaService(){}
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    private final QCategoria qCategoria = QCategoria.categoria;
    
    public Iterable<Categoria> getCategoriaPadres(){
        Predicate esPadre = qCategoria.fkCategoria.isNull();
        return categoriaRepository.findAll(esPadre);
    }
    
    public Iterable<Categoria> findByFkCategoria(Categoria categoria){
        Predicate byCategoria = qCategoria.fkCategoria.eq(categoria);
        return categoriaRepository.findAll(byCategoria);
    }
    
    public Iterable<Categoria> getHijosCategoria(Categoria categoria)
    {
        return findByFkCategoria(categoria);
    }
}
