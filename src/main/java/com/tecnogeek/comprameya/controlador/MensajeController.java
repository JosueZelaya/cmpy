/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Destinatario;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.managers.ManejadorDestinatario;
import com.tecnogeek.comprameya.managers.ManejadorMensaje;
import com.tecnogeek.comprameya.managers.ManejadorUsuario;
import com.tecnogeek.comprameya.pojo.pojoDestinatario;
import com.tecnogeek.comprameya.pojo.pojoEmisor;
import com.tecnogeek.comprameya.pojo.pojoMensaje;
import com.tecnogeek.comprameya.pojo.pojoUsuario;
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
    ManejadorMensaje mManager;
    @Autowired
    ManejadorDestinatario dManager;
    @Autowired
    ManejadorUsuario uManager;
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody List<pojoMensaje> getMensajeUsuario(@PathVariable("id") long id)  {          
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        
        Usuario usr_local = uManager.getUserByLogin(userName);
        Usuario usr_remoto = uManager.getUsuario(id);
        
        List<Mensaje> mensajes = new  ArrayList<>();
        
        List<Destinatario> des_local = dManager.getDestinarioUsuario(usr_local,usr_remoto);
        List<Destinatario> des_remoto = dManager.getDestinarioUsuario(usr_remoto,usr_local);
        
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
        
        Usuario usr_local = uManager.getUserByLogin(userName);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setTitulo(pMensaje.getTitulo());
        mensaje.setTexto(pMensaje.getMensaje());
        mensaje.setFkUsuarioEmisor(usr_local);
        
        List<Destinatario> ldest = new ArrayList<>();
        
        for(pojoDestinatario pdest: pMensaje.destinatarios)
        {
            Destinatario dest = new Destinatario();
            
            dest.setFkUsuarioDestinatario(uManager.getUsuario(pdest.getId()));
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
        
        Usuario usr_local = uManager.getUserByLogin(userName);
        
        List<Usuario> lista_usuarios = new ArrayList<>();
        
       List<Destinatario> des = new ArrayList<>();
       
       des = dManager.getDestinario(usr_local);
       
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
       
       
              
        return uManager.getUsuarioPojo(lista_usuarios);   
    }
  
    
    private List<pojoMensaje> getMensajePojo(List<Mensaje> lista)
    {
        List<pojoMensaje> lista_pojo = new ArrayList<>();
        
        for(Mensaje men : lista)
        {
            pojoMensaje pmensaje = new pojoMensaje();
            pmensaje.setId(men.getMensajeId());
            pmensaje.setTitulo(men.getTitulo());
            pmensaje.setMensaje(men.getTexto());
            
            pojoEmisor pemisor = new pojoEmisor();
            pemisor.setId(men.getFkUsuarioEmisor().getUsuarioId());
            pemisor.setLogin(men.getFkUsuarioEmisor().getLogin());
           
            pmensaje.setEmisor(pemisor);
            
            List<pojoDestinatario> lpdestinario = new ArrayList<>();
            
            for(Destinatario des : men.getDestinatarioList())
            {
                pojoDestinatario pdestinario = new pojoDestinatario();
                pdestinario.setId(des.getFkUsuarioDestinatario().getUsuarioId());
                pdestinario.setLogin(des.getFkUsuarioDestinatario().getLogin());
                
                lpdestinario.add(pdestinario);
            }
            
            pmensaje.setDestinatarios(lpdestinario);

            lista_pojo.add(pmensaje);
        }
        
        return lista_pojo;
        
    }
    
    
}
