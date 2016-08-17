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
public enum TipoPublicacionEnum {
    
    PAGADA(1L, "PUBLICACION_PAGADA"),
    GRATIS(2L, "PUBLICACION_GRATIS");
    
    @Getter
    Long codigo;
    
    @Getter
    String descripcion;
    
    private TipoPublicacionEnum(final Long codigo, final String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    public TipoPublicacionEnum getTipo(final Long codigo){
        for(TipoPublicacionEnum tipo : values()){
            if(tipo.getCodigo().equals(codigo)){
                return tipo;
            }
        }
        return null;
    }
    
    
}
