/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

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
     
     public Usuario getUsuario(long id)
     {
         return userService.findOne(id);
     }
     
    
}
