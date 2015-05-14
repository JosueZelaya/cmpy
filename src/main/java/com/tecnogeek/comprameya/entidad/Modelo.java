/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
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
@Table(name = "modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m"),
    @NamedQuery(name = "Modelo.findByModeloId", query = "SELECT m FROM Modelo m WHERE m.modeloId = :modeloId"),
    @NamedQuery(name = "Modelo.findByNombre", query = "SELECT m FROM Modelo m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Modelo.findByDescripcion", query = "SELECT m FROM Modelo m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Modelo.findBySisActivo", query = "SELECT m FROM Modelo m WHERE m.sisActivo = :sisActivo"),
    @NamedQuery(name = "Modelo.findBySisFechaCreacion", query = "SELECT m FROM Modelo m WHERE m.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Modelo.findBySisFechaModificacion", query = "SELECT m FROM Modelo m WHERE m.sisFechaModificacion = :sisFechaModificacion")})
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "modelo_id")
    private Long modeloId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(mappedBy = "fkModelo")
    private List<Producto> productoList;
    @JoinColumn(name = "fk_marca", referencedColumnName = "marca_id")
    @ManyToOne
    private Marca fkMarca;

    public Modelo() {
    }

    public Modelo(Long modeloId) {
        this.modeloId = modeloId;
    }

    public Modelo(Long modeloId, String nombre, boolean sisActivo) {
        this.modeloId = modeloId;
        this.nombre = nombre;
        this.sisActivo = sisActivo;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public Marca getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(Marca fkMarca) {
        this.fkMarca = fkMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modeloId != null ? modeloId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.modeloId == null && other.modeloId != null) || (this.modeloId != null && !this.modeloId.equals(other.modeloId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Modelo[ modeloId=" + modeloId + " ]";
    }
    
}
