/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnobitz.core.entidad.Tienda;
import com.tecnogeek.comprameya.service.TiendaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author alexander
 */
@Controller()
@RequestMapping("/tiendas")
@ResponseBody
@Slf4j
public class TiendaController {
 
    @Autowired
    private TiendaService tiendaService;
    
    @RequestMapping(value = "/aleatorias", method = RequestMethod.GET)
    public List<Tienda> getTiendasAleatorias(){
        return tiendaService.getTiendasAleatorias();
    }
    
}
