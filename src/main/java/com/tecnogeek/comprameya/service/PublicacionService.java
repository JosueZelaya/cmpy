/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.google.common.collect.Iterables;
import com.tecnogeek.comprameya.constantes.Constantes;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.dto.GridResponse;
import com.tecnogeek.comprameya.entidad.Producto;
import com.tecnogeek.comprameya.entidad.ProductoPS;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.Tienda;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.enums.TipoPublicacionEnum;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import com.tecnogeek.comprameya.repositories.ProductoPSRepository;
import com.tecnogeek.comprameya.utils.Utilidades;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.PublicacionRepository;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import java.util.Iterator;
import java.util.List;
import com.tecnogeek.comprameya.repositories.TiendaRepository;

/**
 *
 * @author arch
 */
@Service
public class PublicacionService extends BaseService<Publicacion, Long> {

    public PublicacionService() {
    }

    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    TiendaRepository tiendaRepository;

    @Autowired
    ProductoPSRepository productoPSRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public BaseRepository<Publicacion, Long> getRepository() {
        return publicacionRepository;
    }

    /**
     * Elimina la publicación por medio de su id
     * 
     * @param publicacionId
     * @throws Exception 
     */
    public void eliminar(Long publicacionId) throws Exception {
        Publicacion publicacion = getRepository().findOne(publicacionId);

        Usuario loggedUser = usuarioRepository.getLoggedUser();
        if (!loggedUser.equals(publicacion.getUsuario())) {
            throw new Exception("ERROR: No es el propietario de la publicacion");
        }

        publicacion.desactivar();
        getRepository().save(publicacion);
    }
    
    /**
     * Establece que una pubicación ya fue vendida por medio de su ID
     * 
     * @param publicacionId
     * @throws Exception 
     */
    public void marcarVendida(Long publicacionId) throws Exception {
        Publicacion publicacion = getRepository().findOne(publicacionId);

        Usuario loggedUser = usuarioRepository.getLoggedUser();
        if (!loggedUser.equals(publicacion.getUsuario())) {
            throw new Exception("ERROR: No es el propietario de la publicacion");
        }

        publicacion.setVendido(true);
        getRepository().save(publicacion);
    }

    /**
     * Devuelve un listado de publicaciones tanto gratis como externas
     * el total de cada tipo se calcula basandose en las configuraciones
     * definidas en la clase: Constantes
     * @param page
     * @return 
     */
    public List<Publicacion> getPublicacionesMixtas(int page) {
        int total = Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR;
        Iterable<Publicacion> publicacionesGratis
                = getPublicaciones(page, total, TipoPublicacionEnum.GRATIS);

        total = Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR;
        Iterable<Publicacion> publicacionesExternas
                = getAnunciosAleatorios(total, TipoPublicacionEnum.EXTERNA);

        return mezclarPublicaciones(publicacionesGratis, publicacionesExternas);
    }

    /**
     * Se encarga de mezclar las publicaciones prioritarias y secundarias en una sola lista
     * intercalada de acuerdo a las reglas definidas.
     * 
     * @param pubPrioritarias
     * @param pubSecundarias
     * @return 
     */
    public List<Publicacion> mezclarPublicaciones(Iterable<Publicacion> pubPrioritarias, Iterable<Publicacion> pubSecundarias) {
        List<Publicacion> publicaciones = new ArrayList<>();
        int prioritariasAgregadas = 0;
        int totalAgregadas = 0;
        Iterator itrSecundaria = pubSecundarias.iterator();
        Iterator itrPrioritaria = pubPrioritarias.iterator();
        
        int numPuPrioritarias = Iterables.size(pubPrioritarias);
        
        while(prioritariasAgregadas < numPuPrioritarias){            
            if (esTurnoPrioritario(totalAgregadas)) {
                if(itrPrioritaria.hasNext()){
                    publicaciones.add((Publicacion) itrPrioritaria.next());
                    prioritariasAgregadas++;
                }
            } else {                                
                if (itrSecundaria.hasNext()) {
                    publicaciones.add((Publicacion) itrSecundaria.next());
                }
            }
            totalAgregadas++;
        }

        //terminar de agregar las publicaciones secundarias que hayan quedado
        while (itrSecundaria.hasNext()) {
            publicaciones.add((Publicacion) itrSecundaria.next());
        }

        return publicaciones;
    }
    
