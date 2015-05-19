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
@Table(name = "recurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r"),
    @NamedQuery(name = "Recurso.findByRecursoId", query = "SELECT r FROM Recurso r WHERE r.recursoId = :recursoId"),
    @NamedQuery(name = "Recurso.findByNombre", query = "SELECT r FROM Recurso r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Recurso.findByDescripcion", query = "SELECT r FROM Recurso r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Recurso.findByRuta", query = "SELECT r FROM Recurso r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "Recurso.findBySisActivo", query = "SELECT r FROM Recurso r WHERE r.sisActivo = :sisActivo"),
    @NamedQuery(name = "Recurso.findBySisFechaCreacion", query = "SELECT r FROM Recurso r WHERE r.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Recurso.findBySisFechaModificacion", query = "SELECT r FROM Recurso r WHERE r.sisFechaModificacion = :sisFechaModificacion")})
public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recurso_id")
    private Long recursoId;
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
    @Size(min = 1, max = 2147483647)
    @Column(name = "ruta")
    private String ruta;
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
    @JoinColumn(name = "fk_grupo", referencedColumnName = "grupo_id")
    @ManyToOne
    private Grupo fkGrupo;
    @JoinColumn(name = "fk_perfil", referencedColumnName = "perfil_id")
    @ManyToOne
    private Perfil fkPerfil;
    @JoinColumn(name = "fk_tipo_recurso", referencedColumnName = "tipo_recuso_id")
    @ManyToOne
    private TipoRecurso fkTipoRecurso;
    @JoinColumn(name = "fk_publicacion", referencedColumnName = "publicacion_id")
    @ManyToOne
    private Publicacion publicacion;

    public Recurso() {
    }

    public Recurso(Long recursoId) {
        this.recursoId = recursoId;
    }

    public Recurso(Long recursoId, String nombre, String ruta, boolean sisActivo) {
        this.recursoId = recursoId;
        this.nombre = nombre;
        this.ruta = ruta;
        this.sisActivo = sisActivo;
    }

    public Long getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Long recursoId) {
        this.recursoId = recursoId;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    public Grupo getFkGrupo() {
        return fkGrupo;
    }

    public void setFkGrupo(Grupo fkGrupo) {
        this.fkGrupo = fkGrupo;
    }

    public Perfil getFkPerfil() {
        return fkPerfil;
    }

    public void setFkPerfil(Perfil fkPerfil) {
        this.fkPerfil = fkPerfil;
    }

    public TipoRecurso getFkTipoRecurso() {
        return fkTipoRecurso;
    }

    public void setFkTipoRecurso(TipoRecurso fkTipoRecurso) {
        this.fkTipoRecurso = fkTipoRecurso;
    }
    
    public Publicacion getPublicacion(){
        return this.publicacion;
    }
    
    public void setPublicacion(Publicacion publicacion){
        this.publicacion = publicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recursoId != null ? recursoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.recursoId == null && other.recursoId != null) || (this.recursoId != null && !this.recursoId.equals(other.recursoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Recurso[ recursoId=" + recursoId + " ]";
    }
    
}
