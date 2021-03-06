/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tecnobitz.cmpy.enums.TipoPublicacionEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "publicacion")
@Getter
@Setter
@ToString(exclude={"recursoList","ubicacionList","productoList","comentarioList","suscriptoresList"})
public class Publicacion extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vendido")
    private boolean vendido;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visto")
    private int visto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje")
    private int puntaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "denuncias")
    private int denuncias;  
    
    @JsonBackReference
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<SuscripcionPublicacion> suscriptoresList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "publicacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Recurso> recursoList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<Ubicacion> ubicacionList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<Producto> productoList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacion")
    private List<Comentario> comentarioList;
    
    @JsonManagedReference
    @JoinColumn(name = "tipo_publicacion", referencedColumnName = "id")
    @ManyToOne
    private TipoPublicacion tipo;
    
    @JsonManagedReference
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuario;
    
    public TipoPublicacionEnum getTipo(){
        return TipoPublicacionEnum.getTipo(tipo.getId(),tipo.getNombre());
    }
    
    public void setTipo(TipoPublicacionEnum tipo){
        this.tipo = new TipoPublicacion();
        this.tipo.setId(tipo.getCodigo());
        this.tipo.setNombre(tipo.getDescripcion());
    }
    
    public void setTipo(TipoPublicacion tipo){
        this.tipo = tipo;
    }
    
    @Transient
    private String link;
    
    @Transient
    public int getPuntaje(){
        return (this.usuario == null)?
                0: 
                this.usuario.getPuntaje();
    }

    @Transient
    public String getImgUrl(){
        return (!this.recursoList.isEmpty())?
                this.recursoList.get(0).getRuta():
                "";
    }
    
    @Transient
    public BigDecimal getPrecio(){
        return (!this.productoList.isEmpty())?
                this.productoList.get(0).getPrecio():
                new BigDecimal(0);
    }
    
}
