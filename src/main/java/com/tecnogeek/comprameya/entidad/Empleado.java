/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByEmpleadoId", query = "SELECT e FROM Empleado e WHERE e.empleadoId = :empleadoId"),
    @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleado.findByNit", query = "SELECT e FROM Empleado e WHERE e.nit = :nit"),
    @NamedQuery(name = "Empleado.findByAfp", query = "SELECT e FROM Empleado e WHERE e.afp = :afp"),
    @NamedQuery(name = "Empleado.findByIsss", query = "SELECT e FROM Empleado e WHERE e.isss = :isss"),
    @NamedQuery(name = "Empleado.findBySisActivo", query = "SELECT e FROM Empleado e WHERE e.sisActivo = :sisActivo"),
    @NamedQuery(name = "Empleado.findBySisFechaCreacion", query = "SELECT e FROM Empleado e WHERE e.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Empleado.findBySisFechaModificacion", query = "SELECT e FROM Empleado e WHERE e.sisFechaModificacion = :sisFechaModificacion")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empleado_id")
    private Long empleadoId;
    @Column(name = "telefono")
    private BigInteger telefono;
    @Column(name = "nit")
    private BigInteger nit;
    @Column(name = "afp")
    private BigInteger afp;
    @Column(name = "isss")
    private BigInteger isss;
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
    @JoinColumn(name = "fk_empresa", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa fkEmpresa;
    @JoinColumn(name = "fk_persona", referencedColumnName = "persona_id")
    @ManyToOne
    private Persona fkPersona;
    @JoinColumn(name = "fk_puesto", referencedColumnName = "puesto_id")
    @ManyToOne
    private Puesto fkPuesto;

    public Empleado() {
    }

    public Empleado(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleado(Long empleadoId, boolean sisActivo) {
        this.empleadoId = empleadoId;
        this.sisActivo = sisActivo;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    public BigInteger getNit() {
        return nit;
    }

    public void setNit(BigInteger nit) {
        this.nit = nit;
    }

    public BigInteger getAfp() {
        return afp;
    }

    public void setAfp(BigInteger afp) {
        this.afp = afp;
    }

    public BigInteger getIsss() {
        return isss;
    }

    public void setIsss(BigInteger isss) {
        this.isss = isss;
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

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
    }

    public Puesto getFkPuesto() {
        return fkPuesto;
    }

    public void setFkPuesto(Puesto fkPuesto) {
        this.fkPuesto = fkPuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleadoId != null ? empleadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empleadoId == null && other.empleadoId != null) || (this.empleadoId != null && !this.empleadoId.equals(other.empleadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Empleado[ empleadoId=" + empleadoId + " ]";
    }
    
}
