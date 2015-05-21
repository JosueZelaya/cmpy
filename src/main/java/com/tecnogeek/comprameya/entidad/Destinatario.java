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
@Table(name = "destinatario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destinatario.findAll", query = "SELECT d FROM Destinatario d"),
    @NamedQuery(name = "Destinatario.findByDestinatarioId", query = "SELECT d FROM Destinatario d WHERE d.destinatarioId = :destinatarioId"),
    @NamedQuery(name = "Destinatario.findBySisActivo", query = "SELECT d FROM Destinatario d WHERE d.sisActivo = :sisActivo"),
    @NamedQuery(name = "Destinatario.findBySisFechaCreacion", query = "SELECT d FROM Destinatario d WHERE d.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Destinatario.findBySisFechaModificacion", query = "SELECT d FROM Destinatario d WHERE d.sisFechaModificacion = :sisFechaModificacion")})
public class Destinatario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "destinatario_id")
    private Long destinatarioId;
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
    @JoinColumn(name = "fk_mensaje", referencedColumnName = "mensaje_id")
    @ManyToOne
    private Mensaje fkMensaje;
    @JoinColumn(name = "fk_usuario_destinatario", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioDestinatario;

    public Destinatario() {
    }

    public Destinatario(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Destinatario(Long destinatarioId, boolean sisActivo) {
        this.destinatarioId = destinatarioId;
        this.sisActivo = sisActivo;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
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

    public Mensaje getFkMensaje() {
        return fkMensaje;
    }

    public void setFkMensaje(Mensaje fkMensaje) {
        this.fkMensaje = fkMensaje;
    }

    public Usuario getFkUsuarioDestinatario() {
        return fkUsuarioDestinatario;
    }

    public void setFkUsuarioDestinatario(Usuario fkUsuarioDestinatario) {
        this.fkUsuarioDestinatario = fkUsuarioDestinatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinatarioId != null ? destinatarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinatario)) {
            return false;
        }
        Destinatario other = (Destinatario) object;
        if ((this.destinatarioId == null && other.destinatarioId != null) || (this.destinatarioId != null && !this.destinatarioId.equals(other.destinatarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Destinatario[ destinatarioId=" + destinatarioId + " ]";
    }
    
}
