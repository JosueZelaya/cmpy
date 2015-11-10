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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "comentario")
@Data
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comentario_id")
    private Long comentarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "texto")
    private String texto;
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
    @JoinColumn(name = "fk_publicacion", referencedColumnName = "publicacion_id")
    @ManyToOne(optional = false)
    private Publicacion fkPublicacion;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario fkUsuario;

}
