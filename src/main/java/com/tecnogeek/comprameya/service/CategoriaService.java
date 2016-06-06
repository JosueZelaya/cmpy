/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Categoria;
import java.util.List;
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
    CategoriaRepository categoriaService;
    
    public List<Categoria> getCategoria()
    {
        return (List<Categoria>) categoriaService.getCategoriaPadres();
    }
    
    public List<Categoria> getHijosCategoria(Categoria categoria)
    {
        return (List<Categoria>) categoriaService.findByFkCategoria(categoria);
    }
}
