/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.service.SorteoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jzelaya
 */

@Controller
@RequestMapping("/sorteo")
@ResponseBody
@Slf4j
public class SorteoController {
    
    private int maxIntentos = 3;
    private int intentos = 0;
    
    @Autowired
    private SorteoService sorteoService;    
    
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void start() {
        sorteoService.iniciarSorteo();
    }
    
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public void stop() {
        try {
            if(intentos >= maxIntentos){            
                    sorteoService.sacarGanador();
                    intentos = 0;
            } else {
                intentos++;
                sorteoService.sacarDescalificado();            
            }   
        } catch (InterruptedException ex) {
                Logger.getLogger(SorteoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
