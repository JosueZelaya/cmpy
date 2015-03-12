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
@Table(name = "tipo_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmpresa.findAll", query = "SELECT t FROM TipoEmpresa t"),
    @NamedQuery(name = "TipoEmpresa.findByTipoEmpresaId", query = "SELECT t FROM TipoEmpresa t WHERE t.tipoEmpresaId = :tipoEmpresaId"),
    @NamedQuery(name = "TipoEmpresa.findByNombre", query = "SELECT t FROM TipoEmpresa t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoEmpresa.findByDescripcion", query = "SELECT t FROM TipoEmpresa t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoEmpresa.findBySisActivo", query = "SELECT t FROM TipoEmpresa t WHERE t.sisActivo = :sisActivo"),
    @NamedQuery(name = "TipoEmpresa.findBySisFechaCreacion", query = "SELECT t FROM TipoEmpresa t WHERE t.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "TipoEmpresa.findBySisFechaModificacion", query = "SELECT t FROM TipoEmpresa t WHERE t.sisFechaModificacion = :sisFechaModificacion")})
public class TipoEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_empresa_id")
    private Long tipoEmpresaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
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
    @OneToMany(mappedBy = "fkTipoEmpresa")
    private List<Empresa> empresaList;

    public TipoEmpresa() {
    }

    public TipoEmpresa(Long tipoEmpresaId) {
        this.tipoEmpresaId = tipoEmpresaId;
    }

    public TipoEmpresa(Long tipoEmpresaId, String nombre, String descripcion, boolean sisActivo) {
        this.tipoEmpresaId = tipoEmpresaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sisActivo = sisActivo;
    }

    public Long getTipoEmpresaId() {
        return tipoEmpresaId;
    }

    public void setTipoEmpresaId(Long tipoEmpresaId) {
        this.tipoEmpresaId = tipoEmpresaId;
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
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoEmpresaId != null ? tipoEmpresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpresa)) {
            return false;
        }
        TipoEmpresa other = (TipoEmpresa) object;
        if ((this.tipoEmpresaId == null && other.tipoEmpresaId != null) || (this.tipoEmpresaId != null && !this.tipoEmpresaId.equals(other.tipoEmpresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.TipoEmpresa[ tipoEmpresaId=" + tipoEmpresaId + " ]";
    }
    
}
