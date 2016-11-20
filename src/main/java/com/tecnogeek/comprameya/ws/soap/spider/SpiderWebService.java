/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.soap.spider;

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
        
        LogicSpider logic = new LogicSpider();
        logic.indexTiendasProductos();
        
        return true;
    }    
    
}
