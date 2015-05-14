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
@Table(name = "puesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puesto.findAll", query = "SELECT p FROM Puesto p"),
    @NamedQuery(name = "Puesto.findByPuestoId", query = "SELECT p FROM Puesto p WHERE p.puestoId = :puestoId"),
    @NamedQuery(name = "Puesto.findByNombre", query = "SELECT p FROM Puesto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Puesto.findByDescripcion", query = "SELECT p FROM Puesto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Puesto.findBySisActivo", query = "SELECT p FROM Puesto p WHERE p.sisActivo = :sisActivo"),
    @NamedQuery(name = "Puesto.findBySisFechaCreacion", query = "SELECT p FROM Puesto p WHERE p.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Puesto.findBySisFechaModificacion", query = "SELECT p FROM Puesto p WHERE p.sisFechaModificacion = :sisFechaModificacion")})
public class Puesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "puesto_id")
    private Long puestoId;
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
    @OneToMany(mappedBy = "fkPuesto")
    private List<Empleado> empleadoList;

    public Puesto() {
    }

    public Puesto(Long puestoId) {
        this.puestoId = puestoId;
    }

    public Puesto(Long puestoId, String nombre, boolean sisActivo) {
        this.puestoId = puestoId;
        this.nombre = nombre;
        this.sisActivo = sisActivo;
    }

    public Long getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(Long puestoId) {
        this.puestoId = puestoId;
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
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puestoId != null ? puestoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puesto)) {
            return false;
        }
        Puesto other = (Puesto) object;
        if ((this.puestoId == null && other.puestoId != null) || (this.puestoId != null && !this.puestoId.equals(other.puestoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Puesto[ puestoId=" + puestoId + " ]";
    }
    
}
