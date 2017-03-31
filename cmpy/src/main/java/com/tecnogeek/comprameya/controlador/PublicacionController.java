/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnobitz.cmpy.entidad.Producto;
import com.tecnobitz.cmpy.entidad.Recurso;
import com.tecnobitz.cmpy.entidad.TipoPublicacion;
import com.tecnobitz.cmpy.entidad.Ubicacion;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnogeek.comprameya.dto.pojoUbicacion;
import com.tecnobitz.cmpy.entidad.Categoria;
import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnobitz.cmpy.entidad.SuscripcionPublicacion;
import com.tecnobitz.cmpy.enums.TipoPublicacionEnum;
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

    @RequestMapping(value = "/getTotalAnuncios/{tipo}")
    public int getTotalAnuncios(@PathVariable int tipo) {
        boolean esPagada = (tipo == Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)
                ? TipoPublicacionEnum.PAGADA
                : TipoPublicacionEnum.GRATIS;
        return publicacionService.getTotalPublicaciones(tipoPublicacion).intValue();
    }

    @RequestMapping(value = "/getTotalPaginas/{tipo}")
    public int getTotalPaginas(@PathVariable int tipo) {
        boolean esPagada = (tipo == Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)
                ? TipoPublicacionEnum.PAGADA
                : TipoPublicacionEnum.GRATIS;

        int totalPublicaciones = publicacionService.getTotalPublicaciones(tipoPublicacion).intValue();
        int pageSize = (tipo == Constantes.PUBLICACION_GRATIS)
                ? Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR
                : Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR;
        return Utilidades.calculateTotalPages(totalPublicaciones, pageSize);
    }

    @RequestMapping(value = "/getPublicacionById/{id}", method = RequestMethod.GET)
    public Publicacion getPublicacionById(@PathVariable Long id) {
        log.info("Se muestra publicacion con id {}", id);
        return publicacionRepository.getPublicacion(id);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminarPublicacion(@PathVariable Long id) {
        try {
            publicacionService.eliminar(id);
        } catch (Exception e) {
            return e.getMessage();
        }

        log.info("Se ha eliminado la publicacion con id {}", id);
        return null;
    }

    @RequestMapping(value = "/vendida/{id}", method = RequestMethod.GET)
    public String setVendida(@PathVariable Long id) {
        try {
            publicacionService.marcarVendida(id);
        } catch (Exception e) {
            return e.getMessage();
        }

        log.info("Se ha marcado como vendida la publicacion con id {}", id);
        return null;
    }

    @RequestMapping(value = "/getAnuncios/{page}", method = RequestMethod.GET)
    public Iterable<Publicacion> getAnuncios(@PathVariable int page) {
        return publicacionService.getPublicacionesMixtas(page);
    }

    @RequestMapping(value = "/getAnuncios/{tipo}/{page}", method = RequestMethod.GET)
    public Iterable<Publicacion> getAnuncios(@PathVariable int tipo, @PathVariable int page, Model model) {
        int totalAnuncios;

        boolean esPagada = (tipo == Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)
                ? TipoPublicacionEnum.PAGADA
                : TipoPublicacionEnum.GRATIS;

        totalAnuncios = (tipo == Constantes.PUBLICACION_GRATIS)
                ? Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR
                : Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR;

        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion);

        return publicaciones;
    }

    @RequestMapping(value = "/getMisAnuncios/{tipo}/{page}", method = RequestMethod.GET)
    public Iterable<Publicacion> getMisAnuncios(@PathVariable int tipo, @PathVariable int page, Model model) {
        int totalAnuncios;

        boolean esPagada = (tipo == Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)
                ? TipoPublicacionEnum.PAGADA
                : TipoPublicacionEnum.GRATIS;

        totalAnuncios = (tipo == Constantes.PUBLICACION_GRATIS)
                ? Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR
                : Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR;

        Usuario loggedUser = usuarioRepository.getLoggedUser();

        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion, true, loggedUser);

        log.info("{} ha solicitado ver sus anuncios", loggedUser.getLogin());
        return publicaciones;
    }

    @RequestMapping(value = "/getAnunciosByMatch/{match}/{page}", method = RequestMethod.GET)
    public Iterable<Publicacion> getAnunciosByMatch(@PathVariable String match, @PathVariable int page) {
        log.info("Se busca {}", match);
        return publicacionService.getPublicaciones(page, match);
    }

    @RequestMapping(value = "/getAnunciosByCat/{tipo}/{page}/{cat}/{nivel}", method = RequestMethod.GET)
    public Iterable<Publicacion> getAnunciosByCat(@PathVariable int tipo, @PathVariable int page, @PathVariable long cat, @PathVariable int nivel, Model model) {
        int totalAnuncios;

        boolean esPagada = (tipo == Constantes.PUBLICACION_PAGADA);
        TipoPublicacionEnum tipoPublicacion = (esPagada)
                ? TipoPublicacionEnum.PAGADA
                : TipoPublicacionEnum.GRATIS;

        totalAnuncios = (tipo == Constantes.PUBLICACION_GRATIS)
                ? Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR
                : Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR;

        Iterable<Publicacion> publicaciones = publicacionService.getPublicaciones(page, totalAnuncios, tipoPublicacion, cat, nivel);

        log.info("Se filtra por cateogoria id {}", cat);
        return publicaciones;
    }

    @RequestMapping(value = "/agregarAnuncio", method = RequestMethod.POST)
    public String agregarAnuncio(@RequestParam(value = "titulo", required = true) String titulo,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam(value = "multipleFiles", required = false) List<MultipartFile> files,
            Model model) {
        Usuario loggedUser = usuarioRepository.getLoggedUser();
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcion(descripcion);
        List<Recurso> recursos = new ArrayList();
        for (MultipartFile multipartFile : files) {
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
        Integer tipo = Constantes.PUBLICACION_PAGADA;
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setId(tipo.longValue());
        publicacion.setTipo(tipoPublicacion);
        publicacion.setUsuario(loggedUser);
        publicacionRepository.save(publicacion);

        log.info("{} ha agregado el anuncio pagado: ", loggedUser.getLogin());
        return "ok";
    }

    @RequestMapping(value = "/agregarPublicacion", method = RequestMethod.POST)
    public Publicacion agregarPublicacion(@RequestParam(value = "titulo", required = true) String titulo,
            @RequestParam(value = "precio", required = true) Double precio,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam(value = "categoriaId", required = true) long categoriaId,
            @RequestParam(value = "ubicaciones", required = false) String ubicaciones,
            @RequestParam(value = "urls", required = false) List<String> urls,
            Model model) throws IOException {
        
        Usuario usuario = usuarioRepository.getLoggedUser();
        log.info("{} intenta agregar la publicacion: {}", usuario.getLogin(), titulo);
        log.info("titulo {}", titulo);
        log.info("precio {}", precio);
        log.info("descripcion {}", descripcion);
        log.info("categoriaId {}", categoriaId);
        log.info("ubicaciones {}", ubicaciones);
        log.info("urls {}", urls.toString());

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcion(descripcion);
        List<Recurso> recursos = new ArrayList();
        for (String url : urls) {
            Recurso recurso = new Recurso();
            recurso.setNombre(url);
            recurso.setRuta(url);
            recurso.setPublicacion(publicacion);
            recursos.add(recurso);
        }
        publicacion.setRecursoList(recursos);
        publicacion.setTipo(TipoPublicacionEnum.GRATIS);
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
        publicacion.setUsuario(usuario);

        ObjectMapper mapper = new ObjectMapper();
        List<pojoUbicacion> list = mapper.readValue(ubicaciones, new TypeReference<List<pojoUbicacion>>() {
        });

        publicacion.setUbicacionList(getUbicacionesI(publicacion, list));

        SuscripcionPublicacion suscripcionPublicacion = new SuscripcionPublicacion();
        suscripcionPublicacion.setPublicacion(publicacion);
        suscripcionPublicacion.setUsuario(usuario);

        List<SuscripcionPublicacion> suscriptores = new ArrayList<>();
        suscriptores.add(suscripcionPublicacion);
        publicacion.setSuscriptoresList(suscriptores);

        publicacion = publicacionRepository.save(publicacion);

        log.info("{} ha agregado la publicacion: {}, con id {} ", usuario.getLogin(), publicacion.getTitulo(), publicacion.getId());
        return publicacion;
    }

    public List<Ubicacion> getUbicacionesI(Publicacion pu,
            List<pojoUbicacion> listaUbicacion) {

        List<Ubicacion> listaUbicacionTmp = new ArrayList();
        int num = listaUbicacion.size();

        for (int i = 0; i < num; i++) {

            Ubicacion ubi = new Ubicacion();
            ubi.setGmLatitud(listaUbicacion.get(i).getLatitud());
            ubi.setGmLongitud(listaUbicacion.get(i).getLongitud());
            ubi.setPublicacion(pu);

            listaUbicacionTmp.add(ubi);
        }

        log.info("Se muestran las ubicaciones de la publicacion {}, con id {} ", pu.getTitulo(), pu.getId());
        return listaUbicacionTmp;
    }

}
