/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnobitz.core.entidad.Tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.TiendaRepository;
import java.util.List;

/**
 *
 * @author genaro
 */
@Service
public class TiendaService {
    
    public TiendaService(){}
    
    @Autowired
    TiendaRepository tiendaRepository;    
    
    public List<Tienda> getTiendasAleatorias(){
        int cantidad = Constantes.TOTAL_TIENDAS_MOSTRAR;
        return tiendaRepository.findTiendasAleatorias(cantidad);
    }
    
}
