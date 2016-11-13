/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tecnogeek.comprameya.repositories.CategoriaRepository;

/**
 *
 * @author genaro
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController {
   
    @Autowired
    CategoriaRepository categoriaRepository;
    
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public @ResponseBody Iterable<Categoria> getTodoCategoria(Model model)  {
         Iterable<Categoria> catTodo = categoriaRepository.getCategoriaPadres();
         return catTodo; 
    }
    

}


