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
@Table(name = "tipo_caracteristica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCaracteristica.findAll", query = "SELECT t FROM TipoCaracteristica t"),
    @NamedQuery(name = "TipoCaracteristica.findByTipoCaracteristicaId", query = "SELECT t FROM TipoCaracteristica t WHERE t.tipoCaracteristicaId = :tipoCaracteristicaId"),
    @NamedQuery(name = "TipoCaracteristica.findByNombre", query = "SELECT t FROM TipoCaracteristica t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCaracteristica.findBySisActivo", query = "SELECT t FROM TipoCaracteristica t WHERE t.sisActivo = :sisActivo"),
    @NamedQuery(name = "TipoCaracteristica.findBySisFechaCreacion", query = "SELECT t FROM TipoCaracteristica t WHERE t.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "TipoCaracteristica.findBySisFechaModificacion", query = "SELECT t FROM TipoCaracteristica t WHERE t.sisFechaModificacion = :sisFechaModificacion")})
public class TipoCaracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_caracteristica_id")
    private Long tipoCaracteristicaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(mappedBy = "fkTipoCaracteristica")
    private List<Caracteristica> caracteristicaList;

    public TipoCaracteristica() {
    }

    public TipoCaracteristica(Long tipoCaracteristicaId) {
        this.tipoCaracteristicaId = tipoCaracteristicaId;
    }

    public TipoCaracteristica(Long tipoCaracteristicaId, String nombre, boolean sisActivo) {
        this.tipoCaracteristicaId = tipoCaracteristicaId;
        this.nombre = nombre;
        this.sisActivo = sisActivo;
    }

    public Long getTipoCaracteristicaId() {
        return tipoCaracteristicaId;
    }

    public void setTipoCaracteristicaId(Long tipoCaracteristicaId) {
        this.tipoCaracteristicaId = tipoCaracteristicaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCaracteristicaId != null ? tipoCaracteristicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCaracteristica)) {
            return false;
        }
        TipoCaracteristica other = (TipoCaracteristica) object;
        if ((this.tipoCaracteristicaId == null && other.tipoCaracteristicaId != null) || (this.tipoCaracteristicaId != null && !this.tipoCaracteristicaId.equals(other.tipoCaracteristicaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.TipoCaracteristica[ tipoCaracteristicaId=" + tipoCaracteristicaId + " ]";
    }
    
}
