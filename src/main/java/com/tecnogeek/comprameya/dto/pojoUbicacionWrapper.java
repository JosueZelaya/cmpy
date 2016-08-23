/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import java.util.List;
import com.tecnogeek.comprameya.dto.pojoUbicacion;
/**
 *
 * @author genaro
 */
public class pojoUbicacionWrapper {
    
    private List<pojoUbicacion> ubicaciones;

    public List<pojoUbicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<pojoUbicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
    
}
