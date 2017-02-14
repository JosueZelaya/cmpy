/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander
 */
@Service
public class SorteoService extends BaseService<Publicacion, Long>{
    
    @Getter
    @Setter
    private List<Publicacion> publicacionList;

    @Getter
    private PublicacionRepository repository;
    
    private SimpMessagingTemplate template;
       
    public SorteoService(@Autowired PublicacionRepository repository,
            @Autowired SimpMessagingTemplate template){
        this.repository = repository;
        this.template = template;
        publicacionList = (List<Publicacion>) repository.getPublicacionesGratis();    
        sendToClient(publicacionList);
    }
    
    private void sendToClient(final List<Publicacion> publicaciones){
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep(2000);
                        for(Publicacion publicacion : publicaciones) {
                            Thread.sleep(1000);
                            System.out.println("USUARIO: "+publicacion.getUsuario().getLogin());
                            template.convertAndSend("/topic/greetings", publicacion.getUsuario().getLogin());   
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SorteoService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                             
            }
        }).start();
//        template.convertAndSend("/topic/greetings", publicacion);        
    }
    
}
