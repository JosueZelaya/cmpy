/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Producto;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import com.tecnogeek.comprameya.managers.ManejadorPublicacion;
import com.tecnogeek.comprameya.repositories.PublicacionService;
import com.tecnogeek.comprameya.utils.FileManager;
import com.tecnogeek.comprameya.utils.GridResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jzelaya
 */
@Controller
@RequestMapping("/publicacion")
public class PublicacionController {
 
    @Autowired
    PublicacionService publicacionService;
    @Autowired
    ManejadorPublicacion pManager;
    
    @RequestMapping(value="/getAnuncios/{tipo}/{page}",method=RequestMethod.GET)
    @ResponseBody
    public List<Publicacion> getAnuncios(@PathVariable int tipo,@PathVariable int page,Model model)
    {                
        int totalAnuncios;
        if(tipo==Constantes.PUBLICACION_GRATIS)
        {
            totalAnuncios = Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR;
        }else
        {
            totalAnuncios = Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;
        }        
        
        List<Publicacion> publicaciones = pManager.getPublicaciones(new PageRequest(page, totalAnuncios), tipo);   
        
        return publicaciones;
    }
        
    @RequestMapping(value = "/agregarAnuncio", method = RequestMethod.POST)    
    public String agregarAnuncio(@RequestParam(value = "titulo", required = true) String titulo,
                                 @RequestParam(value = "descripcion", required = true) String descripcion,
                                 @RequestParam(value = "multipleFiles", required = false) List<MultipartFile> files,
                                 Model model)
    {                  
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcion(descripcion);
        List<Recurso> recursos = new ArrayList();
        for (MultipartFile multipartFile : files ){
            try {
                String fileName = FileManager.saveFile(multipartFile);
                Recurso recurso = new Recurso();
                recurso.setNombre(fileName);
                recurso.setRuta(fileName);
                recurso.setFkPublicacion(publicacion);
                recursos.add(recurso);                
            } catch (IOException ex) {
                Logger.getLogger(PublicacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        publicacion.setRecursoList(recursos);
        Integer tipo=1;
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setTipoPublicacionId(tipo.longValue());
        publicacion.setFkTipoPublicacion(tipoPublicacion);
        publicacionService.save(publicacion);
        
        return "redirect:/";
//        return new HomeController().welcomePage(model);        
    }
    
    @RequestMapping(value = "/agregarPublicacion", method = RequestMethod.POST)    
    public String agregarPublicacion(@RequestParam(value = "titulo", required = true) String titulo,
                                 @RequestParam(value = "precio", required = true) Double precio,
                                 @RequestParam(value = "descripcion", required = true) String descripcion,
                                 @RequestParam(value = "multipleFiles", required = false) List<MultipartFile> files,
                                 Model model)
    {           
        
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcion(descripcion);
        List<Recurso> recursos = new ArrayList();
        for (MultipartFile multipartFile : files ){
            try {
                String fileName = FileManager.saveFile(multipartFile);
                Recurso recurso = new Recurso();
                recurso.setNombre(fileName);
                recurso.setRuta(fileName);
                recurso.setFkPublicacion(publicacion);
                recursos.add(recurso);                
            } catch (IOException ex) {
                Logger.getLogger(PublicacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        publicacion.setRecursoList(recursos);        
        Integer tipo=2;
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setTipoPublicacionId(tipo.longValue());
        publicacion.setFkTipoPublicacion(tipoPublicacion);        
        Producto producto = new Producto();
        producto.setNombre(titulo);
        producto.setPrecio(BigDecimal.valueOf(precio));
        producto.setFkPublicacion(publicacion);
        producto.setDescripcion(descripcion);
        List<Producto> productos = new ArrayList();
        productos.add(producto);
        publicacion.setProductoList(productos);
        publicacionService.save(publicacion);
        
        return "redirect:/";
    }
    
}
