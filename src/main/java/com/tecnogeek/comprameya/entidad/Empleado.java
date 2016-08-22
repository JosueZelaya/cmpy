/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

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
import lombok.Data;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "empleado")
@Data
public class Empleado extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empleado_id")
    private Long id;
    @Column(name = "telefono")
    private BigInteger telefono;
    @Column(name = "nit")
    private BigInteger nit;
    @Column(name = "afp")
    private BigInteger afp;
    @Column(name = "isss")
    private BigInteger isss;
    @JoinColumn(name = "fk_empresa", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa fkEmpresa;
    @JoinColumn(name = "fk_persona", referencedColumnName = "persona_id")
    @ManyToOne
    private Persona fkPersona;
    @JoinColumn(name = "fk_puesto", referencedColumnName = "puesto_id")
    @ManyToOne
    private Puesto fkPuesto;
    
}
