/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.QSuscriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.SuscriptorRepository;

/**
 *
 * @author genaro
 */

@Service
public class SuscriptorService {
    
    public SuscriptorService(){}
    
    @Autowired
    SuscriptorRepository suscriptorRepository;
    
    private final QSuscriptor qSuscriptor = QSuscriptor.suscriptor;
    
}
