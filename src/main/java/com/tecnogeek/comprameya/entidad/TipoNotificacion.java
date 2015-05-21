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
@Table(name = "tipo_notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNotificacion.findAll", query = "SELECT t FROM TipoNotificacion t"),
    @NamedQuery(name = "TipoNotificacion.findByTipoNotificacionId", query = "SELECT t FROM TipoNotificacion t WHERE t.tipoNotificacionId = :tipoNotificacionId"),
    @NamedQuery(name = "TipoNotificacion.findByNombre", query = "SELECT t FROM TipoNotificacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoNotificacion.findByDescripcion", query = "SELECT t FROM TipoNotificacion t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoNotificacion.findBySisActivo", query = "SELECT t FROM TipoNotificacion t WHERE t.sisActivo = :sisActivo"),
    @NamedQuery(name = "TipoNotificacion.findBySisFechaCreacion", query = "SELECT t FROM TipoNotificacion t WHERE t.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "TipoNotificacion.findBySisFechaModificacion", query = "SELECT t FROM TipoNotificacion t WHERE t.sisFechaModificacion = :sisFechaModificacion")})
public class TipoNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_notificacion_id")
    private Long tipoNotificacionId;
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
    @OneToMany(mappedBy = "fkTipoNotificacion")
    private List<Notificacion> notificacionList;

    public TipoNotificacion() {
    }

    public TipoNotificacion(Long tipoNotificacionId) {
        this.tipoNotificacionId = tipoNotificacionId;
    }

    public TipoNotificacion(Long tipoNotificacionId, String nombre, boolean sisActivo) {
        this.tipoNotificacionId = tipoNotificacionId;
        this.nombre = nombre;
        this.sisActivo = sisActivo;
    }

    public Long getTipoNotificacionId() {
        return tipoNotificacionId;
    }

    public void setTipoNotificacionId(Long tipoNotificacionId) {
        this.tipoNotificacionId = tipoNotificacionId;
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
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoNotificacionId != null ? tipoNotificacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNotificacion)) {
            return false;
        }
        TipoNotificacion other = (TipoNotificacion) object;
        if ((this.tipoNotificacionId == null && other.tipoNotificacionId != null) || (this.tipoNotificacionId != null && !this.tipoNotificacionId.equals(other.tipoNotificacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.TipoNotificacion[ tipoNotificacionId=" + tipoNotificacionId + " ]";
    }
    
}
