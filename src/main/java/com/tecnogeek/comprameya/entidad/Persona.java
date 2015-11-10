/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "persona")
@Data
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "persona_id")
    private Long personaId;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "genero")
    private Boolean genero;
    @Column(name = "telefono")
    private BigInteger telefono;
    @Column(name = "celular")
    private BigInteger celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Column(name = "dui")
    private BigInteger dui;
    @Size(max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 2147483647)
    @Column(name = "fb_nombre")
    private String fbNombre;
    @Size(max = 2147483647)
    @Column(name = "fb_apellido")
    private String fbApellido;
    @Column(name = "fb_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fbFechaNacimiento;
    @Column(name = "fb_genero")
    private Boolean fbGenero;
    @Size(max = 2147483647)
    @Column(name = "fb_correo")
    private String fbCorreo;
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
    @OneToMany(mappedBy = "fkPersona")
    private List<Empleado> empleadoList;
    @JoinColumn(name = "fk_ubicacion", referencedColumnName = "ubicacion_id")
    @ManyToOne
    private Ubicacion fkUbicacion;
    @OneToMany(mappedBy = "fkPersona")
    private List<Usuario> usuarioList;

}
