/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.Predicate;
import com.tecnogeek.comprameya.entidad.Categoria;
import com.tecnogeek.comprameya.entidad.QCategoria;
import com.tecnogeek.comprameya.repositories.CategoriaRepository;
import com.tecnogeek.comprameya.repositories.custom.CategoriaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class CategoriaRepositoryImpl implements CategoriaRepositoryCustom{
    
    @Autowired
    CategoriaRepository categoriaRepository;
    private final QCategoria qCategoria = QCategoria.categoria1;
    
    @Override
    public Iterable<Categoria> getCategoriaPadres(){
        Predicate esPadre = qCategoria.categoria.isNull();
        return categoriaRepository.findAll(esPadre);
    }
    
    @Override
    public Iterable<Categoria> findByFkCategoria(Categoria categoria){
        Predicate byCategoria = qCategoria.categoria.eq(categoria);
        return categoriaRepository.findAll(byCategoria);
    }
    
    @Override
    public Iterable<Categoria> getHijosCategoria(Categoria categoria)
    {
        return findByFkCategoria(categoria);
    }
    
}
