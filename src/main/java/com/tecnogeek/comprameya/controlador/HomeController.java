/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.Sistema;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import com.tecnogeek.comprameya.repositories.PublicacionService;
import com.tecnogeek.comprameya.utils.FileManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.security.krb5.internal.ktab.KeyTabConstants;

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
        int limit = Math.round(totalPublicaciones/pageZise);        
        Random rnd = new Random();
        int page = (int)Math.ceil((rnd.nextDouble() * limit + 1));

        List<Publicacion> publicaciones = publicacionService.findAll(new PageRequest(page,pageZise)).getContent();
        
        for (Publicacion publicacion : publicaciones){
            System.out.println("titulo: "+publicacion.getTitulo());
            List<Recurso> recursos = publicacion.getRecursoList();
            for(Recurso recurso : recursos){
                System.out.println("recurso: "+recurso.getRuta());
            }            
        }        
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