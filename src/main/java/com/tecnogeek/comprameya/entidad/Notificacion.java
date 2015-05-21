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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByNotificacionId", query = "SELECT n FROM Notificacion n WHERE n.notificacionId = :notificacionId"),
    @NamedQuery(name = "Notificacion.findByTabla", query = "SELECT n FROM Notificacion n WHERE n.tabla = :tabla"),
    @NamedQuery(name = "Notificacion.findByTablaId", query = "SELECT n FROM Notificacion n WHERE n.tablaId = :tablaId"),
    @NamedQuery(name = "Notificacion.findByVisto", query = "SELECT n FROM Notificacion n WHERE n.visto = :visto"),
    @NamedQuery(name = "Notificacion.findBySisActivo", query = "SELECT n FROM Notificacion n WHERE n.sisActivo = :sisActivo"),
    @NamedQuery(name = "Notificacion.findBySisFechaCreacion", query = "SELECT n FROM Notificacion n WHERE n.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Notificacion.findBySisFechaModificacion", query = "SELECT n FROM Notificacion n WHERE n.sisFechaModificacion = :sisFechaModificacion")})
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notificacion_id")
    private Long notificacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tabla")
    private String tabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tabla_id")
    private int tablaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visto")
    private boolean visto;
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
    @JoinColumn(name = "fk_tipo_notificacion", referencedColumnName = "tipo_notificacion_id")
    @ManyToOne
    private TipoNotificacion fkTipoNotificacion;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuario;

    public Notificacion() {
    }

    public Notificacion(Long notificacionId) {
        this.notificacionId = notificacionId;
    }

    public Notificacion(Long notificacionId, String tabla, int tablaId, boolean visto, boolean sisActivo) {
        this.notificacionId = notificacionId;
        this.tabla = tabla;
        this.tablaId = tablaId;
        this.visto = visto;
        this.sisActivo = sisActivo;
    }

    public Long getNotificacionId() {
        return notificacionId;
    }

    public void setNotificacionId(Long notificacionId) {
        this.notificacionId = notificacionId;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getTablaId() {
        return tablaId;
    }

    public void setTablaId(int tablaId) {
        this.tablaId = tablaId;
    }

    public boolean getVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
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

    public TipoNotificacion getFkTipoNotificacion() {
        return fkTipoNotificacion;
    }

    public void setFkTipoNotificacion(TipoNotificacion fkTipoNotificacion) {
        this.fkTipoNotificacion = fkTipoNotificacion;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificacionId != null ? notificacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.notificacionId == null && other.notificacionId != null) || (this.notificacionId != null && !this.notificacionId.equals(other.notificacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Notificacion[ notificacionId=" + notificacionId + " ]";
    }
    
}
