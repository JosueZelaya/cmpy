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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "destinatario")
@Getter
@Setter
public class Destinatario extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;    
    @JoinColumn(name = "mensaje_id", referencedColumnName = "id")
    @ManyToOne
    private Mensaje mensaje;
    @JoinColumn(name = "usuario_destinatario_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioDestinatario;
    
}
