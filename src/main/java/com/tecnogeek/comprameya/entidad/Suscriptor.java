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
@Table(name = "suscriptor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suscriptor.findAll", query = "SELECT s FROM Suscriptor s"),
    @NamedQuery(name = "Suscriptor.findBySuscriptorId", query = "SELECT s FROM Suscriptor s WHERE s.suscriptorId = :suscriptorId"),
    @NamedQuery(name = "Suscriptor.findBySisActivo", query = "SELECT s FROM Suscriptor s WHERE s.sisActivo = :sisActivo"),
    @NamedQuery(name = "Suscriptor.findBySisFechaCreacion", query = "SELECT s FROM Suscriptor s WHERE s.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Suscriptor.findBySisFechaModificacion", query = "SELECT s FROM Suscriptor s WHERE s.sisFechaModificacion = :sisFechaModificacion")})
public class Suscriptor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "suscriptor_id")
    private Long suscriptorId;
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
    @JoinColumn(name = "fk_usuario_proveedor", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioProveedor;
    @JoinColumn(name = "fk_usuario_suscriptor", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioSuscriptor;

    public Suscriptor() {
    }

    public Suscriptor(Long suscriptorId) {
        this.suscriptorId = suscriptorId;
    }

    public Suscriptor(Long suscriptorId, boolean sisActivo) {
        this.suscriptorId = suscriptorId;
        this.sisActivo = sisActivo;
    }

    public Long getSuscriptorId() {
        return suscriptorId;
    }

    public void setSuscriptorId(Long suscriptorId) {
        this.suscriptorId = suscriptorId;
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

    public Usuario getFkUsuarioProveedor() {
        return fkUsuarioProveedor;
    }

    public void setFkUsuarioProveedor(Usuario fkUsuarioProveedor) {
        this.fkUsuarioProveedor = fkUsuarioProveedor;
    }

    public Usuario getFkUsuarioSuscriptor() {
        return fkUsuarioSuscriptor;
    }

    public void setFkUsuarioSuscriptor(Usuario fkUsuarioSuscriptor) {
        this.fkUsuarioSuscriptor = fkUsuarioSuscriptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suscriptorId != null ? suscriptorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suscriptor)) {
            return false;
        }
        Suscriptor other = (Suscriptor) object;
        if ((this.suscriptorId == null && other.suscriptorId != null) || (this.suscriptorId != null && !this.suscriptorId.equals(other.suscriptorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Suscriptor[ suscriptorId=" + suscriptorId + " ]";
    }
    
}
