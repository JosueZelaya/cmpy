/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByProductoId", query = "SELECT p FROM Producto p WHERE p.productoId = :productoId"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByConcluido", query = "SELECT p FROM Producto p WHERE p.concluido = :concluido"),
    @NamedQuery(name = "Producto.findBySisActivo", query = "SELECT p FROM Producto p WHERE p.sisActivo = :sisActivo"),
    @NamedQuery(name = "Producto.findBySisFechaCreacion", query = "SELECT p FROM Producto p WHERE p.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Producto.findBySisFechaModificacion", query = "SELECT p FROM Producto p WHERE p.sisFechaModificacion = :sisFechaModificacion")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "producto_id")
    private Long productoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "concluido")
    private boolean concluido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sis_activo")
    private boolean sisActivo;
    @Column(name = "sis_fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date sisFechaCreacion;
    @Column(name = "sis_fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date sisFechaModificacion;
    @OneToMany(mappedBy = "fkProducto")
    private List<Caracteristica> caracteristicaList;
    @JoinColumn(name = "fk_sub_tipo_producto", referencedColumnName = "categoria_id")
    @ManyToOne
    private Categoria fkSubTipoProducto;
    @JoinColumn(name = "fk_modelo", referencedColumnName = "modelo_id")
    @ManyToOne
    private Modelo fkModelo;
    @JoinColumn(name = "fk_publicacion", referencedColumnName = "publicacion_id")
    @ManyToOne
    private Publicacion fkPublicacion;
    @OneToMany(mappedBy = "fkProducto")
    private List<Cesta> cestaList;

    public Producto() {
    }

    public Producto(Long productoId) {
        this.productoId = productoId;
    }

    public Producto(Long productoId, String nombre, BigDecimal precio, boolean concluido, boolean sisActivo) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.concluido = concluido;
        this.sisActivo = sisActivo;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public boolean getSisActivo() {
        return sisActivo;
    }

    public void setSisActivo(boolean sisActivo) {
        this.sisActivo = sisActivo;
    }

    public Date getSisFechaCreacion() {
        return sisFechaCreacion;
    }

    public void setSisFechaCreacion(Date sisFechaCreacion) {
        this.sisFechaCreacion = sisFechaCreacion;
    }

    public Date getSisFechaModificacion() {
        return sisFechaModificacion;
    }

    public void setSisFechaModificacion(Date sisFechaModificacion) {
        this.sisFechaModificacion = sisFechaModificacion;
    }

    @XmlTransient
    public List<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }

    public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
    }

    public Categoria getFkSubTipoProducto() {
        return fkSubTipoProducto;
    }

    public void setFkSubTipoProducto(Categoria fkSubTipoProducto) {
        this.fkSubTipoProducto = fkSubTipoProducto;
    }

    public Modelo getFkModelo() {
        return fkModelo;
    }

    public void setFkModelo(Modelo fkModelo) {
        this.fkModelo = fkModelo;
    }

    public Publicacion getFkPublicacion() {
        return fkPublicacion;
    }

    public void setFkPublicacion(Publicacion fkPublicacion) {
        this.fkPublicacion = fkPublicacion;
    }

    @XmlTransient
    public List<Cesta> getCestaList() {
        return cestaList;
    }

    public void setCestaList(List<Cesta> cestaList) {
        this.cestaList = cestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productoId == null && other.productoId != null) || (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Producto[ productoId=" + productoId + " ]";
    }
    
}
