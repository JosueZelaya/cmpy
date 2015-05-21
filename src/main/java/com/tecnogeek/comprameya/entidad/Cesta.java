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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "cesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cesta.findAll", query = "SELECT c FROM Cesta c"),
    @NamedQuery(name = "Cesta.findByCestaId", query = "SELECT c FROM Cesta c WHERE c.cestaId = :cestaId"),
    @NamedQuery(name = "Cesta.findBySisActivo", query = "SELECT c FROM Cesta c WHERE c.sisActivo = :sisActivo"),
    @NamedQuery(name = "Cesta.findBySisFechaCreacion", query = "SELECT c FROM Cesta c WHERE c.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Cesta.findBySisFechaModificacion", query = "SELECT c FROM Cesta c WHERE c.sisFechaModificacion = :sisFechaModificacion")})
public class Cesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cesta_id")
    private Long cestaId;
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
    @JoinColumn(name = "fk_compra", referencedColumnName = "compra_id")
    @ManyToOne
    private Compra fkCompra;
    @JoinColumn(name = "fk_producto", referencedColumnName = "producto_id")
    @ManyToOne
    private Producto fkProducto;

    public Cesta() {
    }

    public Cesta(Long cestaId) {
        this.cestaId = cestaId;
    }

    public Cesta(Long cestaId, boolean sisActivo) {
        this.cestaId = cestaId;
        this.sisActivo = sisActivo;
    }

    public Long getCestaId() {
        return cestaId;
    }

    public void setCestaId(Long cestaId) {
        this.cestaId = cestaId;
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

    public Compra getFkCompra() {
        return fkCompra;
    }

    public void setFkCompra(Compra fkCompra) {
        this.fkCompra = fkCompra;
    }

    public Producto getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(Producto fkProducto) {
        this.fkProducto = fkProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cestaId != null ? cestaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cesta)) {
            return false;
        }
        Cesta other = (Cesta) object;
        if ((this.cestaId == null && other.cestaId != null) || (this.cestaId != null && !this.cestaId.equals(other.cestaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Cesta[ cestaId=" + cestaId + " ]";
    }
    
}
