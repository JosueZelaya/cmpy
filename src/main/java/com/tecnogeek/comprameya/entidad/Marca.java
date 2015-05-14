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
@Table(name = "marca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByMarcaId", query = "SELECT m FROM Marca m WHERE m.marcaId = :marcaId"),
    @NamedQuery(name = "Marca.findByNombre", query = "SELECT m FROM Marca m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Marca.findByDescripcion", query = "SELECT m FROM Marca m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Marca.findBySisActivo", query = "SELECT m FROM Marca m WHERE m.sisActivo = :sisActivo"),
    @NamedQuery(name = "Marca.findBySisFechaCreacion", query = "SELECT m FROM Marca m WHERE m.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Marca.findBySisFechaModificacion", query = "SELECT m FROM Marca m WHERE m.sisFechaModificacion = :sisFechaModificacion")})
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "marca_id")
    private Long marcaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
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
    @OneToMany(mappedBy = "fkMarca")
    private List<Modelo> modeloList;

    public Marca() {
    }

    public Marca(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Marca(Long marcaId, String nombre, String descripcion, boolean sisActivo) {
        this.marcaId = marcaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sisActivo = sisActivo;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
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
    public List<Modelo> getModeloList() {
        return modeloList;
    }

    public void setModeloList(List<Modelo> modeloList) {
        this.modeloList = modeloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marcaId != null ? marcaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.marcaId == null && other.marcaId != null) || (this.marcaId != null && !this.marcaId.equals(other.marcaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Marca[ marcaId=" + marcaId + " ]";
    }
    
}
