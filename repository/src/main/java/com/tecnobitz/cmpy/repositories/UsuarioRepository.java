/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.repositories.custom.UsuarioRepositoryCustom;

/**
 *
 * @author jzelaya
 */
public interface UsuarioRepository 
        extends BaseRepository<Usuario,Long>, 
        UsuarioRepositoryCustom{}
