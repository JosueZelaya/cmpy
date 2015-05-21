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
@Table(name = "continente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Continente.findAll", query = "SELECT c FROM Continente c"),
    @NamedQuery(name = "Continente.findByContinenteId", query = "SELECT c FROM Continente c WHERE c.continenteId = :continenteId"),
    @NamedQuery(name = "Continente.findByNombre", query = "SELECT c FROM Continente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Continente.findBySisActivo", query = "SELECT c FROM Continente c WHERE c.sisActivo = :sisActivo"),
    @NamedQuery(name = "Continente.findBySisFechaCreacion", query = "SELECT c FROM Continente c WHERE c.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Continente.findBySisFechaModificacion", query = "SELECT c FROM Continente c WHERE c.sisFechaModificacion = :sisFechaModificacion")})
public class Continente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "continente_id")
    private Long continenteId;
    @Size(max = 2147483647)
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
    @OneToMany(mappedBy = "fkContinente")
    private List<Pais> paisList;

    public Continente() {
    }

    public Continente(Long continenteId) {
        this.continenteId = continenteId;
    }

    public Continente(Long continenteId, boolean sisActivo) {
        this.continenteId = continenteId;
        this.sisActivo = sisActivo;
    }

    public Long getContinenteId() {
        return continenteId;
    }

    public void setContinenteId(Long continenteId) {
        this.continenteId = continenteId;
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
    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (continenteId != null ? continenteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Continente)) {
            return false;
        }
        Continente other = (Continente) object;
        if ((this.continenteId == null && other.continenteId != null) || (this.continenteId != null && !this.continenteId.equals(other.continenteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Continente[ continenteId=" + continenteId + " ]";
    }
    
}
