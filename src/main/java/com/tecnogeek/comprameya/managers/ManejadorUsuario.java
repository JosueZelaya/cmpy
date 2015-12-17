/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.pojo.pojoUsuario;
import com.tecnogeek.comprameya.repositories.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author genaro
 */
public class ManejadorUsuario {
    
     @Autowired
     UserService userService;
     
     public Usuario getUsuarioLogin(String login)
     {
        return userService.findByLogin(login);
     }
     
     public Usuario getUsuarioLogin()
     {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return getUsuarioLogin(userName);
     }
     

     
     public Usuario getUsuario(long id)
     {
         return userService.findOne(id);
     }
     
    public List<pojoUsuario> getUsuarioPojo(List<Usuario> lista)
    {
        List<pojoUsuario> lpUsuario = new ArrayList<>();


        for(Usuario usr : lista)
        {
            pojoUsuario p = new pojoUsuario();
            p.setId(usr.getUsuarioId());
            p.setLogin(usr.getLogin());

            lpUsuario.add(p);
        }

        return lpUsuario;
    }

    
}
