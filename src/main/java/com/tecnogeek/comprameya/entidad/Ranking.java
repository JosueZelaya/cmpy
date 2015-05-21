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
@Table(name = "ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ranking.findAll", query = "SELECT r FROM Ranking r"),
    @NamedQuery(name = "Ranking.findByRankingId", query = "SELECT r FROM Ranking r WHERE r.rankingId = :rankingId"),
    @NamedQuery(name = "Ranking.findByNombre", query = "SELECT r FROM Ranking r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Ranking.findByMinimo", query = "SELECT r FROM Ranking r WHERE r.minimo = :minimo"),
    @NamedQuery(name = "Ranking.findByMaximo", query = "SELECT r FROM Ranking r WHERE r.maximo = :maximo"),
    @NamedQuery(name = "Ranking.findBySisActivo", query = "SELECT r FROM Ranking r WHERE r.sisActivo = :sisActivo"),
    @NamedQuery(name = "Ranking.findBySisFechaCreacion", query = "SELECT r FROM Ranking r WHERE r.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Ranking.findBySisFechaModificacion", query = "SELECT r FROM Ranking r WHERE r.sisFechaModificacion = :sisFechaModificacion")})
public class Ranking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ranking_id")
    private Long rankingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minimo")
    private int minimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maximo")
    private int maximo;
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
    @OneToMany(mappedBy = "fkRanking")
    private List<Usuario> usuarioList;

    public Ranking() {
    }

    public Ranking(Long rankingId) {
        this.rankingId = rankingId;
    }

    public Ranking(Long rankingId, String nombre, int minimo, int maximo, boolean sisActivo) {
        this.rankingId = rankingId;
        this.nombre = nombre;
        this.minimo = minimo;
        this.maximo = maximo;
        this.sisActivo = sisActivo;
    }

    public Long getRankingId() {
        return rankingId;
    }

    public void setRankingId(Long rankingId) {
        this.rankingId = rankingId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rankingId != null ? rankingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ranking)) {
            return false;
        }
        Ranking other = (Ranking) object;
        if ((this.rankingId == null && other.rankingId != null) || (this.rankingId != null && !this.rankingId.equals(other.rankingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Ranking[ rankingId=" + rankingId + " ]";
    }
    
}
