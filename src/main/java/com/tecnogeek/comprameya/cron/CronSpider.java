/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.cron;
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
        LogicSpider logic = new LogicSpider();
        logic.indexTiendasProductos();
    }
    
}
