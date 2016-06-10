/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Categoria;
import com.tecnogeek.comprameya.service.CategoriaService;
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
    CategoriaRepository categoriaService;
    @Autowired
    CategoriaService cManager;
   

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public @ResponseBody List<Object> getTodoCategoria(Model model)  {
         List<Categoria> catTodo = cManager.getCategoria();
         List < Object > catObject = new ArrayList();
         for(Categoria cat : catTodo)
         {
             catObject.add(getCatMin(cat, getHijos(cat)));
         }
         return catObject ;   
    }
    
    private List<Object> getHijos(Categoria categoria)
    {
        List<Categoria> catHijos = cManager.getHijosCategoria(categoria);
        List<Object> catObject = new ArrayList<>();
        for(Categoria cat : catHijos)
        {
            catObject.add(getCatMin(cat, getHijos(cat)));
        }
        return catObject;
    }  
    
    private Object getCatMin( final Categoria catPadre, final List<Object> catHijos) 
    {
            Object obj;
            obj = new Object() {
               public Long id = catPadre.getCategoriaId();
               public String nombre = catPadre.getNombre();
               public String descripcion = catPadre.getDescripcion();
               public Object hijos = catHijos;
            };
     
         return obj;
         
    }

}


