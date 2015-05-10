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
@Table(name = "publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
    @NamedQuery(name = "Publicacion.findByPublicacionId", query = "SELECT p FROM Publicacion p WHERE p.publicacionId = :publicacionId"),
    @NamedQuery(name = "Publicacion.findByTitulo", query = "SELECT p FROM Publicacion p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Publicacion.findByDescripcion", query = "SELECT p FROM Publicacion p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Publicacion.findByConcluido", query = "SELECT p FROM Publicacion p WHERE p.concluido = :concluido"),
    @NamedQuery(name = "Publicacion.findByFechaVencimiento", query = "SELECT p FROM Publicacion p WHERE p.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Publicacion.findByVisto", query = "SELECT p FROM Publicacion p WHERE p.visto = :visto"),
    @NamedQuery(name = "Publicacion.findByPuntaje", query = "SELECT p FROM Publicacion p WHERE p.puntaje = :puntaje"),
    @NamedQuery(name = "Publicacion.findByDenuncias", query = "SELECT p FROM Publicacion p WHERE p.denuncias = :denuncias"),
    @NamedQuery(name = "Publicacion.findBySisActivo", query = "SELECT p FROM Publicacion p WHERE p.sisActivo = :sisActivo"),
    @NamedQuery(name = "Publicacion.findBySisFechaCreacion", query = "SELECT p FROM Publicacion p WHERE p.sisFechaCreacion = :sisFechaCreacion"),
    @NamedQuery(name = "Publicacion.findBySisFechaModificacion", query = "SELECT p FROM Publicacion p WHERE p.sisFechaModificacion = :sisFechaModificacion")})
public class Publicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "publicacion_id")
    private Long publicacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "concluido")
    private boolean concluido;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visto")
    private int visto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje")
    private int puntaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "denuncias")
    private int denuncias;
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
    @OneToMany(mappedBy = "fkPublicacion")
    private List<Ubicacion> ubicacionList;
    @OneToMany(mappedBy = "fkPublicacion")
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPublicacion")
    private List<Comentario> comentarioList;
    @JoinColumn(name = "fk_tipo_publicacion", referencedColumnName = "tipo_publicacion_id")
    @ManyToOne
    private TipoPublicacion fkTipoPublicacion;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuario;
    @JoinColumn(name = "fk_id_recurso", referencedColumnName = "recurso_id")
    @ManyToOne
    private Recurso fkRecurso;

    public Publicacion() {
    }

    public Publicacion(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Publicacion(Long publicacionId, String titulo, boolean concluido, int visto, int puntaje, int denuncias, boolean sisActivo) {
        this.publicacionId = publicacionId;
        this.titulo = titulo;
        this.concluido = concluido;
        this.visto = visto;
        this.puntaje = puntaje;
        this.denuncias = denuncias;
        this.sisActivo = sisActivo;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getVisto() {
        return visto;
    }

    public void setVisto(int visto) {
        this.visto = visto;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(int denuncias) {
        this.denuncias = denuncias;
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
    public List<Ubicacion> getUbicacionList() {
        return ubicacionList;
    }

    public void setUbicacionList(List<Ubicacion> ubicacionList) {
        this.ubicacionList = ubicacionList;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public TipoPublicacion getFkTipoPublicacion() {
        return fkTipoPublicacion;
    }

    public void setFkTipoPublicacion(TipoPublicacion fkTipoPublicacion) {
        this.fkTipoPublicacion = fkTipoPublicacion;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Recurso getFkRecurso(){
        return fkRecurso;
    }
    
    public void setFkRecurso(Recurso fkRecurso){
        this.fkRecurso = fkRecurso;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (publicacionId != null ? publicacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.publicacionId == null && other.publicacionId != null) || (this.publicacionId != null && !this.publicacionId.equals(other.publicacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnogeek.comprameya.entidades.Publicacion[ publicacionId=" + publicacionId + " ]";
    }
    
}
