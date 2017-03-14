/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnobitz.core.entidad.Categoria;

/**
 *
 * @author alexander
 */
public interface CategoriaRepositoryCustom {
    
    public Iterable<Categoria> getCategoriaPadres();
    
    public Iterable<Categoria> findByFkCategoria(Categoria categoria);
    
    public Iterable<Categoria> getHijosCategoria(Categoria categoria);
    
}
