/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.ws.soap.spider.LogicSpider;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alexander
 */

@Controller
@RequestMapping("/Spider")
public class SpiderController {
    
    @RequestMapping(value="/scanearTiendasOverSSL")
    public String scanearTiendas(Model model){
        LogicSpider logic = new LogicSpider();
        try {
            logic.indexTiendasProductos(true);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("resultado", "ok");
        return "admin";
    }
    
    @RequestMapping(value="/scanearTiendasInsecure")
    public String scanearTiendasInsecure(Model model){
        LogicSpider logic = new LogicSpider();
        try {
            logic.indexTiendasProductos(false);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("resultado", "ok");
        return "admin";
    }
    
}
