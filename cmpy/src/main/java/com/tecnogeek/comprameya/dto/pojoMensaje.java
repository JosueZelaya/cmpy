/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import java.util.List;

/**
 *
 * @author genaro
 */
public class pojoMensaje {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public pojoEmisor getEmisor() {
        return emisor;
    }

    public void setEmisor(pojoEmisor emisor) {
        this.emisor = emisor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<pojoDestinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<pojoDestinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }
    public long id;
    public pojoEmisor emisor;
    public String titulo;
    public String mensaje;
    public List<pojoDestinatario> destinatarios;
    
}
