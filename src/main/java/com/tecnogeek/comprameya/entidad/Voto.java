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
@Table(name = "voto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voto.findAll", query = "SELECT v FROM Voto v"),
    @NamedQuery(name = "Voto.findByVotoId", query = "SELECT v FROM Voto v WHERE v.votoId = :votoId"),
    @NamedQuery(name = "Voto.findByValor", query = "SELECT v FROM Voto v WHERE v.valor = :valor"),
    @NamedQuery(name = "Voto.findBySisActivo", query = "SELECT v FROM Voto v WHERE v.sisActivo = :sisActivo"),
    @NamedQuery(name = "Voto.findBySisFechaCreacion", query = "SELECT v FROM Voto v WHERE v.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Voto.findBySisFechaModificacion", query = "SELECT v FROM Voto v WHERE v.sisFechaModificacion = :sisFechaModificacion"),
    @NamedQuery(name = "Voto.findByComentario", query = "SELECT v FROM Voto v WHERE v.comentario = :comentario")})
public class Voto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "voto_id")
    private Long votoId;
    @Column(name = "valor")
    private Integer valor;
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
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "fk_usuario_evaluado", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioEvaluado;
    @JoinColumn(name = "fk_usuario_votante", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioVotante;

    public Voto() {
    }

    public Voto(Long votoId) {
        this.votoId = votoId;
    }

    public Voto(Long votoId, boolean sisActivo) {
        this.votoId = votoId;
        this.sisActivo = sisActivo;
    }

    public Long getVotoId() {
        return votoId;
    }

    public void setVotoId(Long votoId) {
        this.votoId = votoId;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getFkUsuarioEvaluado() {
        return fkUsuarioEvaluado;
    }

    public void setFkUsuarioEvaluado(Usuario fkUsuarioEvaluado) {
        this.fkUsuarioEvaluado = fkUsuarioEvaluado;
    }

    public Usuario getFkUsuarioVotante() {
        return fkUsuarioVotante;
    }

    public void setFkUsuarioVotante(Usuario fkUsuarioVotante) {
        this.fkUsuarioVotante = fkUsuarioVotante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (votoId != null ? votoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voto)) {
            return false;
        }
        Voto other = (Voto) object;
        if ((this.votoId == null && other.votoId != null) || (this.votoId != null && !this.votoId.equals(other.votoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidad.Voto[ votoId=" + votoId + " ]";
    }
    
}
