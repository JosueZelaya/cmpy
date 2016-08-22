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
import com.tecnogeek.comprameya.dto.pojoDestinatario;
import com.tecnogeek.comprameya.dto.pojoEmisor;
import com.tecnogeek.comprameya.dto.pojoMensaje;
import com.tecnogeek.comprameya.dto.pojoUsuario;
import com.tecnogeek.comprameya.repositories.DestinatarioRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/mensaje")
public class MensajeController {
    @Autowired
    MensajeService mManager;
    @Autowired
     DestinatarioRepository destinatarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody List<pojoMensaje> getMensajeUsuario(@PathVariable("id") long id)  {          
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        
        Usuario usr_local = usuarioRepository.findActiveUserByLogin(userName);
        Usuario usr_remoto = usuarioRepository.findOne(id);
        
        List<Mensaje> mensajes = new  ArrayList<>();
        
        Iterable<Destinatario> des_local = destinatarioRepository.getDestinarioUsuario(usr_local,usr_remoto);
        Iterable<Destinatario> des_remoto = destinatarioRepository.getDestinarioUsuario(usr_remoto,usr_local);
        
        for(Destinatario des : des_local)
        {
            mensajes.addAll(mManager.getMensajeUsuario(des));
        }

        for(Destinatario des : des_remoto)
        {
            mensajes.addAll(mManager.getMensajeUsuario(des));
        }       

        
        return getMensajePojo(mensajes);   
    }
    
    
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public @ResponseBody Object setMensaje(@RequestBody pojoMensaje pMensaje)  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        
        Usuario usr_local = usuarioRepository.findActiveUserByLogin(userName);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setTitulo(pMensaje.getTitulo());
        mensaje.setTexto(pMensaje.getMensaje());
        mensaje.setFkUsuarioEmisor(usr_local);
        
        List<Destinatario> ldest = new ArrayList<>();
        
        for(pojoDestinatario pdest: pMensaje.destinatarios)
        {
            Destinatario dest = new Destinatario();
            
            dest.setFkUsuarioDestinatario(usuarioRepository.findOne(pdest.getId()));
            dest.setFkMensaje(mensaje);
            
            
            ldest.add(dest);
        }

        mensaje.setDestinatarioList(ldest);
        
        mManager.setMensajes(mensaje);
              
        return "exito";   
    }
    
    
    
    
    @RequestMapping(value = "/usuarios/get", method = RequestMethod.GET)
    public @ResponseBody List<pojoUsuario> getUsuarios() {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        
        Usuario usr_local = usuarioRepository.findActiveUserByLogin(userName);
        
        List<Usuario> lista_usuarios = new ArrayList<>();
        
       Iterable<Destinatario> des = new ArrayList<>();
       
       des = destinatarioRepository.getDestinario(usr_local);
       
       for(Destinatario d : des)
       {
           if(!d.getFkUsuarioDestinatario().equals(usr_local))
           {
              
               if(!lista_usuarios.contains(d.getFkUsuarioDestinatario())){
                    lista_usuarios.add(d.getFkUsuarioDestinatario());
               }

           }
           else if (!d.getFkMensaje().getFkUsuarioEmisor().equals(usr_local))
           {
                if(!lista_usuarios.contains(d.getFkMensaje().getFkUsuarioEmisor())){
                    lista_usuarios.add(d.getFkMensaje().getFkUsuarioEmisor());
                }
             
           }
           
       }        
        
        List<pojoUsuario> lpUsuario = new ArrayList<>();

        for (Usuario usr : lista_usuarios) {
            pojoUsuario p = new pojoUsuario();
            p.setId(usr.getId());
            p.setLogin(usr.getLogin());

            lpUsuario.add(p);
        }

        return lpUsuario;
        
    }
  
    
    private List<pojoMensaje> getMensajePojo(List<Mensaje> lista)
    {
        List<pojoMensaje> lista_pojo = new ArrayList<>();
        
        for(Mensaje men : lista)
        {
            pojoMensaje pmensaje = new pojoMensaje();
            pmensaje.setId(men.getId());
            pmensaje.setTitulo(men.getTitulo());
            pmensaje.setMensaje(men.getTexto());
            
            pojoEmisor pemisor = new pojoEmisor();
            pemisor.setId(men.getFkUsuarioEmisor().getId());
            pemisor.setLogin(men.getFkUsuarioEmisor().getLogin());
           
            pmensaje.setEmisor(pemisor);
            
            List<pojoDestinatario> lpdestinario = new ArrayList<>();
            
            for(Destinatario des : men.getDestinatarioList())
            {
                pojoDestinatario pdestinario = new pojoDestinatario();
                pdestinario.setId(des.getFkUsuarioDestinatario().getId());
                pdestinario.setLogin(des.getFkUsuarioDestinatario().getLogin());
                
                lpdestinario.add(pdestinario);
            }
            
            pmensaje.setDestinatarios(lpdestinario);

            lista_pojo.add(pmensaje);
        }
        
        return lista_pojo;
        
    }
    
    
}
