/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.enums;

import lombok.Getter;

/**
 *
 * @author alexander
 */
public enum TipoNotificacionEnum {
    
    COMENTARIO(1L, "COMENTARIO_RECIBIDO"),
    MENSAJE(2L, "MENSAJE_RECIBIDO");
    
    @Getter
    Long codigo;
    
    @Getter
    String nombre;
    
    private TipoNotificacionEnum(final Long codigo, final String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public static TipoNotificacionEnum getTipo(final Long codigo, final String nombre){
        for(TipoNotificacionEnum tipo : values()){
            if(tipo.getCodigo().equals(codigo) && tipo.getNombre().equals(nombre)){
                return tipo;
            }
        }
        return null;
    }
    
    public static TipoNotificacionEnum getTipo(final Long codigo){
        for(TipoNotificacionEnum tipo : values()){
            if(tipo.getCodigo().equals(codigo)){
                return tipo;
            }
        }
        return null;
    }
    
}
