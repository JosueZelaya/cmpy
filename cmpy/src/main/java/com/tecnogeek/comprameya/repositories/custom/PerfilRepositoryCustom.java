/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnobitz.cmpy.entidad.Perfil;

/**
 *
 * @author alexander
 */
public interface PerfilRepositoryCustom {
    
    public Perfil findByNombre(String name);
    
}
