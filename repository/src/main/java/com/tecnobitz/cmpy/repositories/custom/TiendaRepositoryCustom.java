/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.custom;

import com.tecnobitz.cmpy.entidad.Tienda;
import java.util.List;

/**
 *
 * @author genaro
 */
public interface TiendaRepositoryCustom {
    
    public Long getTotal();
    public List<Tienda> findTiendas(int page, int itemsByPage);
    public List<Tienda> findTiendasAleatorias(int cantidad);
    
}
