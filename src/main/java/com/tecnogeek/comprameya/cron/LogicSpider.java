/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.cron;

import com.tecnogeek.comprameya.entidad.ProductoPS;
import com.tecnogeek.comprameya.entidad.Tienda;
import com.tecnogeek.comprameya.repositories.ProductoPSRepository;
import com.tecnogeek.comprameya.service.ProductoPSService;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import com.tecnogeek.comprameya.service.TiendaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import com.tecnogeek.comprameya.repositories.TiendaRepository;

/**
 *
 * @author genaro
 */
@Configurable
public class LogicSpider {
    
    
    @Autowired
    TiendaRepository tiendaPSRepository;
    
    @Autowired
    ProductoPSRepository productoPSRepository;
    
    public boolean indexTiendasProductos() {
                 
        Iterable<Tienda> tiendas = tiendaPSRepository.findAll();
        
        for(Tienda tienda : tiendas){
            
            List<ProductoPS> listaProductoPS = new ArrayList<>();
            String dominio = tienda.getDominio();
            String key = tienda.getKey();

            Dictionary<Integer,String> listaProductos  = ServiceSpider.getTiendaProductos(dominio,key);
            Enumeration<Integer> k = listaProductos.keys();

            while(k.hasMoreElements())
            {   
                int id = k.nextElement();
                String linkProducto = listaProductos.get(id);

                Dictionary<String,String> detalleProducto = ServiceSpider.getTiendaProducto(linkProducto, key,dominio);
                
                ProductoPS producto = new ProductoPS();
                producto.setIdps(Long.parseLong(detalleProducto.get("id")));
                producto.setTitulo(!detalleProducto.get("name").equals("")?detalleProducto.get("name"):" ");
                producto.setPrecio(BigDecimal.valueOf(Double.parseDouble(detalleProducto.get("price"))));
                producto.setImagen_id(Long.parseLong(!detalleProducto.get("id_default_image").equals("")?detalleProducto.get("id_default_image"):"0"));
                producto.setRutaimagen(detalleProducto.get("url_image")!=null?detalleProducto.get("url_image"):" ");
                producto.setDescripcion(!detalleProducto.get("description_short").equals("")?detalleProducto.get("description_short"):" ");
                
                if(producto.getDescripcion().length()>255){
                    producto.setDescripcion(producto.getDescripcion().substring(0, 254));
                }                
                
                producto.setDescripcion(producto.getDescripcion().replaceAll("<p>", ""));
                producto.setDescripcion(producto.getDescripcion().replaceAll("</p>", ""));
                
                producto.setUrl(detalleProducto.get("url_producto"));
                producto.setCategoria(detalleProducto.get("category_name"));
                producto.setCategoria_id(Long.parseLong(detalleProducto.get("id_category_default")));              
                producto.setTienda(tienda);
                
                
                listaProductoPS.add(producto);

            }
            
            productoPSRepository.save(listaProductoPS);
        
        }

        
        
        return true;
    } 
    
}