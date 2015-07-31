/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.repositories.PublicacionService;
import com.tecnogeek.comprameya.utils.Numbers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    PublicacionService publicacionService;
    
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String welcomePage(Model model)
    {
        System.out.println("AQUI ESTOY");        
        int pageZise = 4;
        int totalPublicaciones = publicacionService.getTotalPublicacionesPagadas();        
        int sum = (totalPublicaciones % pageZise == 0) ? 0 : 1;
        int totalPages = totalPublicaciones / pageZise + sum;
        int page = Numbers.randomInt(0,totalPages-1); //dado que la primer pagina en la BD es cero.
        
        System.out.println("PAGE: "+page+" PAGESIZE: "+pageZise+" totalPublicaciones: "+totalPublicaciones+" totalPages: "+totalPages);
        
        List<Publicacion> publicaciones = publicacionService.getPublicacionesPagadas(new PageRequest(page,pageZise));
        
        for (Publicacion publicacion : publicaciones){
            System.out.println("titulo: "+publicacion.getTitulo());
            List<Recurso> recursos = publicacion.getRecursoList();
            for(Recurso recurso : recursos){
                System.out.println("recurso: "+recurso.getRuta());
            }            
        }        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username
        
        model.addAttribute("username", userName);
        model.addAttribute("publicaciones", publicaciones);
        model.addAttribute("parametro", "Pagina Inicio");
        return "boot";
    }
    
    @RequestMapping(value="/admin",method=RequestMethod.GET)
    public String adminPage(Model model)
    {   
        System.out.println("AQUI ESTOY");
        model.addAttribute("parametro", "Hola Mundo");
        return "admin";
    }
    
}