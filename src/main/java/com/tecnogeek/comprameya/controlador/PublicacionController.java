/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Producto;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.dto.pojoUbicacion;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.service.PublicacionService;
import com.tecnogeek.comprameya.service.UsuarioService;
import com.tecnogeek.comprameya.utils.FileManager;
import com.tecnogeek.comprameya.utils.Utilidades;
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
import com.tecnogeek.comprameya.repositories.PublicacionRepository;

/**
 *
 * @author jzelaya
 */
@Controller
@RequestMapping("/publicacion")
@ResponseBody
public class PublicacionController {
 
    @Autowired
    PublicacionRepository publicacionService;
    
    @Autowired
    PublicacionService pManager;
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Autowired
    UsuarioService uManager;
    
    @RequestMapping(value="/getTotalAnuncios/{tipo}")
    public int getTotalAnuncios(@PathVariable int tipo){
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        return pManager.getTotalPublicaciones(tipoPublicacion).intValue();
    }
    
    @RequestMapping(value="/getTotalPaginas/{tipo}")
    public int getTotalPaginas(@PathVariable int tipo){
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        
        int totalPublicaciones = pManager.getTotalPublicaciones(tipoPublicacion).intValue();
        int pageSize = (tipo==Constantes.PUBLICACION_GRATIS)?
                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR:
                Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;
        return Utilidades.calculateTotalPages(totalPublicaciones, pageSize);
    }
    
    @RequestMapping(value="/getPublicacionById/{id}",method=RequestMethod.GET)    
    public Publicacion getPublicacionById(@PathVariable Long id){
        return publicacionRepository.getPublicacion(id);
    }
    
    @RequestMapping(value="/getAnuncios/{tipo}/{page}",method=RequestMethod.GET)    
    public Iterable<Publicacion> getAnuncios(@PathVariable int tipo,@PathVariable int page,Model model)
    {                
        int totalAnuncios;
        
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        
        totalAnuncios = (tipo==Constantes.PUBLICACION_GRATIS)?
                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR:
                Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;        
        
        Iterable<Publicacion> publicaciones = pManager.getPublicaciones(new PageRequest(page, totalAnuncios), tipoPublicacion);   
        
        return publicaciones;
    }
        
    @RequestMapping(value = "/agregarAnuncio", method = RequestMethod.POST)    
    public String agregarAnuncio(@RequestParam(value = "titulo", required = true) String titulo,
                                 @RequestParam(value = "descripcion", required = true) String descripcion,
                                 //@RequestParam(value = "ubicaciones", required = true) pojoUbicacionWrapper ubicaciones,
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
        Integer tipo=Constantes.PUBLICACION_PAGADA;
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setTipoPublicacionId(tipo.longValue());
        publicacion.setFkTipoPublicacion(tipoPublicacion);
        
        //cUbicacion.setUbicacionPublicacion(publicacion.getPublicacionId(), ubicaciones);
        
        publicacionService.save(publicacion);
        
        return "ok";
//        return new HomeController().welcomePage(model);        
    }
    
    @RequestMapping(value = "/agregarPublicacion", method = RequestMethod.POST)    
    public String agregarPublicacion(@RequestParam(value = "titulo", required = true) String titulo,
                                 @RequestParam(value = "precio", required = true) Double precio,
                                 @RequestParam(value = "descripcion", required = true) String descripcion,
                                 @RequestParam(value = "ubicaciones", required = false) String ubicaciones,
                                 //@RequestBody pojoUbicacionWrapper ubicaciones,
                                 @RequestParam(value = "multipleFiles", required = false) List<MultipartFile> files,
                                 Model model) throws IOException
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
        Integer tipo=Constantes.PUBLICACION_GRATIS;
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
        Usuario usuario = uManager.getLoggedUser();
        publicacion.setFkUsuario(usuario);
        
        ObjectMapper mapper = new ObjectMapper();
        List<pojoUbicacion> list = mapper.readValue(ubicaciones, new TypeReference<List<pojoUbicacion>>() { });
        
        publicacion.setUbicacionList(getUbicacionesI(publicacion, list));
        
        publicacionService.save(publicacion);
        
        return "ok";
    }
    
    
     public List<Ubicacion> getUbicacionesI( Publicacion pu, 
             List<pojoUbicacion> listaUbicacion )  {
        
        List<Ubicacion> listaUbicacionTmp = new ArrayList();
        int num = listaUbicacion.size();
   
            for(int i = 0;i<num;i++)
            {
                                
                Ubicacion ubi = new Ubicacion();
                ubi.setGmLatitud(listaUbicacion.get(i).getLatitud());
                ubi.setGmLongitud(listaUbicacion.get(i).getLongitud());
                ubi.setFkPublicacion(pu);
                
                listaUbicacionTmp.add(ubi);
            }
            
            
        return listaUbicacionTmp;  
    }    
    
    
}
