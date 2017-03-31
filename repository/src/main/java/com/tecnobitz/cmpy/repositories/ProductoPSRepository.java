/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.ProductoPS;
import com.tecnobitz.cmpy.entidad.ProductoPsKey;
import com.tecnogeek.comprameya.repositories.custom.ProductoPSRepositoryCustom;

/**
 *
 * @author genaro
 */
public interface ProductoPSRepository extends 
        BaseRepository<ProductoPS, ProductoPsKey>, ProductoPSRepositoryCustom {
    
}
