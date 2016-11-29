/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.NumberExpression;
import com.tecnogeek.comprameya.entidad.QTienda;
import com.tecnogeek.comprameya.entidad.Tienda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import com.tecnogeek.comprameya.repositories.custom.TiendaRepositoryCustom;
import com.tecnogeek.comprameya.repositories.TiendaRepository;
import javax.persistence.EntityManager;

/**
 *
 * @author genaro
 */
public class TiendaRepositoryImpl implements TiendaRepositoryCustom {

    @Autowired
    TiendaRepository repository;
    
    @Autowired
    EntityManager em;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }
    
    private final QTienda qTienda = QTienda.tienda;
    
    @Override
    public List<Tienda> findTiendas(int page, int itemsByPage) {
        return repository.findAll(new PageRequest(page, itemsByPage)).getContent();        
    }
    
    @Override
    public List<Tienda> findTiendasAleatorias(int cantidad) {
     return newJpaQuery().from(qTienda)
                .orderBy(NumberExpression.random().asc())
                .limit(cantidad)
                .list(qTienda);      
    }
    
}
