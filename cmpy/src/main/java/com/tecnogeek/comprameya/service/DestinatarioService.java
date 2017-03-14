/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.DestinatarioRepository;

/**
 *
 * @author genaro
 */

@Service
public class DestinatarioService {
    
     @Autowired
     DestinatarioRepository destinatarioRepository;
    
}
