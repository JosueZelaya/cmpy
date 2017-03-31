/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.tecnobitz.cmpy.entidad.ProductoPS;
import com.tecnobitz.cmpy.entidad.QProductoPS;
import com.tecnobitz.cmpy.repositories.ProductoPSRepository;
import com.tecnobitz.cmpy.repositories.custom.ProductoPSRepositoryCustom;
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
        BooleanExpression filteredByTienda = qProducto.id.tienda.id.eq(tiendaId);
        return newJpaQuery().from(qProducto)
                .where(filteredByTienda)
                .orderBy(NumberExpression.random().asc())
                .limit(cantidad)
                .list(qProducto);
    }
    
    @Override
    public List<ProductoPS> findAleatoriosByTienda(long tiendaId, int cantidad, String match) {
        BooleanExpression filteredByTienda = qProducto.id.tienda.id.eq(tiendaId);
        BooleanExpression matchName = qProducto.titulo.toUpperCase().like("%"+match.toUpperCase()+"%");
        return newJpaQuery().from(qProducto)
                .where(filteredByTienda.and(matchName))
                .orderBy(NumberExpression.random().asc())
                .limit(cantidad)
                .list(qProducto);
    }
    
    @Override
    public ProductoPS findAleatorioByTienda(long tiendaId) {
        BooleanExpression filteredByTienda = qProducto.id.tienda.id.eq(tiendaId);
        return newJpaQuery().from(qProducto)
                .where(filteredByTienda)
                .orderBy(NumberExpression.random().asc())
                .limit(1)
                .singleResult(qProducto);
    }

    @Override
    public Long getTotal() {
        return repository.count();
    }

    @Override
    public List<ProductoPS> findAleatorios(String match, int cantidad) {
        BooleanExpression matchDescripcion = qProducto.titulo.toUpperCase().like("%"+match.toUpperCase()+"%");
        return newJpaQuery().from(qProducto)
                .where(matchDescripcion)
                .orderBy(NumberExpression.random().asc())
                .limit(cantidad)
                .list(qProducto);
    }
    
}
