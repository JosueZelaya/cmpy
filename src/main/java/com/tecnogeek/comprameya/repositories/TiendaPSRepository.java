/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnogeek.comprameya.entidad.TiendaPS;
import com.tecnogeek.comprameya.repositories.custom.TiendaPSRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface TiendaPSRepository extends 
        BaseRepository<TiendaPS, Long>, TiendaPSRepositoryCustom {}
