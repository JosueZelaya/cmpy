/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Tienda;
import java.util.List;

/**
 *
 * @author genaro
 */
public interface TiendaRepositoryCustom {
    
    public List<Tienda> findTiendas(int page, int itemsByPage);
    public List<Tienda> findTiendasAleatorias(int cantidad);
    
}
