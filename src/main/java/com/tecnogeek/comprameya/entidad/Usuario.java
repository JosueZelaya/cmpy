/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tecnogeek.comprameya.enums.SocialMediaService;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "usuario") 
@Getter
@Setter
public class Usuario extends BaseEntity<Long> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "login")
    private String login;
    @Size(min = 1, max = 2147483647)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje")
    private int puntaje;  
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Compra> compraList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioEvaluado")
    private List<Voto> votosDadosList;
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioVotante")
    private List<Voto> votosRecibidosList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioContacto")
    private List<Contacto> contactoList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioDuenio")
    private List<Contacto> contactoList1;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioSuscriptor")
    private List<SuscripcionUsuario> suscripcionesList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioProveedor")
    private List<SuscripcionUsuario> seguidoresList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Comentario> comentarioList;
    
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name="usuario_notificacion",
      joinColumns=@JoinColumn(name="usuario_id", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="notificacion_id", referencedColumnName="ID")
    )
    private List<Notificacion> notificacionesList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Publicacion> publicacionList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<SuscripcionPublicacion> publiacionSuscritaList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioDestinatario")
    private List<Destinatario> destinatarioList;
    
    @JsonManagedReference
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    @ManyToOne
    private Perfil perfil;
    @JsonManagedReference
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne
    private Persona persona;
    @JoinColumn(name = "ranking_id", referencedColumnName = "id")
    @ManyToOne
    private Ranking ranking;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEmisor")
    private List<Mensaje> mensajeList;
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaService signInProvider;
    
    private String rutaImagen;
    
}
