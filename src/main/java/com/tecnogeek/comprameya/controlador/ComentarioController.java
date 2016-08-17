/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.service.ComentarioService;
import com.tecnogeek.comprameya.service.PublicacionService;
import com.tecnogeek.comprameya.service.UsuarioService;
import com.tecnogeek.comprameya.dto.pojoComentario;
import com.tecnogeek.comprameya.dto.pojoUsuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author genaro
 */
@Controller
@RequestMapping("/comentario")
public class ComentarioController {
    
    @Autowired
    ComentarioService cManager;
    @Autowired
    PublicacionService pManager;
    @Autowired
    UsuarioService uManager;
    
    @RequestMapping(value = "/set/{publicacion_id}", method = RequestMethod.POST)
    public @ResponseBody String setUbicacionPublicacion(@PathVariable("publicacion_id") long publicacion_id, @RequestBody pojoComentario pcomentario )  {
        
        Usuario u = uManager.getLoggedUser();
        
        Publicacion p = pManager.getPublicacion(publicacion_id);
        
        Comentario c = new Comentario();
        c.setTexto(pcomentario.getTexto());
        c.setFkPublicacion(p);
        c.setFkUsuario(u);
       
        cManager.setComentario(c);
        
        return "";   
    }  
    
    @RequestMapping(value = "/get/{publicacion_id}", method = RequestMethod.GET)
    public @ResponseBody List<pojoComentario> setUbicacionPublicacion(@PathVariable("publicacion_id") long publicacion_id)  {
          
        Publicacion p = pManager.getPublicacion(publicacion_id);
        
        Iterable<Comentario> lcomentario = cManager.getComentario(p);
        return  getComentarioPojo(lcomentario);
    }
    
    
    private List<pojoComentario> getComentarioPojo(Iterable<Comentario> lcomentario)
    {
        List<pojoComentario> lpcomentario = new ArrayList<>();
        
        for(Comentario c : lcomentario)
        {
            pojoComentario p = new pojoComentario();
            p.setId(c.getComentarioId());
            p.setTexto(c.getTexto());
            p.setPuntaje(c.getPuntaje());
            
            pojoUsuario pu = new pojoUsuario();
            pu.setId(c.getFkUsuario().getId());
            pu.setLogin(c.getFkUsuario().getLogin());
            
            p.setUsuario(pu);          
            
            lpcomentario.add(p);
            
        }
        
        return lpcomentario;
        
    }
    
}

