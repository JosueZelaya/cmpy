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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findByMensajeId", query = "SELECT m FROM Mensaje m WHERE m.mensajeId = :mensajeId"),
    @NamedQuery(name = "Mensaje.findByTitulo", query = "SELECT m FROM Mensaje m WHERE m.titulo = :titulo"),
    @NamedQuery(name = "Mensaje.findByTexto", query = "SELECT m FROM Mensaje m WHERE m.texto = :texto"),
    @NamedQuery(name = "Mensaje.findBySisActivo", query = "SELECT m FROM Mensaje m WHERE m.sisActivo = :sisActivo"),
    @NamedQuery(name = "Mensaje.findBySisFechaCreacion", query = "SELECT m FROM Mensaje m WHERE m.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Mensaje.findBySisFechaModificacion", query = "SELECT m FROM Mensaje m WHERE m.sisFechaModificacion = :sisFechaModificacion")})
public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mensaje_id")
    private Long mensajeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "texto")
    private String texto;
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
    @OneToMany(mappedBy = "fkMensaje")
    private List<Destinatario> destinatarioList;
    @JoinColumn(name = "fk_usuario_emisor", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario fkUsuarioEmisor;

    public Mensaje() {
    }

    public Mensaje(Long mensajeId) {
        this.mensajeId = mensajeId;
    }

    public Mensaje(Long mensajeId, String titulo, String texto, boolean sisActivo) {
        this.mensajeId = mensajeId;
        this.titulo = titulo;
        this.texto = texto;
        this.sisActivo = sisActivo;
    }

    public Long getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(Long mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
    public List<Destinatario> getDestinatarioList() {
        return destinatarioList;
    }

    public void setDestinatarioList(List<Destinatario> destinatarioList) {
        this.destinatarioList = destinatarioList;
    }

    public Usuario getFkUsuarioEmisor() {
        return fkUsuarioEmisor;
    }

    public void setFkUsuarioEmisor(Usuario fkUsuarioEmisor) {
        this.fkUsuarioEmisor = fkUsuarioEmisor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensajeId != null ? mensajeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.mensajeId == null && other.mensajeId != null) || (this.mensajeId != null && !this.mensajeId.equals(other.mensajeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Mensaje[ mensajeId=" + mensajeId + " ]";
    }
    
}
