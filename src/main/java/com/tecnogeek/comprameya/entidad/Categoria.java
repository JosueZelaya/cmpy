/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "categoria")
@ToString(exclude = {"categoriaList","productoList"})
@Getter
@Setter
public class Categoria extends BaseEntity<Long> implements Serializable {
    
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
    
    @JsonBackReference
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productoList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "categoriaPadre")
    private List<Categoria> categoriaList; 
    
    @JsonManagedReference
    @JoinColumn(name = "categoria_padre_id", referencedColumnName = "id")
    @ManyToOne
    private Categoria categoriaPadre;
    
}
