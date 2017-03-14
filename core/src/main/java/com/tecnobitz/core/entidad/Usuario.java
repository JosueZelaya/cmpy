/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.core.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tecnobitz.core.enums.SocialMediaService;
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
@Table(name = "usuario") 
@ToString(exclude = {"compraList","votosDadosList","votosRecibidosList","contactoList","contactoList1",
                    "suscripcionesList","seguidoresList","comentarioList","notificacionesList",
                    "publicacionList","publiacionSuscritaList","destinatarioList","mensajeList", "tiendaList"})
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
    @JsonIgnore
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
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<NotificacionUsuario> notificacionesList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Publicacion> publicacionList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<SuscripcionPublicacion> publiacionSuscritaList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuarioDestinatario")
    private List<Destinatario> destinatarioList;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Tienda> tiendaList;
    
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
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEmisor")
    private List<Mensaje> mensajeList;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaService signInProvider;
    
    private String rutaImagen;
    

    
}
