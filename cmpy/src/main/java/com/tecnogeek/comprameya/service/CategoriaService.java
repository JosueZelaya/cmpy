/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnobitz.cmpy.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author genaro
 */

@Service
public class CategoriaService {
    
    
    public CategoriaService(){}
    
    @Autowired
    CategoriaRepository categoriaRepository;
        
}
