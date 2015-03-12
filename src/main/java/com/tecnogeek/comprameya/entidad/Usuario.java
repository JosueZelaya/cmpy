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
import javax.persistence.CascadeType;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findByPass", query = "SELECT u FROM Usuario u WHERE u.pass = :pass"),
    @NamedQuery(name = "Usuario.findByPuntaje", query = "SELECT u FROM Usuario u WHERE u.puntaje = :puntaje"),
    @NamedQuery(name = "Usuario.findByFbUsuarioId", query = "SELECT u FROM Usuario u WHERE u.fbUsuarioId = :fbUsuarioId"),
    @NamedQuery(name = "Usuario.findBySisSesionActiva", query = "SELECT u FROM Usuario u WHERE u.sisSesionActiva = :sisSesionActiva"),
    @NamedQuery(name = "Usuario.findBySisActivo", query = "SELECT u FROM Usuario u WHERE u.sisActivo = :sisActivo"),
    @NamedQuery(name = "Usuario.findBySisFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Usuario.findBySisFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.sisFechaModificacion = :sisFechaModificacion")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private Long usuarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje")
    private int puntaje;
    @Size(max = 2147483647)
    @Column(name = "fb_usuario_id")
    private String fbUsuarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sis_sesion_activa")
    private boolean sisSesionActiva;
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
    @OneToMany(mappedBy = "fkUsuarioVotante")
    private List<Voto> votoList;
    @OneToMany(mappedBy = "fkUsuarioEvaluado")
    private List<Voto> votoList1;
    @OneToMany(mappedBy = "fkUsuarioProveedor")
    private List<Suscriptor> suscriptorList;
    @OneToMany(mappedBy = "fkUsuarioSuscriptor")
    private List<Suscriptor> suscriptorList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<Comentario> comentarioList;
    @OneToMany(mappedBy = "fkUsuario")
    private List<Notificacion> notificacionList;
    @OneToMany(mappedBy = "fkUsuario")
    private List<Publicacion> publicacionList;
    @JoinColumn(name = "fk_perfil", referencedColumnName = "perfil_id")
    @ManyToOne
    private Perfil fkPerfil;
    @JoinColumn(name = "fk_persona", referencedColumnName = "persona_id")
    @ManyToOne
    private Persona fkPersona;
    @JoinColumn(name = "fk_ranking", referencedColumnName = "ranking_id")
    @ManyToOne
    private Ranking fkRanking;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioEmisor")
    private List<Mensaje> mensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioReceptor")
    private List<Mensaje> mensajeList1;

    public Usuario() {
    }

    public Usuario(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Long usuarioId, String login, String pass, int puntaje, boolean sisSesionActiva, boolean sisActivo) {
        this.usuarioId = usuarioId;
        this.login = login;
        this.pass = pass;
        this.puntaje = puntaje;
        this.sisSesionActiva = sisSesionActiva;
        this.sisActivo = sisActivo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getFbUsuarioId() {
        return fbUsuarioId;
    }

    public void setFbUsuarioId(String fbUsuarioId) {
        this.fbUsuarioId = fbUsuarioId;
    }

    public boolean getSisSesionActiva() {
        return sisSesionActiva;
    }

    public void setSisSesionActiva(boolean sisSesionActiva) {
        this.sisSesionActiva = sisSesionActiva;
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
    public List<Voto> getVotoList() {
        return votoList;
    }

    public void setVotoList(List<Voto> votoList) {
        this.votoList = votoList;
    }

    @XmlTransient
    public List<Voto> getVotoList1() {
        return votoList1;
    }

    public void setVotoList1(List<Voto> votoList1) {
        this.votoList1 = votoList1;
    }

    @XmlTransient
    public List<Suscriptor> getSuscriptorList() {
        return suscriptorList;
    }

    public void setSuscriptorList(List<Suscriptor> suscriptorList) {
        this.suscriptorList = suscriptorList;
    }

    @XmlTransient
    public List<Suscriptor> getSuscriptorList1() {
        return suscriptorList1;
    }

    public void setSuscriptorList1(List<Suscriptor> suscriptorList1) {
        this.suscriptorList1 = suscriptorList1;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @XmlTransient
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    @XmlTransient
    public List<Publicacion> getPublicacionList() {
        return publicacionList;
    }

    public void setPublicacionList(List<Publicacion> publicacionList) {
        this.publicacionList = publicacionList;
    }

    public Perfil getFkPerfil() {
        return fkPerfil;
    }

    public void setFkPerfil(Perfil fkPerfil) {
        this.fkPerfil = fkPerfil;
    }

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
    }

    public Ranking getFkRanking() {
        return fkRanking;
    }

    public void setFkRanking(Ranking fkRanking) {
        this.fkRanking = fkRanking;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList1() {
        return mensajeList1;
    }

    public void setMensajeList1(List<Mensaje> mensajeList1) {
        this.mensajeList1 = mensajeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Usuario[ usuarioId=" + usuarioId + " ]";
    }
    
}
