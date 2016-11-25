/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.tecnogeek.comprameya.entidad.TiendaPS;
import com.tecnogeek.comprameya.repositories.TiendaPSRepository;
import com.tecnogeek.comprameya.repositories.custom.TiendaPSRepositoryCustom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author genaro
 */
public class TiendaPSRepositoryImpl implements TiendaPSRepositoryCustom {

    @Autowired
    TiendaPSRepository repository;
    
    @Override
    public List<TiendaPS> findTiendas(int page, int itemsByPage) {
        return repository.findAll(new PageRequest(page, itemsByPage)).getContent();        
    }
    
}
