/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.service.UsuarioService;
import com.tecnogeek.comprameya.repositories.ComentarioRepository;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author genaro
 */
@Controller
@RequestMapping("/comentario")
@ResponseBody
public class ComentarioController {
    
    @Autowired
    ComentarioRepository comentarioRepository;  
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    UsuarioService uManager;
    
    @RequestMapping(value = "/agregarComentario", method = RequestMethod.POST)
    public String setComentario(@RequestParam(value = "publicacion_id", required = true) long publicacion_id, 
                                              @RequestParam(value = "comentario", required = true) String comentario )  
    {
        
        Usuario u = uManager.getLoggedUser();
        
        Publicacion p = publicacionRepository.getPublicacion(publicacion_id);
        
        Comentario c = new Comentario();
        c.setTexto(comentario);
        c.setFkPublicacion(p);
        c.setFkUsuario(u);
       
        comentarioRepository.save(c);
        
        return "ok";   
    }
    
}

