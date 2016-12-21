/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.Visita;
import com.tecnogeek.comprameya.repositories.VisitaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jzelaya
 */
@Service
@Getter
@Setter
public class VisitaService extends BaseService<Visita, Long>{
    
    private VisitaRepository repository;
    
    private long visitas;
    
    public VisitaService(@Autowired VisitaRepository repository){
        this.repository = repository;
        visitas = repository.count();
    }
    
    public void nuevaVisita(String ip){        
        visitas++;
        repository.save(new Visita(ip));
    }
    
    
}