    /**
     * Las publicaciones prioritarias se van agregando en multiplos del valor Constante.PRIORITARIA
     * 
     * @param prioritariasAgregadas
     * @return 
     */
    public boolean esTurnoPrioritario(int prioritariasAgregadas){          
        if(prioritariasAgregadas==0){
            return true;
        }else {
            return (prioritariasAgregadas % (Constantes.PRIORITARIA + 1) != 0);
        }
    }

    /**
     * Devuelve un listado de anuncios aleatorios del tipo especificado
     * 
     * @param total
     * @param tipo
     * @return 
     */
    public Iterable<Publicacion> getAnunciosAleatorios(int total, TipoPublicacionEnum tipo) {
        int pageZise = total;
        Long totalPublicaciones = getTotalPublicaciones(tipo);
        int totalPages = Utilidades.calculateTotalPages(totalPublicaciones.intValue(), pageZise);
        int page = Utilidades.randomInt(0, totalPages - 1); //dado que la primer pagina en la BD es cero. 

        Iterable<Publicacion> publicaciones = new ArrayList<>();
        if (null != tipo) {
            switch (tipo) {
                case TIENDA:
                    List<Tienda> tiendas = tiendaRepository.findTiendas(page, pageZise);
                    publicaciones = convertTienda(tiendas);
                    break;
                case EXTERNA:
                    List<Tienda> tiendasAletorias = tiendaRepository.findTiendas(page, pageZise);
                    publicaciones = getPublicacionesExternas(tiendasAletorias);
                    break;
                default:
                    publicaciones = getPublicaciones(page, pageZise, tipo);
                    break;
            }
        }

        return publicaciones;
    }

    /**
     * Devuelve un listado de publicaciones externas por cada tienda y su tamaño
     * no debe exeder al especificado en Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR
     * 
     * @param tiendasAletorias
     * @return 
     */
    private List<Publicacion> getPublicacionesExternas(List<Tienda> tiendasAletorias) {
        int totalAnuncios = Constantes.TOTAL_ANUNCIOS_EXTERNOS_MOSTRAR;
        List<ProductoPS> productos = new ArrayList<>();
        if(tiendasAletorias.isEmpty()){
            return convertProducto(productos);
        }
        
        boolean salir=false;
        while (productos.size() < totalAnuncios) {
            for (Tienda tienda : tiendasAletorias) {                
                if (productos.size() < totalAnuncios) {
                    ProductoPS producto = productoPSRepository.findAleatorioByTienda(tienda.getId());
                    if(producto == null){
                        salir=true;
                        break;
                    }              
                    productos.add(producto);
                } else {
                    break;
                }
            }
            if(salir){
                break;
            }
        }

        return convertProducto(productos);
    }

    /**
     * Recibe una lista de tiendas que convierte en objetos de tipo publicación.
     * 
     * @param tiendaList
     * @return 
     */
    public List<Publicacion> convertTienda(List<Tienda> tiendaList) {
        List<Publicacion> publicaciones = new ArrayList<>();
        for (Tienda tienda : tiendaList) {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(tienda.getDominio());
            publicacion.setDescripcion(tienda.getDominio());

            publicaciones.add(publicacion);
        }
        return publicaciones;
    }

    /**
     * Recibe una lista de productos que convierte a objetos de tipo publicación.
     * 
     * @param productoPSList
     * @return 
     */
    public List<Publicacion> convertProducto(List<ProductoPS> productoPSList) {
        List<Publicacion> publicaciones = new ArrayList<>();
        for (ProductoPS productoPS : productoPSList) {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(productoPS.getTitulo());
            publicacion.setDescripcion(productoPS.getDescripcion());
            publicacion.setTipo(TipoPublicacionEnum.EXTERNA);
            publicacion.setLink(productoPS.getUrl());
            
            List<Recurso> recursoList = new ArrayList<>();
            Recurso recurso = new Recurso();
            recurso.setRuta(productoPS.getRutaimagen());
            recursoList.add(recurso);
            publicacion.setRecursoList(recursoList);

            List<Producto> productoList = new ArrayList();
            Producto producto = new Producto();
            producto.setPrecio(productoPS.getPrecio());
            productoList.add(producto);
            publicacion.setProductoList(productoList);

            Usuario usuario = new Usuario();
            usuario.setPuntaje(3);
            usuario.setId(Long.MIN_VALUE);
            publicacion.setUsuario(usuario);

            publicaciones.add(publicacion);
        }
        return publicaciones;
    }

