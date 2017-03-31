/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnobitz.cmpy.entidad.Persona;
import com.tecnogeek.comprameya.repositories.custom.PersonaRepositoryCustom;

/**
 *
 * @author alexander
 */
public interface PersonaRepository 
        extends BaseRepository<Persona,Long>, 
        PersonaRepositoryCustom{}
