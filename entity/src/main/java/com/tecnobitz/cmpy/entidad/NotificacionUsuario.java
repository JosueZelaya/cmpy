/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.entidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexander
 */

@Entity
@Table(name = "notificacion_usuario")
@Getter
@Setter
public class NotificacionUsuario extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Override
    public Long getId() {
        return this.id;
    }
    
    @JsonManagedReference
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuario;
    
    @JsonManagedReference
    @JoinColumn(name = "notificacion_id", referencedColumnName = "id")
    @ManyToOne
    private Notificacion notificacion;
    
    @Column(name = "visto")
    private boolean visto;
    
    @Transient
    private Usuario emisor;
    
}
