/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.dto.SocialSecurityUserDTO;
import com.tecnogeek.comprameya.service.PublicacionService;
import com.tecnogeek.comprameya.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tecnogeek.comprameya.repositories.CategoriaRepository;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.service.VisitaService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author genaro
 */
@Controller
@Slf4j
public class HomeController {

    @Autowired
    PublicacionRepository publicacionService;
    
    @Autowired
    PublicacionService pManager;
    
    @Autowired
    UsuarioService uManager;

    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Autowired
    VisitaService visitaService;

    private final ProviderSignInUtils providerSignInUtils;
    
    @Autowired
    public HomeController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {        
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }
    
    @RequestMapping(value="/visitas",method=RequestMethod.GET) 
    @ResponseBody
    public long getTotalVisitas()
    {           
        return visitaService.getVisitas();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(Model model, HttpServletRequest request) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); 
        
        String ip = request.getRemoteAddr();
        visitaService.nuevaVisita(ip);
        log.info("{} accedió a homepage desde {}",userName,ip);
        return "index";
    }
    
    @ResponseBody
    @RequestMapping(value = "/uploadImg", method = RequestMethod.GET)
    public String uploadImg(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); 
        
        String ip = request.getRemoteAddr();
        log.info("{} intenta cargar una foto desde {}",userName,ip);
        return "ok";
    }

    @RequestMapping(value = {
	    "/misPublicaciones",
	    "/categoria/{cat}/{nivel}",
	    "/busqueda/{terminoBusqueda}",
	    "/vistaProducto/{publicacionId}",
	    "/vistaProducto/{publicacionId}",
	    "/vistaMensaje",
            "/vistaNotificaciones",
            "/terminos",
            "/about",
            "/contratar_tienda",
            "/update_pass",
            "/sorteo"
	}, method = RequestMethod.GET)    
//    @RequestMapping(value = {"/{[path:(?!resources).*}/**"}, method = RequestMethod.GET)
    public String angularRoutes(Model model, HttpServletRequest request) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); 
        
        String ip = request.getRemoteAddr();
        visitaService.nuevaVisita(ip);
        log.info("{} accedió a homepage desde {}",userName,ip);
        return "index";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {        
        model.addAttribute("parametro", "Hola Mundo");
        log.info("Se muestra admin page");
        return "admin";
    }
    
    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    public String spiderPage(Model model) {        
        model.addAttribute("parametro", "Hola Mundo");
        log.info("Se muestra admin page");
        return "spider";
    }

    @RequestMapping(value = "/angular", method = RequestMethod.GET)
    public String angularPage(Model model) {
        System.out.println("AQUI ESTOY");
        model.addAttribute("parametro", "Hola Mundo");
        return "angular";
    }

    @RequestMapping(value = "/modal", method = RequestMethod.GET)
    public String modalPage(Model model) {
        System.out.println("AQUI ESTOY");
        model.addAttribute("parametro", "Hola Mundo");
        return "modal";
    }

    @ResponseBody
    @RequestMapping(value = "/user")    
    public SocialSecurityUserDTO getUsuario(WebRequest request) {    
        
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
             
        if(auth.getPrincipal().equals("anonymousUser")){
            return null;
        }        
        
        SocialSecurityUserDTO user = (SocialSecurityUserDTO) auth.getPrincipal();
        
        if(connection!=null){
            user.setRutaImagen(connection.getImageUrl());
        }
        
        log.info("El usuario {} se ha autenticado", user.getLogin());
        return user;
    }

}
