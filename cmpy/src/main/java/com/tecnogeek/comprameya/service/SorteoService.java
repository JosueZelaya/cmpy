/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnobitz.core.entidad.Notificacion;
import com.tecnobitz.core.entidad.Publicacion;
import com.tecnobitz.core.enums.TipoNotificacionEnum;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class SorteoService extends BaseService<Publicacion, Long> {

    @Getter
    @Setter
    private List<Publicacion> publicacionList;

    @Getter
    private PublicacionRepository repository;

    private SimpMessagingTemplate template;

    private Tombola tombola;

    private Publicacion publicacionSeleccionada = new Publicacion();
    
    private List<Long> ids = new ArrayList<>();

    public SorteoService(@Autowired PublicacionRepository repository,
            @Autowired SimpMessagingTemplate template) {
        this.repository = repository;
        this.template = template;
        publicacionList = (List<Publicacion>) repository.getPublicacionesSorteo();
        tombola = new Tombola(template, publicacionList);
    }

    public void iniciarSorteo() {
        publicacionList = (List<Publicacion>) repository.getPublicacionesSorteo();
        //Colectar los ids participantes
        for(Publicacion publicacion : publicacionList){
            ids.add(publicacion.getId());
        }
        tombola.setPublicaciones(publicacionList);
        tombola.setIds(ids);
        if (!tombola.isRunning()) {
            tombola.restart();
        } else {
            tombola.start();
        }
    }

    public void sacarGanador() throws InterruptedException {
        tombola.stopRolling();
        Thread.sleep(1000);
        publicacionSeleccionada = tombola.getPublicacionSeleccionada();        
        Notificacion notificacion = new Notificacion();
        notificacion.setId(publicacionSeleccionada.getUsuario().getId());
        notificacion.setTipo(TipoNotificacionEnum.GANADOR_SORTEO);
        notificacion.setUsuarioSeleccionado(publicacionSeleccionada.getUsuario());
        String mensaje = publicacionSeleccionada.getUsuario().getPersona().getNombre()
                + " " + publicacionSeleccionada.getUsuario().getPersona().getApellido()
                + ", numero sorteo: " + publicacionSeleccionada.getId();
        notificacion.setMensaje(mensaje);
        notificacion.setLink(publicacionSeleccionada.getUsuario().getRutaImagen());
        System.out.println("USUARIO: " + publicacionSeleccionada.getUsuario().getLogin());
        template.convertAndSend("/topic/greetings", notificacion);        
    }
    
    public void sacarDescalificado() throws InterruptedException {
        tombola.stopRolling();
        Thread.sleep(1000);
        publicacionSeleccionada = tombola.getPublicacionSeleccionada();
        Notificacion notificacion = new Notificacion();        
        notificacion.setTipo(TipoNotificacionEnum.DESCALIFICADO_SORTEO);
        notificacion.setUsuarioSeleccionado(publicacionSeleccionada.getUsuario());
        String mensaje = publicacionSeleccionada.getUsuario().getPersona().getNombre()
                + " " + publicacionSeleccionada.getUsuario().getPersona().getApellido()
                + ", numero sorteo: " + publicacionSeleccionada.getId();
        notificacion.setMensaje(mensaje);
        notificacion.setLink(publicacionSeleccionada.getUsuario().getRutaImagen());
        System.out.println("USUARIO: " + publicacionSeleccionada.getUsuario().getLogin());
        template.convertAndSend("/topic/greetings", notificacion);        
    }

}

@Getter
@Setter
class Tombola extends Thread {

    private SimpMessagingTemplate template;
    private List<Publicacion> publicaciones;
    private List<Long> ids = new ArrayList<>(); //ids participantes
    private Publicacion publicacionSeleccionada;
    private volatile boolean isRunning = true;

    public Tombola(SimpMessagingTemplate template, List<Publicacion> publicaciones) {
        this.template = template;
        this.publicaciones = publicaciones;
    }

    public void stopRolling() {
        isRunning = false;
    }

    public synchronized void restart() {
        isRunning = true;
    }

    public synchronized Publicacion getPublicacionAleatoria() {
        Publicacion pubSeleccionada = new Publicacion();
        int total = ids.size();
        Random rand = new Random();
        int randomId = rand.nextInt(total);
        Long idSeleccionado = ids.get(randomId);
        for (Publicacion publicacion : publicaciones) {
            if (idSeleccionado.equals(publicacion.getId())) {
                return publicacion;
            }
        }
        return pubSeleccionada;
    }
    
    public synchronized Publicacion getDescalificadoAleatorio() {
        Publicacion pubSeleccionada = new Publicacion();
        int total = ids.size();
        Random rand = new Random();
        int randomId = rand.nextInt(total);
        Long idSeleccionado = ids.get(randomId);
        for (Publicacion publicacion : publicaciones) {
            if (idSeleccionado.equals(publicacion.getId())) {
                publicaciones.remove(publicacion);
                ids.remove(randomId);
                return publicacion;
            }
        }
        return pubSeleccionada;
    }

    @Override
    public void run() {

        while (true) {
            if (isRunning) {
                try {
                    publicacionSeleccionada = getPublicacionAleatoria();
                    Notificacion notificacion = new Notificacion();                    
                    notificacion.setTipo(TipoNotificacionEnum.SELECCIONADO_SORTEO);
                    String mensaje = publicacionSeleccionada.getUsuario().getPersona().getNombre()
                            + " " + publicacionSeleccionada.getUsuario().getPersona().getApellido()
                            + ", numero sorteo: " + publicacionSeleccionada.getId();
                    notificacion.setMensaje(mensaje);                    
                    Thread.sleep(100);
                    System.out.println("USUARIO: " + publicacionSeleccionada.getUsuario().getLogin());
                    template.convertAndSend("/topic/greetings", notificacion);

                } catch (InterruptedException ex) {
                    Logger.getLogger(SorteoService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
