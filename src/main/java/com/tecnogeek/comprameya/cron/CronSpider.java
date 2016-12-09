/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.cron;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author genaro
 */
public class CronSpider {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       indexTiendasProductos();
    }
    
    public static void indexTiendasProductos(){
        try {
            LogicSpider logic = new LogicSpider();
            logic.indexTiendasProductos(true);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CronSpider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(CronSpider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
