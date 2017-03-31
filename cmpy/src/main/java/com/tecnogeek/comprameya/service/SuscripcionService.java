/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnobitz.cmpy.entidad.SuscripcionPublicacion;
import com.tecnobitz.cmpy.entidad.SuscripcionUsuario;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.SuscripcionPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.tecnogeek.comprameya.repositories.SuscripcionUsuarioRepository;

/**
 *
 * @author genaro
 */

@Service
public class SuscripcionService {
    
    public SuscripcionService(){}
    
    @Autowired
    SuscripcionUsuarioRepository suscripcionUsuarioRepository;
    
    @Autowired
    SuscripcionPublicacionRepository suscripcionPublicacionRepository;
    
    public SuscripcionUsuario save(SuscripcionUsuario sU){
        return suscripcionUsuarioRepository.save(sU);
    }
    
    public SuscripcionPublicacion save(SuscripcionPublicacion sp){
        return suscripcionPublicacionRepository.save(sp);
    }
    
    public List<Usuario> getSuscriptores(Publicacion publicacion){
        List<SuscripcionPublicacion> suscripcionList = publicacion.getSuscriptoresList();        
        List<Usuario> usuarioList = new ArrayList<>();
        for(SuscripcionPublicacion suscripcion : suscripcionList){
            usuarioList.add(suscripcion.getUsuario());
        }
        return usuarioList;
    }
    
}
