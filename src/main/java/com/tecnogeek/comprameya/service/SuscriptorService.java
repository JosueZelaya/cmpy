/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.SuscriptorRepository;

/**
 *
 * @author genaro
 */

@Service
public class SuscriptorService {
    
    public SuscriptorService(){}
    
    @Autowired
    SuscriptorRepository suscriptorService;
    
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
    
    public String setSuscriptor(Suscriptor sus)
    {
        suscriptorService.save(sus);
        return null;
    }
    
}
