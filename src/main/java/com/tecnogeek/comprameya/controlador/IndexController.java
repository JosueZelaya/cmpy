/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author genaro
 */
@Controller
public class IndexController 
{
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String indexPublico(Model model)
    {
        model.addAttribute("parametro", "Hola Mundo");
        return "index";
    } 
    
}
