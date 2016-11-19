/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.soap.spider;

import java.util.Dictionary;
import java.util.List;

/**
 *
 * @author genaro
 */
public class LogicSpider {
    
    public static boolean indexTiendasProductos() {
        
        
        
        Dictionary<Integer,String> listaProductos  = ServiceSpider.getTiendaProductos("localhost/prestashop","R1H6QVBKUKKIJ3IDWSA65H8ANF8FLG4L");
       


        
        
        return true;
    } 
    
}
