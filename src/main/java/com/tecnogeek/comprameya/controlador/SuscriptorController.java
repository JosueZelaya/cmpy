/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;


import com.tecnogeek.comprameya.managers.ManejadorSuscriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author genaro
 */
@Controller
@RequestMapping("/suscriptor")
public class SuscriptorController {
    @Autowired
    ManejadorSuscriptor sManager;
    
    
    
    
}
