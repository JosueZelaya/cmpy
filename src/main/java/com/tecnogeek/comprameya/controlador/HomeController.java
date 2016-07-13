/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.dto.SocialSecurityUserDTO;
import com.tecnogeek.comprameya.entidad.Categoria;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.service.CategoriaService;
import com.tecnogeek.comprameya.service.PublicacionService;
import com.tecnogeek.comprameya.service.UsuarioService;
import java.util.List;
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
public class HomeController {

    @Autowired
    PublicacionRepository publicacionService;
    
    @Autowired
    PublicacionService pManager;
    
    @Autowired
    UsuarioService uManager;

    //genaro req categorias
    @Autowired
    CategoriaRepository categoriaService;
    @Autowired
    CategoriaService cManager;
    //fin req categorias

    private ProviderSignInUtils providerSignInUtils;
    
    @Autowired
    public HomeController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {        
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(Model model) {
        List<Publicacion> anuncios = pManager.getAnunciosAleatorios(Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR, Constantes.PUBLICACION_PAGADA);
//        List<Publicacion> publicaciones = pManager.getAnunciosAleatorios(Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR,Constantes.PUBLICACION_GRATIS);
//        GridResponse<Publicacion> gridPublicaciones = pManager.getPublicacionesGrid(0, Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR, new Sort(Sort.Direction.DESC, "sisFechaCreacion"), Constantes.PUBLICACION_GRATIS);
//        List<Publicacion> publicaciones = pManager.getPublicaciones(new PageRequest(0,Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR, new Sort(Sort.Direction.DESC,"sisFechaCreacion")), Constantes.PUBLICACION_GRATIS);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username
        //genaro req categorias
        List<Categoria> categorias = cManager.getCategoria();
        model.addAttribute("categorias", categorias);
        //fin req categorias

        model.addAttribute("username", userName);
        model.addAttribute("anuncios", anuncios);
//        model.addAttribute("publicaciones", gridPublicaciones.getRows());
//        model.addAttribute("totalPages", gridPublicaciones.getTotalPages());
        model.addAttribute("tipoPublicacion", Constantes.PUBLICACION_GRATIS);
        model.addAttribute("parametro", "Pagina Inicio");
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        System.out.println("AQUI ESTOY");
        model.addAttribute("parametro", "Hola Mundo");
        return "admin";
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
             
        SocialSecurityUserDTO user = (SocialSecurityUserDTO) auth.getPrincipal();
        
        if(connection!=null){
            user.setRutaImagen(connection.getImageUrl());
        }
        
        return user;
    }

}
