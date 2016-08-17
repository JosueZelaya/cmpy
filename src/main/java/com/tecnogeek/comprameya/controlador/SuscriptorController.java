/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;


import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.service.SuscriptorService;
import com.tecnogeek.comprameya.service.UsuarioService;
import com.tecnogeek.comprameya.dto.pojoUsuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author genaro
 */
@Controller
@RequestMapping("/suscripcion")
public class SuscriptorController {
    @Autowired
    SuscriptorService sManager;
    @Autowired
    UsuarioService uManager;
    
    @RequestMapping(value = "/suscriptor/get", method = RequestMethod.GET)
    public @ResponseBody List<pojoUsuario> getSuscriptores()  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario usr_local = uManager.findActiveUserByLogin(userName);
        
        Iterable<Suscriptor> lsuscriptor = new ArrayList<>();
        lsuscriptor = sManager.getSuscriptor(usr_local);
        
        List<Usuario> lusr  = new ArrayList<>();
        
        for(Suscriptor sus : lsuscriptor )
        {
            lusr.add(sus.getFkUsuarioSuscriptor());
        }
        
        return uManager.getUsuarioPojo(lusr);
        
    }
    
    @RequestMapping(value = "/proveedor/get", method = RequestMethod.GET)
    public @ResponseBody List<pojoUsuario> getProveedores()  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario usr_local = uManager.findActiveUserByLogin(userName);
        
        Iterable<Suscriptor> lsuscriptor = new ArrayList<>();
        lsuscriptor = sManager.getProveedor(usr_local);
        
        List<Usuario> lusr  = new ArrayList<>();
        
        for(Suscriptor sus : lsuscriptor )
        {
            lusr.add(sus.getFkUsuarioProveedor());
        }
        
        return uManager.getUsuarioPojo(lusr);
    }
    
    
    @RequestMapping(value = "/suscriptor/set/{id}", method = RequestMethod.GET)
    public @ResponseBody Object setSuscriptor(@PathVariable("id") long id)  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario usr_local = uManager.findActiveUserByLogin(userName);
        
        Usuario usr_pro = uManager.getUsuario(id);
        
        Suscriptor sus = new Suscriptor();
        sus.setFkUsuarioProveedor(usr_pro);
        sus.setFkUsuarioSuscriptor(usr_local);
        
        sManager.setSuscriptor(sus);
        
        return null;
        
    }
    
    
    
    
}
