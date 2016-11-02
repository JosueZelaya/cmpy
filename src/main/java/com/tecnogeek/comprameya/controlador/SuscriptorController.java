/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;


import com.tecnogeek.comprameya.entidad.SuscripcionUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.dto.pojoUsuario;
import com.tecnogeek.comprameya.repositories.SuscriptorRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
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
    SuscriptorRepository suscriptorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @RequestMapping(value = "/suscriptor/get", method = RequestMethod.GET)
    public @ResponseBody List<pojoUsuario> getSuscriptores()  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario usr_local = usuarioRepository.findActiveUserByLogin(userName);
        
        Iterable<SuscripcionUsuario> lsuscriptor = new ArrayList<>();
        lsuscriptor = suscriptorRepository.getSuscriptor(usr_local);
        
        List<Usuario> lusr  = new ArrayList<>();
        
        for(SuscripcionUsuario sus : lsuscriptor )
        {
            lusr.add(sus.getUsuarioSuscriptor());
        }
        
        List<pojoUsuario> lpUsuario = new ArrayList<>();

        for (Usuario usr : lusr) {
            pojoUsuario p = new pojoUsuario();
            p.setId(usr.getId());
            p.setLogin(usr.getLogin());

            lpUsuario.add(p);
        }

        return lpUsuario;
        
        
    }
    
    @RequestMapping(value = "/proveedor/get", method = RequestMethod.GET)
    public @ResponseBody List<pojoUsuario> getProveedores()  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario usr_local = usuarioRepository.findActiveUserByLogin(userName);
        
        Iterable<SuscripcionUsuario> lsuscriptor = new ArrayList<>();
        lsuscriptor = suscriptorRepository.getProveedor(usr_local);
        
        List<Usuario> lusr  = new ArrayList<>();
        
        for(SuscripcionUsuario sus : lsuscriptor )
        {
            lusr.add(sus.getUsuarioProveedor());
        }
        
        List<pojoUsuario> lpUsuario = new ArrayList<>();

        for (Usuario usr : lusr) {
            pojoUsuario p = new pojoUsuario();
            p.setId(usr.getId());
            p.setLogin(usr.getLogin());

            lpUsuario.add(p);
        }

        return lpUsuario;
        
    }
    
    
    @RequestMapping(value = "/suscriptor/set/{id}", method = RequestMethod.GET)
    public @ResponseBody Object setSuscriptor(@PathVariable("id") long id)  {          
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario usr_local = usuarioRepository.findActiveUserByLogin(userName);
        
        Usuario usr_pro = usuarioRepository.findOne(id);
        
        SuscripcionUsuario sus = new SuscripcionUsuario();
        sus.setUsuarioProveedor(usr_pro);
        sus.setUsuarioSuscriptor(usr_local);
        
        suscriptorRepository.setSuscriptor(sus);
        
        return null;
        
    }
    
    
    
    
}
