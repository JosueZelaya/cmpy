/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "ubicacion")
@Data
public class Ubicacion extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ubicacion_id")
    private Long Id;
    @Size(max = 2147483647)
    @Column(name = "poligono")
    private String poligono;
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 2147483647)
    @Column(name = "local")
    private String local;
    @Size(max = 2147483647)
    @Column(name = "calle")
    private String calle;
    @Size(max = 2147483647)
    @Column(name = "avenida")
    private String avenida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gm_latitud")
    private Double gmLatitud;
    @Column(name = "gm_longitud")
    private Double gmLongitud;
    @JoinColumn(name = "fk_ciudad", referencedColumnName = "ciudad_id")
    @ManyToOne
    private Ciudad fkCiudad;
    @JsonBackReference
    @JoinColumn(name = "fk_publicacion", referencedColumnName = "publicacion_id")
    @ManyToOne    
    private Publicacion fkPublicacion;
    @JoinColumn(name = "fk_tipo_ubicacion", referencedColumnName = "tipo_ubicacion_id")
    @ManyToOne
    private TipoUbicacion fkTipoUbicacion;
    @OneToMany(mappedBy = "fkUbicacion")
    private List<Persona> personaList;
        
}
