/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.Persona;
import com.tecnobitz.cmpy.repositories.custom.PersonaRepositoryCustom;

/**
 *
 * @author alexander
 */
public interface PersonaRepository 
        extends BaseRepository<Persona,Long>, 
        PersonaRepositoryCustom{}
