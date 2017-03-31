/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnobitz.cmpy.entidad.ProductoPS;
import java.util.List;

/**
 *
 * @author genaro
 */
public interface ProductoPSRepositoryCustom {
    public Long getTotal();
    public List<ProductoPS> findProductos(int page, int itemsByPage);
    public List<ProductoPS> findAleatoriosByTienda(long tiendaId, int cantidad);
    public List<ProductoPS> findAleatoriosByTienda(long tiendaId, int cantidad, String match);
    public List<ProductoPS> findAleatorios(String match, int cantidad);
    public ProductoPS findAleatorioByTienda(long tiendaId);
}
