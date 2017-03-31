/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.soap.spider;

import com.tecnobitz.cmpy.entidad.ProductoPS;
import com.tecnobitz.cmpy.entidad.ProductoPsKey;
import com.tecnobitz.cmpy.entidad.Tienda;
import com.tecnogeek.comprameya.repositories.ProductoPSRepository;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import com.tecnogeek.comprameya.repositories.TiendaRepository;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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
    
    public boolean indexTiendasProductos(Boolean overSSL) throws NoSuchAlgorithmException, KeyManagementException {
                 
        Iterable<Tienda> tiendas = tiendaPSRepository.findAll();
        
        for(Tienda tienda : tiendas){
            
            List<ProductoPS> listaProductoPS = new ArrayList<>();
            String dominio = tienda.getDominio();
            String key = tienda.getKey();
            ServiceSpider serviceSpicer = new ServiceSpider(overSSL);
            
            Dictionary<Integer,String> listaProductos  = serviceSpicer.getTiendaProductos(dominio,key);
            Enumeration<Integer> k = listaProductos.keys();

            while(k.hasMoreElements())
            {   
                int id = k.nextElement();
                String linkProducto = listaProductos.get(id);

                Dictionary<String,String> detalleProducto = serviceSpicer.getTiendaProducto(linkProducto, key,dominio);
                
                ProductoPS producto = new ProductoPS();
                ProductoPsKey idProducto = new ProductoPsKey(Long.parseLong(detalleProducto.get("id")), tienda);
                producto.setId(idProducto);
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
                
                listaProductoPS.add(producto);

            }
            
            productoPSRepository.save(listaProductoPS);
        
        }
        return true;
    } 
    
}
