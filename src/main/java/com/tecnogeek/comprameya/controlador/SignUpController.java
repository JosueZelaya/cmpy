/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josue.zelaya
 */

@Controller
@Slf4j
public class SignUpController {
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        log.info("Se solicita formulario de registro por facebook");
        return "redirect:/user/register";
    }
    
}
