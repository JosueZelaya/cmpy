/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "notificacion")
@Getter
@Setter
@ToString(exclude={"usuariosNotificadosList"})
public class Notificacion extends BaseEntity<Long> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "mensaje")
    private String mensaje;
    
    @Column(name = "link")
    private String link;
    
    @JoinColumn(name = "tipo_notificacion_id", referencedColumnName = "id")
    @ManyToOne
    private TipoNotificacion tipoNotificacion;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notificacion")
    private List<NotificacionUsuario> usuariosNotificadosList;
    
}
