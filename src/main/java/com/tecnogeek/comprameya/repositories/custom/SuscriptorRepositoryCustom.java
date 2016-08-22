/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Usuario;

/**
 *
 * @author alexander
 */
public interface SuscriptorRepositoryCustom {
    
    public Iterable<Suscriptor> getSuscriptor(Usuario usuario);
    
    public Iterable<Suscriptor> getProveedor(Usuario usuario);
    
    public String setSuscriptor(Suscriptor sus);
    
}
