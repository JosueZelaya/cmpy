/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "ubicacion")
@ToString (exclude = {"personaList"})
@Setter
@Getter
public class Ubicacion extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
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
    @JsonManagedReference
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    @ManyToOne
    private Ciudad ciudad;
    @JsonManagedReference
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @ManyToOne    
    private Publicacion publicacion;
    @JsonManagedReference
    @JoinColumn(name = "tipo_ubicacion", referencedColumnName = "id")
    @ManyToOne
    private TipoUbicacion tipoUbicacion;
    @JsonBackReference
    @OneToMany(mappedBy = "ubicacion")
    private List<Persona> personaList;
        
}
