/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.soap.spider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
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
    
    public static Dictionary<String,String> getTiendaProducto(String url,String key,String dominio){
        
        Dictionary<String,String> listaDetalleProducto = new Hashtable<>();
        try {
            String[] stringProductoInfo = new String[]
            {
                "id",
                "id_category_default",
                "price",
                "name",
                "description_short",
                "id_default_image",
                "link_rewrite"
            };
            
            String StrResponse = GenericClient.getRequest(url+"?ws_key="+key);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(StrResponse.getBytes()));
            
            for(String infoKey : stringProductoInfo)
            {
                NodeList tmp = doc.getElementsByTagName(infoKey);
                String infoValue =  tmp.item(0).getTextContent();                
                listaDetalleProducto.put(infoKey, infoValue);
            
            }
            
            NodeList tmp = doc.getElementsByTagName("id_category_default");
            String linkcat =  tmp.item(0).getAttributes().getNamedItem("xlink:href").getNodeValue();
            
            String StrResponseCat = GenericClient.getRequest(linkcat+"?ws_key="+key);
            Document doccat = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(StrResponseCat.getBytes())); 
            
            NodeList tmpcat = doccat.getElementsByTagName("link_rewrite");
            String category_name =  tmpcat.item(0).getTextContent();
            
            listaDetalleProducto.put("category_name", category_name);
            
            String urlProducto = "http://"
                                 +dominio
                                 +listaDetalleProducto.get("category_name")
                                 +"/"+listaDetalleProducto.get("id")
                                 +"-"
                                 +listaDetalleProducto.get("link_rewrite")
                                 +".html" ;
            
            listaDetalleProducto.put("url_producto", urlProducto);
            
            String urlImage = "http://"
                                +dominio
                                +"/"
                                +listaDetalleProducto.get("id_default_image") 
                                + "-home_default"
                                +"/"
                                +listaDetalleProducto.get("link_rewrite")
                                +".jpg";
           
            listaDetalleProducto.put("url_image", urlImage);
           
            return listaDetalleProducto;
            
        } catch (IOException | ParserConfigurationException | SAXException | DOMException | NumberFormatException ex) {
            Logger.getLogger(ServiceSpider.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
