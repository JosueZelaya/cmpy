/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.soap.spider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import com.tecnogeek.comprameya.ws.rs.client.GenericClient;
import java.util.Dictionary;
import java.util.Hashtable;



/**
 *
 * @author genaro
 */
public class ServiceSpider {
    
  
    public static Dictionary<Integer,String> getTiendaProductos(String dominioTienda, String key) {
        
        Dictionary<Integer,String> listaProductos = new Hashtable<>();
        try {
            
            String url = "http://"+dominioTienda+"/api/products?ws_key=" + key;
            String StrResponse = GenericClient.getRequest(url);

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(StrResponse.getBytes()));
            NodeList e = doc.getElementsByTagName("product");
            
            for(int i = 0; i< e.getLength();i++){
                int id = Integer.parseInt(e.item(i).getAttributes().getNamedItem("id").getNodeValue());
                String link = e.item(i).getAttributes().getNamedItem("xlink:href").getNodeValue();
                listaProductos.put(id, link);
            } 
            
            return listaProductos;
           
        } catch (IOException | ParserConfigurationException | SAXException | DOMException | NumberFormatException ex) {
            Logger.getLogger(ServiceSpider.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
         
    }
    
    public static Dictionary<Integer,Dictionary<String,String>> getTiendaProducto(String url,String key){
        
        Dictionary<Integer,Dictionary<String,String>> listaDetalleProducto = new Hashtable<>();
        try {
        
            String StrResponse = GenericClient.getRequest(url);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(StrResponse.getBytes()));
            NodeList e = doc.getElementsByTagName("id");
            return null;
            
        } catch (IOException | ParserConfigurationException | SAXException | DOMException | NumberFormatException ex) {
            Logger.getLogger(ServiceSpider.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
