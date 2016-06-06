/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

/**
 *
 * @author genaro
 */
public class pojoComentario {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public long id;
    public String texto;
    public int puntaje; 
    public pojoUsuario usuario;

    public pojoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(pojoUsuario usuario) {
        this.usuario = usuario;
    }
}
