/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.core.entidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "empleado")
@Getter
@Setter
public class Empleado extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "telefono")
    private BigInteger telefono;
    @Column(name = "nit")
    private BigInteger nit;
    @Column(name = "afp")
    private BigInteger afp;
    @Column(name = "isss")
    private BigInteger isss;
    @JsonManagedReference
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    @ManyToOne
    private Empresa empresa;
    @JsonManagedReference
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne
    private Persona persona;
    @JsonManagedReference
    @JoinColumn(name = "puesto_id", referencedColumnName = "id")
    @ManyToOne
    private Puesto puesto;
    
}
