/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;
import com.tecnogeek.comprameya.dao.AbstractGenericDAO;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Sistema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author genaro
 */
@Controller
public class HomeController 
{
    @Autowired
    AbstractGenericDAO sisdao;
    
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String welcomePage(Model model)
    {
        System.out.println("AQUI ESTOY");
        
        List<Publicacion> publicaciones = sisdao.findAll(Publicacion.class);
        model.addAttribute("publicaciones", publicaciones);
        model.addAttribute("parametro", "Pagina Inicio");
        return "index";
    }
    
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String indexPublico(Model model)
    {   
        @SuppressWarnings("unchecked")
        List<Sistema> sistemas = sisdao.findAll(Sistema.class);
        for (Sistema sistema : sistemas) {
                System.out.println("hola, este es mi id: "+sistema.getSistemaId());                
        }
        System.out.println("AQUI ESTOY");
        model.addAttribute("parametro", "Hola Mundo");
        return "index";
    }
    
}