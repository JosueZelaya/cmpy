/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.enums.TipoNotificacionEnum;
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
       
    private Tombola tombola;
    
    public SorteoService(@Autowired PublicacionRepository repository,
            @Autowired SimpMessagingTemplate template){
        this.repository = repository;
        this.template = template;
        publicacionList = (List<Publicacion>) repository.getPublicacionesGratis();    
        tombola = new Tombola(template, publicacionList);
        sendToClient();
    }
    
    private void sendToClient(){        
        tombola.start(); 
    }
    
    private void iniciarSorteo(){
        tombola.start();
    }
    
    private void detenerTombola(){
        tombola.interrupt();
    }
    
}

@Getter
@Setter
class Tombola extends Thread {
    
    private SimpMessagingTemplate template;
    private List<Publicacion> publicaciones;
    
    public Tombola(SimpMessagingTemplate template, List<Publicacion> publicaciones){
       this.template = template;
       this.publicaciones = publicaciones;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2000);
                for(Publicacion publicacion : publicaciones) {
                    Notificacion notificacion = new Notificacion();
                    notificacion.setId(publicacion.getUsuario().getId());
                    notificacion.setTipo(TipoNotificacionEnum.SORTEO);
                    String mensaje = publicacion.getUsuario().getPersona().getNombre() +
                            " " + publicacion.getUsuario().getPersona().getApellido();
                    notificacion.setMensaje(mensaje);
                    notificacion.setLink(publicacion.getUsuario().getRutaImagen());
                    Thread.sleep(1000);
                    System.out.println("USUARIO: "+publicacion.getUsuario().getLogin());
                    template.convertAndSend("/topic/greetings", notificacion);   
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(SorteoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                             
    }
}
