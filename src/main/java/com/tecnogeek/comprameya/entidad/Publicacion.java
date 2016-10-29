/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import lombok.RequiredArgsConstructor;
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
@ToString(exclude={"publicacionList","recursoList","ubicacionList","productoList","comentarioList"})
@RequiredArgsConstructor 
public class Publicacion extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "publicacion_id")
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
    @Column(name = "concluido")
    private boolean concluido;
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
    @OneToMany(mappedBy = "fkPublicacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Recurso> recursoList;
    @JsonBackReference
    @OneToMany(mappedBy = "fkPublicacion", cascade = CascadeType.ALL)
    private List<Ubicacion> ubicacionList;
    @JsonBackReference
    @OneToMany(mappedBy = "fkPublicacion", cascade = CascadeType.ALL)
    private List<Producto> productoList;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPublicacion")
    private List<Comentario> comentarioList;
    @JsonManagedReference
    @JoinColumn(name = "fk_tipo_publicacion", referencedColumnName = "tipo_publicacion_id")
    @ManyToOne
    private TipoPublicacion fkTipoPublicacion;
    @JsonManagedReference
    @JoinColumn(name = "fk_usuario", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuario;
    
    @Transient
    public int getPuntaje(){
        return (this.fkUsuario == null)?
                0: 
                this.fkUsuario.getPuntaje();
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
