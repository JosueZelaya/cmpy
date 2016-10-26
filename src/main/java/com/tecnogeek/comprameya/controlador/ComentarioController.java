/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.service.UsuarioService;
import com.tecnogeek.comprameya.repositories.ComentarioRepository;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    ComentarioService comentarioService;
    
    @RequestMapping(value="/getComentarios/{idPublicacion}/{page}",method=RequestMethod.GET)    
    public Iterable<Comentario> getComentarios(@PathVariable Long idPublicacion,@PathVariable int page, Model model)
    {                
        int commentsByPage = 20;
        return comentarioService.getComentarios(idPublicacion, page, commentsByPage);
    }
    
    @RequestMapping(value = "/agregarComentario", method = RequestMethod.POST)
    public String setComentario(@RequestParam(value = "publicacion_id", required = true) long publicacion_id, 
                                              @RequestParam(value = "comentario", required = true) String comentario )  
    {
        
        comentarioService.save(publicacion_id, comentario);
        
        return "ok";   
    }
    
}

