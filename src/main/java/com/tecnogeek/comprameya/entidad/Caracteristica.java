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
@Table(name = "caracteristica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caracteristica.findAll", query = "SELECT c FROM Caracteristica c"),
    @NamedQuery(name = "Caracteristica.findByCaracteristicaId", query = "SELECT c FROM Caracteristica c WHERE c.caracteristicaId = :caracteristicaId"),
    @NamedQuery(name = "Caracteristica.findByValor", query = "SELECT c FROM Caracteristica c WHERE c.valor = :valor"),
    @NamedQuery(name = "Caracteristica.findBySisActivo", query = "SELECT c FROM Caracteristica c WHERE c.sisActivo = :sisActivo"),
    @NamedQuery(name = "Caracteristica.findBySisFechaCreacion", query = "SELECT c FROM Caracteristica c WHERE c.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Caracteristica.findBySisFechaModificacion", query = "SELECT c FROM Caracteristica c WHERE c.sisFechaModificacion = :sisFechaModificacion")})
public class Caracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caracteristica_id")
    private Long caracteristicaId;
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
    @Temporal(TemporalType.DATE)
    private Date sisFechaCreacion;
    @Column(name = "sis_fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date sisFechaModificacion;
    @JoinColumn(name = "fk_producto", referencedColumnName = "producto_id")
    @ManyToOne
    private Producto fkProducto;
    @JoinColumn(name = "fk_tipo_caracteristica", referencedColumnName = "tipo_caracteristica_id")
    @ManyToOne
    private TipoCaracteristica fkTipoCaracteristica;

    public Caracteristica() {
    }

    public Caracteristica(Long caracteristicaId) {
        this.caracteristicaId = caracteristicaId;
    }

    public Caracteristica(Long caracteristicaId, String valor, boolean sisActivo) {
        this.caracteristicaId = caracteristicaId;
        this.valor = valor;
        this.sisActivo = sisActivo;
    }

    public Long getCaracteristicaId() {
        return caracteristicaId;
    }

    public void setCaracteristicaId(Long caracteristicaId) {
        this.caracteristicaId = caracteristicaId;
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

    public Producto getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(Producto fkProducto) {
        this.fkProducto = fkProducto;
    }

    public TipoCaracteristica getFkTipoCaracteristica() {
        return fkTipoCaracteristica;
    }

    public void setFkTipoCaracteristica(TipoCaracteristica fkTipoCaracteristica) {
        this.fkTipoCaracteristica = fkTipoCaracteristica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caracteristicaId != null ? caracteristicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristica)) {
            return false;
        }
        Caracteristica other = (Caracteristica) object;
        if ((this.caracteristicaId == null && other.caracteristicaId != null) || (this.caracteristicaId != null && !this.caracteristicaId.equals(other.caracteristicaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Caracteristica[ caracteristicaId=" + caracteristicaId + " ]";
    }
    
}
