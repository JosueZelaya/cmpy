/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "contacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
    @NamedQuery(name = "Contacto.findByContactoId", query = "SELECT c FROM Contacto c WHERE c.contactoId = :contactoId"),
    @NamedQuery(name = "Contacto.findBySisActivo", query = "SELECT c FROM Contacto c WHERE c.sisActivo = :sisActivo"),
    @NamedQuery(name = "Contacto.findBySisFechaCreacion", query = "SELECT c FROM Contacto c WHERE c.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Contacto.findBySisFechaModificacion", query = "SELECT c FROM Contacto c WHERE c.sisFechaModificacion = :sisFechaModificacion")})
public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contacto_id")
    private Long contactoId;
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
    @JoinColumn(name = "fk_usuario_contacto", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioContacto;
    @JoinColumn(name = "fk_usuario_duenio", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioDuenio;

    public Contacto() {
    }

    public Contacto(Long contactoId) {
        this.contactoId = contactoId;
    }

    public Contacto(Long contactoId, boolean sisActivo) {
        this.contactoId = contactoId;
        this.sisActivo = sisActivo;
    }

    public Long getContactoId() {
        return contactoId;
    }

    public void setContactoId(Long contactoId) {
        this.contactoId = contactoId;
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

    public Usuario getFkUsuarioContacto() {
        return fkUsuarioContacto;
    }

    public void setFkUsuarioContacto(Usuario fkUsuarioContacto) {
        this.fkUsuarioContacto = fkUsuarioContacto;
    }

    public Usuario getFkUsuarioDuenio() {
        return fkUsuarioDuenio;
    }

    public void setFkUsuarioDuenio(Usuario fkUsuarioDuenio) {
        this.fkUsuarioDuenio = fkUsuarioDuenio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactoId != null ? contactoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.contactoId == null && other.contactoId != null) || (this.contactoId != null && !this.contactoId.equals(other.contactoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Contacto[ contactoId=" + contactoId + " ]";
    }
    
}
