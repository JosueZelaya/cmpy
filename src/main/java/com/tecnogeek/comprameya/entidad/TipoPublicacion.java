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
@Table(name = "tipo_publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPublicacion.findAll", query = "SELECT t FROM TipoPublicacion t"),
    @NamedQuery(name = "TipoPublicacion.findByTipoPublicacionId", query = "SELECT t FROM TipoPublicacion t WHERE t.tipoPublicacionId = :tipoPublicacionId"),
    @NamedQuery(name = "TipoPublicacion.findByNombre", query = "SELECT t FROM TipoPublicacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPublicacion.findBySisActivo", query = "SELECT t FROM TipoPublicacion t WHERE t.sisActivo = :sisActivo"),
    @NamedQuery(name = "TipoPublicacion.findBySisFechaCreacion", query = "SELECT t FROM TipoPublicacion t WHERE t.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "TipoPublicacion.findBySisFechaModificacion", query = "SELECT t FROM TipoPublicacion t WHERE t.sisFechaModificacion = :sisFechaModificacion")})
public class TipoPublicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_publicacion_id")
    private Long tipoPublicacionId;
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
    @OneToMany(mappedBy = "fkTipoPublicacion")
    private List<Publicacion> publicacionList;

    public TipoPublicacion() {
    }

    public TipoPublicacion(Long tipoPublicacionId) {
        this.tipoPublicacionId = tipoPublicacionId;
    }

    public TipoPublicacion(Long tipoPublicacionId, String nombre, boolean sisActivo) {
        this.tipoPublicacionId = tipoPublicacionId;
        this.nombre = nombre;
        this.sisActivo = sisActivo;
    }

    public Long getTipoPublicacionId() {
        return tipoPublicacionId;
    }

    public void setTipoPublicacionId(Long tipoPublicacionId) {
        this.tipoPublicacionId = tipoPublicacionId;
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
    public List<Publicacion> getPublicacionList() {
        return publicacionList;
    }

    public void setPublicacionList(List<Publicacion> publicacionList) {
        this.publicacionList = publicacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoPublicacionId != null ? tipoPublicacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPublicacion)) {
            return false;
        }
        TipoPublicacion other = (TipoPublicacion) object;
        if ((this.tipoPublicacionId == null && other.tipoPublicacionId != null) || (this.tipoPublicacionId != null && !this.tipoPublicacionId.equals(other.tipoPublicacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.TipoPublicacion[ tipoPublicacionId=" + tipoPublicacionId + " ]";
    }
    
}
