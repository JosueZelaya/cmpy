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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author genaro
 */

@Entity
@Table(name = "caracteristica")
@Data
public class Caracteristica extends BaseEntity<Long> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caracteristica_id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "fk_producto", referencedColumnName = "producto_id")
    @ManyToOne
    private Producto fkProducto;
    @JoinColumn(name = "fk_tipo_caracteristica", referencedColumnName = "tipo_caracteristica_id")
    @ManyToOne
    private TipoCaracteristica fkTipoCaracteristica;
    
}
