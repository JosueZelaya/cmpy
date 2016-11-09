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
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.dto.pojoUbicacion;
import com.tecnogeek.comprameya.entidad.Categoria;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.SuscripcionPublicacion;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.service.PublicacionService;
import com.tecnogeek.comprameya.utils.FileManager;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jzelaya
 */
@Controller
@RequestMapping("/publicacion")
@ResponseBody
@Slf4j
public class PublicacionController {
    
    @Autowired
    PublicacionService publicacionService;
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @RequestMapping(value="/getTotalAnuncios/{tipo}")
    public int getTotalAnuncios(@PathVariable int tipo){
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        return publicacionService.getTotalPublicaciones(tipoPublicacion).intValue();
    }
    
    @RequestMapping(value="/getTotalPaginas/{tipo}")
    public int getTotalPaginas(@PathVariable int tipo){
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        
        int totalPublicaciones = publicacionService.getTotalPublicaciones(tipoPublicacion).intValue();
        int pageSize = (tipo==Constantes.PUBLICACION_GRATIS)?
                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR:
                Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;
        return Utilidades.calculateTotalPages(totalPublicaciones, pageSize);
    }
    
    @RequestMapping(value="/getPublicacionById/{id}",method=RequestMethod.GET)    
    public Publicacion getPublicacionById(@PathVariable Long id){
        return publicacionRepository.getPublicacion(id);
    }
    
    @RequestMapping(value="/eliminar/{id}",method=RequestMethod.GET)    
    public String eliminarPublicacion(@PathVariable Long id){     
        try{
            publicacionService.eliminar(id);
        }catch(Exception e){
            return e.getMessage();
        }
        
        return null;
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
        
        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion);   
        
        return publicaciones;
    }
    
    @RequestMapping(value="/getMisAnuncios/{tipo}/{page}",method=RequestMethod.GET)    
    public Iterable<Publicacion> getMisAnuncios(@PathVariable int tipo,@PathVariable int page,Model model)
    {                
        int totalAnuncios;
        
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        
        totalAnuncios = (tipo==Constantes.PUBLICACION_GRATIS)?
                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR:
                Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;        
        
        Usuario loggedUser = usuarioRepository.getLoggedUser();
        
        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion, loggedUser);   
        
        return publicaciones;
    }
    
    @RequestMapping(value="/getAnunciosByMatch/{tipo}/{page}/{match}",method=RequestMethod.GET)    
    public Iterable<Publicacion> getAnunciosByMatch(@PathVariable int tipo,@PathVariable int page,@PathVariable String match,Model model)
    {                
        int totalAnuncios;
        
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        
        totalAnuncios = (tipo==Constantes.PUBLICACION_GRATIS)?
                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR:
                Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;        
        
        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion,match);   
        
        return publicaciones;
    }
    
    @RequestMapping(value="/getAnunciosByCat/{tipo}/{page}/{cat}/{nivel}",method=RequestMethod.GET)    
    public Iterable<Publicacion> getAnunciosByCat(@PathVariable int tipo,@PathVariable int page,@PathVariable long cat,@PathVariable int nivel,Model model)
    {                
        int totalAnuncios;
        
        boolean esPagada = (tipo==Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)?                
                TipoPublicacionEnum.PAGADA:
                TipoPublicacionEnum.GRATIS;
        
        totalAnuncios = (tipo==Constantes.PUBLICACION_GRATIS)?
                Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR:
                Constantes.TOTAL_ANUNCIOS_PAGADOS_MOSTRAR;        
        
        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion,cat, nivel);   
        
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
                recurso.setPublicacion(publicacion);
                recursos.add(recurso);                
            } catch (IOException ex) {
                log.error(PublicacionController.class.getName(), "No se pudo cargar imagen", ex);
            }
        }
        publicacion.setRecursoList(recursos);
        Integer tipo=Constantes.PUBLICACION_PAGADA;
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setId(tipo.longValue());
        publicacion.setTipoPublicacion(tipoPublicacion);
        
        publicacionRepository.save(publicacion);
        
        return "ok";
    }
    
    @RequestMapping(value = "/agregarPublicacion", method = RequestMethod.POST)    
    public String agregarPublicacion(@RequestParam(value = "titulo", required = true) String titulo,
                                 @RequestParam(value = "precio", required = true) Double precio,
                                 @RequestParam(value = "descripcion", required = true) String descripcion,
                                 @RequestParam(value = "categoriaId", required = true) long categoriaId,
                                 @RequestParam(value = "ubicaciones", required = false) String ubicaciones,
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
                recurso.setPublicacion(publicacion);
                recursos.add(recurso);                
            } catch (IOException ex) {
                log.error(PublicacionController.class.getName(), "No se pudo cargar imagen", ex);
            }
        }
        publicacion.setRecursoList(recursos);        
        Integer tipo=Constantes.PUBLICACION_GRATIS;
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setId(tipo.longValue());
        publicacion.setTipoPublicacion(tipoPublicacion);        
        Producto producto = new Producto();
        producto.setNombre(titulo);
        producto.setPrecio(BigDecimal.valueOf(precio));
        producto.setPublicacion(publicacion);
        producto.setDescripcion(descripcion);
        
        
        Categoria cat = new Categoria();
        cat.setId(categoriaId);
        producto.setCategoria(cat);
                
                
        List<Producto> productos = new ArrayList();
        productos.add(producto);
        publicacion.setProductoList(productos);
        Usuario usuario = usuarioRepository.getLoggedUser();
        publicacion.setUsuario(usuario);
        
        ObjectMapper mapper = new ObjectMapper();
        List<pojoUbicacion> list = mapper.readValue(ubicaciones, new TypeReference<List<pojoUbicacion>>() { });
        
        publicacion.setUbicacionList(getUbicacionesI(publicacion, list));
        
        SuscripcionPublicacion suscripcionPublicacion = new SuscripcionPublicacion();
        suscripcionPublicacion.setPublicacion(publicacion);
        suscripcionPublicacion.setUsuario(usuario);
        
        List<SuscripcionPublicacion> suscriptores = new ArrayList<>();
        suscriptores.add(suscripcionPublicacion);
        publicacion.setSuscriptoresList(suscriptores);
        
        publicacionRepository.save(publicacion);
        
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
                ubi.setPublicacion(pu);
                
                listaUbicacionTmp.add(ubi);
            }
            
            
        return listaUbicacionTmp;  
    }    
    
    
}
