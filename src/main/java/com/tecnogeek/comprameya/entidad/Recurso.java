/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "recurso")
@Data
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
    @JoinColumn(name = "fk_publicacion", referencedColumnName = "publicacion_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Publicacion fkPublicacion;
    @JoinColumn(name = "fk_tipo_recurso", referencedColumnName = "tipo_recuso_id")
    @ManyToOne
    private TipoRecurso fkTipoRecurso;

}
