/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.service.MensajeService;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/mensaje")
public class MensajeController {
    @Autowired
    MensajeService mensajeService;
    @Autowired
    UsuarioRepository usuarioRepository;
    
    
    @RequestMapping(value = "/get/{usuarioId}/{page}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Mensaje> getMensajeUsuario(@PathVariable("usuarioId") long usuarioId,@PathVariable("page") int page)  {          
        
        Usuario usuarioLocal = usuarioRepository.getLoggedUser();
        
        Iterable<Mensaje> resul = mensajeService.getMensajeUsuario(usuarioId,usuarioLocal,page);
        
        return resul;

    }
    
    
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public @ResponseBody Boolean setMensaje(@RequestParam(value = "titulo", required = true) String titulo,
                                @RequestParam(value = "texto", required = true) String texto,
                                @RequestParam(value = "destinatarios", required = true) List<Long> destinatarios,
                                Model model)  {
        

        mensajeService.setMensaje(titulo, texto, destinatarios);

        
        
        return true;   
    }
    

    @RequestMapping(value = "/usuarios/get", method = RequestMethod.GET)
    public @ResponseBody Iterable<Usuario> getUsuarios() {          

        Usuario usuarioLocal = usuarioRepository.getLoggedUser();
        Iterable<Usuario> res = mensajeService.getUsuarios(usuarioLocal);
        return res;
        
    }

}
