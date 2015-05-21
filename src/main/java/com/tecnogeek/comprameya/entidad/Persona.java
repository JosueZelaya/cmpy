/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByPersonaId", query = "SELECT p FROM Persona p WHERE p.personaId = :personaId"),
    @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero"),
    @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Persona.findByCelular", query = "SELECT p FROM Persona p WHERE p.celular = :celular"),
    @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo"),
    @NamedQuery(name = "Persona.findByDui", query = "SELECT p FROM Persona p WHERE p.dui = :dui"),
    @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Persona.findByFbNombre", query = "SELECT p FROM Persona p WHERE p.fbNombre = :fbNombre"),
    @NamedQuery(name = "Persona.findByFbApellido", query = "SELECT p FROM Persona p WHERE p.fbApellido = :fbApellido"),
    @NamedQuery(name = "Persona.findByFbFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fbFechaNacimiento = :fbFechaNacimiento"),
    @NamedQuery(name = "Persona.findByFbGenero", query = "SELECT p FROM Persona p WHERE p.fbGenero = :fbGenero"),
    @NamedQuery(name = "Persona.findByFbCorreo", query = "SELECT p FROM Persona p WHERE p.fbCorreo = :fbCorreo"),
    @NamedQuery(name = "Persona.findBySisActivo", query = "SELECT p FROM Persona p WHERE p.sisActivo = :sisActivo"),
    @NamedQuery(name = "Persona.findBySisFechaCreacion", query = "SELECT p FROM Persona p WHERE p.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Persona.findBySisFechaModificacion", query = "SELECT p FROM Persona p WHERE p.sisFechaModificacion = :sisFechaModificacion")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "persona_id")
    private Long personaId;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "genero")
    private Boolean genero;
    @Column(name = "telefono")
    private BigInteger telefono;
    @Column(name = "celular")
    private BigInteger celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Column(name = "dui")
    private BigInteger dui;
    @Size(max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 2147483647)
    @Column(name = "fb_nombre")
    private String fbNombre;
    @Size(max = 2147483647)
    @Column(name = "fb_apellido")
    private String fbApellido;
    @Column(name = "fb_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fbFechaNacimiento;
    @Column(name = "fb_genero")
    private Boolean fbGenero;
    @Size(max = 2147483647)
    @Column(name = "fb_correo")
    private String fbCorreo;
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
    @OneToMany(mappedBy = "fkPersona")
    private List<Empleado> empleadoList;
    @JoinColumn(name = "fk_ubicacion", referencedColumnName = "ubicacion_id")
    @ManyToOne
    private Ubicacion fkUbicacion;
    @OneToMany(mappedBy = "fkPersona")
    private List<Usuario> usuarioList;

    public Persona() {
    }

    public Persona(Long personaId) {
        this.personaId = personaId;
    }

    public Persona(Long personaId, String correo, boolean sisActivo) {
        this.personaId = personaId;
        this.correo = correo;
        this.sisActivo = sisActivo;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    public BigInteger getCelular() {
        return celular;
    }

    public void setCelular(BigInteger celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigInteger getDui() {
        return dui;
    }

    public void setDui(BigInteger dui) {
        this.dui = dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFbNombre() {
        return fbNombre;
    }

    public void setFbNombre(String fbNombre) {
        this.fbNombre = fbNombre;
    }

    public String getFbApellido() {
        return fbApellido;
    }

    public void setFbApellido(String fbApellido) {
        this.fbApellido = fbApellido;
    }

    public Date getFbFechaNacimiento() {
        return fbFechaNacimiento;
    }

    public void setFbFechaNacimiento(Date fbFechaNacimiento) {
        this.fbFechaNacimiento = fbFechaNacimiento;
    }

    public Boolean getFbGenero() {
        return fbGenero;
    }

    public void setFbGenero(Boolean fbGenero) {
        this.fbGenero = fbGenero;
    }

    public String getFbCorreo() {
        return fbCorreo;
    }

    public void setFbCorreo(String fbCorreo) {
        this.fbCorreo = fbCorreo;
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

    public Ubicacion getFkUbicacion() {
        return fkUbicacion;
    }

    public void setFkUbicacion(Ubicacion fkUbicacion) {
        this.fkUbicacion = fkUbicacion;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Persona[ personaId=" + personaId + " ]";
    }
    
}
