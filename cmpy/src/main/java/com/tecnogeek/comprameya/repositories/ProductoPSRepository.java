/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import com.tecnobitz.core.entidad.ProductoPS;
import com.tecnobitz.core.entidad.ProductoPsKey;
import com.tecnogeek.comprameya.repositories.custom.ProductoPSRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface ProductoPSRepository extends 
        BaseRepository<ProductoPS, ProductoPsKey>, ProductoPSRepositoryCustom {
    
}