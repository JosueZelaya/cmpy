/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.repositories;

import com.tecnobitz.core.entidad.Perfil;
import com.tecnogeek.comprameya.repositories.custom.PerfilRepositoryCustom;

/**
 *
 * @author jzelaya
 */
public interface PerfilRepository 
        extends BaseRepository<Perfil, Long>,
        PerfilRepositoryCustom {
}
