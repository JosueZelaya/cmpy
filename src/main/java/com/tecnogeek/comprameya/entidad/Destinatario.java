/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

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
import lombok.Data;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "destinatario")
@Data
public class Destinatario extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "destinatario_id")
    private Long Id;    
    @JoinColumn(name = "fk_mensaje", referencedColumnName = "mensaje_id")
    @ManyToOne
    private Mensaje fkMensaje;
    @JoinColumn(name = "fk_usuario_destinatario", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuario fkUsuarioDestinatario;
    
}
