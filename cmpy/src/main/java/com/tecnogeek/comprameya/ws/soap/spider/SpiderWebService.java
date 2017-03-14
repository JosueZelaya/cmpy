/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.soap.spider;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author genaro
 */
@WebService(serviceName = "SpiderWebService")
public class SpiderWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "indexCategoriasProductos")
    public boolean indexCategoriasProductos(@WebParam(name = "categoriaId") Long categoriaId) {
        
        return true;
    }
    
    @WebMethod(operationName = "indexTiendasProductos")
    public boolean indexTiendasProductos() {
          try {
        
        LogicSpider logic = new LogicSpider();

            logic.indexTiendasProductos(false);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SpiderWebService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (KeyManagementException ex) {
            Logger.getLogger(SpiderWebService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }    
    
}
