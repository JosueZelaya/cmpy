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
@Table(name = "tipo_recurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRecurso.findAll", query = "SELECT t FROM TipoRecurso t"),
    @NamedQuery(name = "TipoRecurso.findByTipoRecusoId", query = "SELECT t FROM TipoRecurso t WHERE t.tipoRecusoId = :tipoRecusoId"),
    @NamedQuery(name = "TipoRecurso.findByNombre", query = "SELECT t FROM TipoRecurso t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoRecurso.findByDescripcion", query = "SELECT t FROM TipoRecurso t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoRecurso.findBySisActivo", query = "SELECT t FROM TipoRecurso t WHERE t.sisActivo = :sisActivo"),
    @NamedQuery(name = "TipoRecurso.findBySisFechaCreacion", query = "SELECT t FROM TipoRecurso t WHERE t.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "TipoRecurso.findBySisFechaModificacion", query = "SELECT t FROM TipoRecurso t WHERE t.sisFechaModificacion = :sisFechaModificacion")})
public class TipoRecurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_recuso_id")
    private Long tipoRecusoId;
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
    @OneToMany(mappedBy = "fkTipoRecurso")
    private List<Recurso> recursoList;

    public TipoRecurso() {
    }

    public TipoRecurso(Long tipoRecusoId) {
        this.tipoRecusoId = tipoRecusoId;
    }

    public TipoRecurso(Long tipoRecusoId, String nombre, String descripcion, boolean sisActivo) {
        this.tipoRecusoId = tipoRecusoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sisActivo = sisActivo;
    }

    public Long getTipoRecusoId() {
        return tipoRecusoId;
    }

    public void setTipoRecusoId(Long tipoRecusoId) {
        this.tipoRecusoId = tipoRecusoId;
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
    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoRecusoId != null ? tipoRecusoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecurso)) {
            return false;
        }
        TipoRecurso other = (TipoRecurso) object;
        if ((this.tipoRecusoId == null && other.tipoRecusoId != null) || (this.tipoRecusoId != null && !this.tipoRecusoId.equals(other.tipoRecusoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.TipoRecurso[ tipoRecusoId=" + tipoRecusoId + " ]";
    }
    
}
