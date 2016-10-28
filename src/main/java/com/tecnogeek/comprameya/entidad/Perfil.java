/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tecnogeek.comprameya.enums.Role;
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
import lombok.Data;

/**
 *
 * @author genaro
 */
@Entity
@Table(name = "perfil")
@Data
public class Perfil extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "perfil_id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre; //Enum    
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JsonBackReference
    @OneToMany(mappedBy = "fkPerfil")
    private List<Recurso> recursoList;
    @JoinColumn(name = "fk_grupo", referencedColumnName = "grupo_id")
    @ManyToOne
    private Grupo fkGrupo;
    @JsonBackReference
    @OneToMany(mappedBy = "fkPerfil")
    private List<Usuario> usuarioList;

    public Role getRole(){
        return Role.getRole(this.nombre);
    }
    
    public void setRole(Role role){
        this.nombre = role.getRoleName();
    }
    
}
