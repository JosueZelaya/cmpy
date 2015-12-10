/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.managers;

import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.SuscriptorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author genaro
 */
public class ManejadorSuscriptor {
    
    public ManejadorSuscriptor(){}
    
    @Autowired
    SuscriptorService suscriptorService;
    
    public List<Suscriptor> getSuscriptor(Usuario usuario)
    {
         List<Suscriptor> suscriptores =  suscriptorService.findByfkUsuarioSuscriptor(usuario);
       
         return suscriptores;
    }
    
        public List<Suscriptor> getProveedor(Usuario usuario)
    {
         List<Suscriptor> suscriptores =  suscriptorService.findByfkUsuarioProveedor(usuario);
       
         return suscriptores;
    }
    
    
}