    /**
     * Devuelve el total de publicaciones que existen del tipo especificado.
     * 
     * @param tipo
     * @return 
     */
    public Long getTotalPublicaciones(TipoPublicacionEnum tipo) {
        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);

        return (esPagada)
                ? publicacionRepository.getTotalPublicacionesPagadas()
                : publicacionRepository.getTotalPublicacionesGratis();
    }

    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage, TipoPublicacionEnum tipo, long categoria_id, int nivel) {
        Iterable<Publicacion> publicaciones = new ArrayList();

        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);

        if (esPagada) {
            publicaciones = publicacionRepository.getPublicacionesPagadas(page, itemsByPage, categoria_id);

        } else {

            switch (nivel) {
                case 1:
                    publicaciones = publicacionRepository.getPublicacionesGratisCat(page, itemsByPage, categoria_id);
                    break;
                case 2:
                    publicaciones = publicacionRepository.getPublicacionesGratisSubCat(page, itemsByPage, categoria_id);
                    break;
                case 3:
                    publicaciones = publicacionRepository.getPublicacionesGratisSubSubCat(page, itemsByPage, categoria_id);
                    break;
            }
        }

        return publicaciones;
    }

    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage, TipoPublicacionEnum tipo) {
        Iterable<Publicacion> publicaciones = new ArrayList();

        boolean esPagada = TipoPublicacionEnum.PAGADA.equals(tipo);

        publicaciones = (esPagada)
                ? publicacionRepository.getPublicacionesPagadas(page, itemsByPage)
                : publicacionRepository.getPublicacionesGratis(page, itemsByPage);

        //Replace backslashes by forward slashes in order to work well with firefox in windows
//        for(Publicacion publicacion : publicaciones){
//            publicacion.getUbicacionList().size();
//            publicacion.getComentarioList().size();
//            publicacion.getProductoList().size();
//            for(Recurso recurso : publicacion.getRecursoList()){                                      
//                recurso.setRuta(recurso.getRuta().replace('\\', '/'));
//            }
//        }
//            for(Publicacion publicacion : publicaciones){
//                publicacion.getRecursoList().size();
//            }
        return publicaciones;
    }

    public Iterable<Publicacion> getPublicaciones(int page, int itemsByPage, TipoPublicacionEnum tipo, Boolean vendidas , Usuario usuario) {
        return publicacionRepository.getPublicacionesByUsuario(page, itemsByPage, tipo, vendidas, usuario);
    }

    /**
     * Devuelve las publicaciones que concuerden con el termino de búsqueda
     * 
     * @param page
     * @param match
     * @return 
     */
    public Iterable<Publicacion> getPublicaciones(int page, String match) {
        int totalAnuncios = Constantes.TOTAL_ANUNCIOS_GRATIS_MOSTRAR;                
        Iterable<Publicacion> publicaciones = publicacionRepository.getPublicacionesGratisByMatch(page, totalAnuncios, match);                
        return publicaciones;
    }

    public GridResponse getPublicacionesGrid(int page, int pageZise, TipoPublicacionEnum tipo) {
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);
        grid.setRows(getPublicaciones(page, pageZise, tipo));
        grid.setTotal(getTotalPublicaciones(tipo).intValue());
        return grid;
    }

    public GridResponse getPublicacionesGrid(int page, int pageZise, Sort sort, TipoPublicacionEnum tipo) {
        int totalPublicaiones = getTotalPublicaciones(tipo).intValue();
        GridResponse<Publicacion> grid = new GridResponse<>();
        grid.setPage(page);
        grid.setRecords(pageZise);
        grid.setTotal(totalPublicaiones);
        grid.setTotalPages(Utilidades.calculateTotalPages(totalPublicaiones, pageZise));
        return grid;
    }

}
