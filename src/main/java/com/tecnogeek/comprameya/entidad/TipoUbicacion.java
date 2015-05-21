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
@Table(name = "tipo_ubicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUbicacion.findAll", query = "SELECT t FROM TipoUbicacion t"),
    @NamedQuery(name = "TipoUbicacion.findByTipoUbicacionId", query = "SELECT t FROM TipoUbicacion t WHERE t.tipoUbicacionId = :tipoUbicacionId"),
    @NamedQuery(name = "TipoUbicacion.findBySisActivo", query = "SELECT t FROM TipoUbicacion t WHERE t.sisActivo = :sisActivo"),
    @NamedQuery(name = "TipoUbicacion.findBySisFechaCreacion", query = "SELECT t FROM TipoUbicacion t WHERE t.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "TipoUbicacion.findBySisFechaModificacion", query = "SELECT t FROM TipoUbicacion t WHERE t.sisFechaModificacion = :sisFechaModificacion"),
    @NamedQuery(name = "TipoUbicacion.findByNombre", query = "SELECT t FROM TipoUbicacion t WHERE t.nombre = :nombre")})
public class TipoUbicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_ubicacion_id")
    private Long tipoUbicacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sis_activo")
    private boolean sisActivo;
    @Column(name = "sis_fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sisFechaCreacion;
    @Column(name = "sis_fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sisFechaModificacion;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "fkTipoUbicacion")
    private List<Ubicacion> ubicacionList;

    public TipoUbicacion() {
    }

    public TipoUbicacion(Long tipoUbicacionId) {
        this.tipoUbicacionId = tipoUbicacionId;
    }

    public TipoUbicacion(Long tipoUbicacionId, boolean sisActivo) {
        this.tipoUbicacionId = tipoUbicacionId;
        this.sisActivo = sisActivo;
    }

    public Long getTipoUbicacionId() {
        return tipoUbicacionId;
    }

    public void setTipoUbicacionId(Long tipoUbicacionId) {
        this.tipoUbicacionId = tipoUbicacionId;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Ubicacion> getUbicacionList() {
        return ubicacionList;
    }

    public void setUbicacionList(List<Ubicacion> ubicacionList) {
        this.ubicacionList = ubicacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoUbicacionId != null ? tipoUbicacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUbicacion)) {
            return false;
        }
        TipoUbicacion other = (TipoUbicacion) object;
        if ((this.tipoUbicacionId == null && other.tipoUbicacionId != null) || (this.tipoUbicacionId != null && !this.tipoUbicacionId.equals(other.tipoUbicacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.TipoUbicacion[ tipoUbicacionId=" + tipoUbicacionId + " ]";
    }
    
}
