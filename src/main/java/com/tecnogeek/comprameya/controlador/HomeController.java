/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.dto.SocialSecurityUserDTO;
import com.tecnogeek.comprameya.entidad.Categoria;
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

    @Autowired
    CategoriaRepository categoriaRepository;

    private final ProviderSignInUtils providerSignInUtils;
    
    @Autowired
    public HomeController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {        
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(Model model) {
//        Iterable<Publicacion> anuncios = pManager.getAnunciosAleatorios(Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR, TipoPublicacionEnum.PAGADA);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); 
        
        Iterable<Categoria> categorias = categoriaRepository.getCategoriaPadres();
        model.addAttribute("categorias", categorias);

        model.addAttribute("username", userName);
//        model.addAttribute("anuncios", anuncios);
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
