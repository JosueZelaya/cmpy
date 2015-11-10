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
@Table(name = "notificacion")
@Data
public class Notificacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notificacion_id")
    private Long notificacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tabla")
    private String tabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tabla_id")
    private int tablaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visto")
    private boolean visto;
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
    @JoinColumn(name = "fk_tipo_notificacion", referencedColumnName = "tipo_notificacion_id")
    @ManyToOne
    private TipoNotificacion fkTipoNotificacion;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuario;
    
}
