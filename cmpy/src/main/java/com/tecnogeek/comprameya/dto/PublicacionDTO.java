/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import com.tecnobitz.cmpy.entidad.Comentario;
import com.tecnobitz.cmpy.entidad.Producto;
import com.tecnobitz.cmpy.entidad.Publicacion;
import com.tecnobitz.cmpy.entidad.Recurso;
import com.tecnobitz.cmpy.entidad.TipoPublicacion;
import com.tecnobitz.cmpy.entidad.Ubicacion;
import com.tecnobitz.cmpy.entidad.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexander
 */

@Getter
@Setter
public class PublicacionDTO implements Serializable{
    
    private Long id;
    private String titulo;
    private String descripcion;
    private boolean vendido;
    private Date fechaVencimiento;
    private int visto;
    private int puntaje;
    private int denuncias;    
    private List<String> imgUrlList;
    private List<Ubicacion> ubicacionList;
    private List<Producto> productoList;
    private List<Comentario> comentarioList;
    private TipoPublicacion fkTipoPublicacion;
    private Usuario fkUsuario;
    
    public PublicacionDTO(Publicacion p){
        this.id = p.getId();
        this.titulo = p.getTitulo();
        this.descripcion = p.getDescripcion();
        this.vendido = p.isVendido();
        this.fechaVencimiento = p.getFechaVencimiento();
        this.visto = p.getVisto();
        this.puntaje = p.getPuntaje();        
        this.denuncias = p.getDenuncias();
        
        for(Recurso r : p.getRecursoList()){
            imgUrlList.add(r.getRuta());
        }
        
        
        
        
    }
    
}
