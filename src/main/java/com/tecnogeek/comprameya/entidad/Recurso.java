/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "recurso")
@Data
public class Recurso extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ruta")
    private String ruta;
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    @ManyToOne
    private Grupo grupo;
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    @ManyToOne
    private Perfil perfil;
    @JsonManagedReference
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Publicacion publicacion;
    @JoinColumn(name = "tipo_recurso_id", referencedColumnName = "id")
    @ManyToOne
    private TipoRecurso tipoRecurso;

}
