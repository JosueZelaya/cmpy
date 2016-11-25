/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.tecnogeek.comprameya.entidad.ProductoPS;
import com.tecnogeek.comprameya.entidad.QProductoPS;
import com.tecnogeek.comprameya.repositories.ProductoPSRepository;
import com.tecnogeek.comprameya.repositories.custom.ProductoPSRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author genaro
 */
public class ProductoPSRepositoryImpl implements ProductoPSRepositoryCustom{

    @Autowired
    EntityManager em;
    
    @Autowired
    ProductoPSRepository repository;
    
    private final QProductoPS qProducto = QProductoPS.productoPS;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }
    
    @Override
    public List<ProductoPS> findProductos(int page, int itemsByPage) {
        return repository.findAll(new PageRequest(page, itemsByPage)).getContent();
    }

    @Override
    public List<ProductoPS> findAleatoriosByTienda(long tiendaId, int cantidad) {
        BooleanExpression filteredByTienda = qProducto.tiendaps.id.eq(tiendaId);
        return newJpaQuery().from(qProducto)
                .where(filteredByTienda)
                .orderBy(NumberExpression.random().asc())
                .limit(cantidad)
                .list(qProducto);
    }
    
    @Override
    public ProductoPS findAleatorioByTienda(long tiendaId) {
        BooleanExpression filteredByTienda = qProducto.tiendaps.id.eq(tiendaId);
        return newJpaQuery().from(qProducto)
                .where(filteredByTienda)
                .orderBy(NumberExpression.random().asc())
                .limit(1)
                .singleResult(qProducto);
    }
    
}
