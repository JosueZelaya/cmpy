/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnobitz.core.entidad.Recurso;
import com.tecnogeek.comprameya.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author alexander
 */

@Controller
@RequestMapping("/recurso")
@ResponseBody
public class RecursoController {
    
    @Autowired
    RecursoService recursoService; 
 
    @RequestMapping(value="/getRecursos/{idPublicacion}/{page}",method=RequestMethod.GET)    
    public Iterable<Recurso> getComentarios(@PathVariable Long idPublicacion,@PathVariable int page, Model model)
    {                
        int recursosByPage = 20;
        return recursoService.getRecursos(idPublicacion, page, recursosByPage);
    }
    
}
