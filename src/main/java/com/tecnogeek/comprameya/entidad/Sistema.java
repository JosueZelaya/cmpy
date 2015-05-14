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
@Table(name = "sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s"),
    @NamedQuery(name = "Sistema.findBySistemaId", query = "SELECT s FROM Sistema s WHERE s.sistemaId = :sistemaId"),
    @NamedQuery(name = "Sistema.findByVariable", query = "SELECT s FROM Sistema s WHERE s.variable = :variable"),
    @NamedQuery(name = "Sistema.findByValor", query = "SELECT s FROM Sistema s WHERE s.valor = :valor"),
    @NamedQuery(name = "Sistema.findBySisActivo", query = "SELECT s FROM Sistema s WHERE s.sisActivo = :sisActivo"),
    @NamedQuery(name = "Sistema.findBySisFechaCreacion", query = "SELECT s FROM Sistema s WHERE s.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Sistema.findBySisFechaModificacion", query = "SELECT s FROM Sistema s WHERE s.sisFechaModificacion = :sisFechaModificacion")})
public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sistema_id")
    private Long sistemaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "variable")
    private String variable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
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

    public Sistema() {
    }

    public Sistema(Long sistemaId) {
        this.sistemaId = sistemaId;
    }

    public Sistema(Long sistemaId, String variable, String valor, boolean sisActivo) {
        this.sistemaId = sistemaId;
        this.variable = variable;
        this.valor = valor;
        this.sisActivo = sisActivo;
    }

    public Long getSistemaId() {
        return sistemaId;
    }

    public void setSistemaId(Long sistemaId) {
        this.sistemaId = sistemaId;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sistemaId != null ? sistemaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistema)) {
            return false;
        }
        Sistema other = (Sistema) object;
        if ((this.sistemaId == null && other.sistemaId != null) || (this.sistemaId != null && !this.sistemaId.equals(other.sistemaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Sistema[ sistemaId=" + sistemaId + " ]";
    }
    
}
