/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.Recurso;
import com.tecnobitz.cmpy.repositories.custom.RecursoRepositoryCustom;

/**
 *
 * @author alexander
 */
public interface RecursoRepository extends BaseRepository<Recurso, Long>, RecursoRepositoryCustom{
    
}
