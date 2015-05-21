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
@Table(name = "ubicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubicacion.findAll", query = "SELECT u FROM Ubicacion u"),
    @NamedQuery(name = "Ubicacion.findByUbicacionId", query = "SELECT u FROM Ubicacion u WHERE u.ubicacionId = :ubicacionId"),
    @NamedQuery(name = "Ubicacion.findBySisActivo", query = "SELECT u FROM Ubicacion u WHERE u.sisActivo = :sisActivo"),
    @NamedQuery(name = "Ubicacion.findBySisFechaCreacion", query = "SELECT u FROM Ubicacion u WHERE u.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Ubicacion.findBySisFechaModificacion", query = "SELECT u FROM Ubicacion u WHERE u.sisFechaModificacion = :sisFechaModificacion"),
    @NamedQuery(name = "Ubicacion.findByPoligono", query = "SELECT u FROM Ubicacion u WHERE u.poligono = :poligono"),
    @NamedQuery(name = "Ubicacion.findByNumero", query = "SELECT u FROM Ubicacion u WHERE u.numero = :numero"),
    @NamedQuery(name = "Ubicacion.findByLocal", query = "SELECT u FROM Ubicacion u WHERE u.local = :local"),
    @NamedQuery(name = "Ubicacion.findByCalle", query = "SELECT u FROM Ubicacion u WHERE u.calle = :calle"),
    @NamedQuery(name = "Ubicacion.findByAvenida", query = "SELECT u FROM Ubicacion u WHERE u.avenida = :avenida"),
    @NamedQuery(name = "Ubicacion.findByGmLatitud", query = "SELECT u FROM Ubicacion u WHERE u.gmLatitud = :gmLatitud"),
    @NamedQuery(name = "Ubicacion.findByGmLongitud", query = "SELECT u FROM Ubicacion u WHERE u.gmLongitud = :gmLongitud")})
public class Ubicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ubicacion_id")
    private Long ubicacionId;
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
    @Size(max = 2147483647)
    @Column(name = "poligono")
    private String poligono;
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 2147483647)
    @Column(name = "local")
    private String local;
    @Size(max = 2147483647)
    @Column(name = "calle")
    private String calle;
    @Size(max = 2147483647)
    @Column(name = "avenida")
    private String avenida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gm_latitud")
    private Double gmLatitud;
    @Column(name = "gm_longitud")
    private Double gmLongitud;
    @JoinColumn(name = "fk_ciudad", referencedColumnName = "ciudad_id")
    @ManyToOne
    private Ciudad fkCiudad;
    @JoinColumn(name = "fk_publicacion", referencedColumnName = "publicacion_id")
    @ManyToOne
    private Publicacion fkPublicacion;
    @JoinColumn(name = "fk_tipo_ubicacion", referencedColumnName = "tipo_ubicacion_id")
    @ManyToOne
    private TipoUbicacion fkTipoUbicacion;
    @OneToMany(mappedBy = "fkUbicacion")
    private List<Persona> personaList;

    public Ubicacion() {
    }

    public Ubicacion(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Ubicacion(Long ubicacionId, boolean sisActivo) {
        this.ubicacionId = ubicacionId;
        this.sisActivo = sisActivo;
    }

    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
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

    public String getPoligono() {
        return poligono;
    }

    public void setPoligono(String poligono) {
        this.poligono = poligono;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public Double getGmLatitud() {
        return gmLatitud;
    }

    public void setGmLatitud(Double gmLatitud) {
        this.gmLatitud = gmLatitud;
    }

    public Double getGmLongitud() {
        return gmLongitud;
    }

    public void setGmLongitud(Double gmLongitud) {
        this.gmLongitud = gmLongitud;
    }

    public Ciudad getFkCiudad() {
        return fkCiudad;
    }

    public void setFkCiudad(Ciudad fkCiudad) {
        this.fkCiudad = fkCiudad;
    }

    public Publicacion getFkPublicacion() {
        return fkPublicacion;
    }

    public void setFkPublicacion(Publicacion fkPublicacion) {
        this.fkPublicacion = fkPublicacion;
    }

    public TipoUbicacion getFkTipoUbicacion() {
        return fkTipoUbicacion;
    }

    public void setFkTipoUbicacion(TipoUbicacion fkTipoUbicacion) {
        this.fkTipoUbicacion = fkTipoUbicacion;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubicacionId != null ? ubicacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.ubicacionId == null && other.ubicacionId != null) || (this.ubicacionId != null && !this.ubicacionId.equals(other.ubicacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Ubicacion[ ubicacionId=" + ubicacionId + " ]";
    }
    
}
