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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author genaro
 */

@Entity
@Table(name = "caracteristica")
@Getter
@Setter
public class Caracteristica extends BaseEntity<Long> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @JsonManagedReference
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    @ManyToOne
    private Producto producto;
    @JsonManagedReference
    @JoinColumn(name = "tipo_caracteristica_id", referencedColumnName = "id")
    @ManyToOne
    private TipoCaracteristica tipoCaracteristica;
    
}
