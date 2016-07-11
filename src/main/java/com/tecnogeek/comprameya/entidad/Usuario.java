/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

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
    @Column(name = "usuario_id")
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
    @Size(max = 2147483647)
    @Column(name = "fb_usuario_id")
    private String fbUsuarioId;        
    @OneToMany(mappedBy = "fkUsuario")
    private List<Compra> compraList;
    @OneToMany(mappedBy = "fkUsuarioEvaluado")
    private List<Voto> votoList;
    @OneToMany(mappedBy = "fkUsuarioVotante")
    private List<Voto> votoList1;
    @OneToMany(mappedBy = "fkUsuarioContacto")
    private List<Contacto> contactoList;
    @OneToMany(mappedBy = "fkUsuarioDuenio")
    private List<Contacto> contactoList1;
    @OneToMany(mappedBy = "fkUsuarioProveedor")
    private List<Suscriptor> suscriptorList;
    @OneToMany(mappedBy = "fkUsuarioSuscriptor")
    private List<Suscriptor> suscriptorList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<Comentario> comentarioList;
    @OneToMany(mappedBy = "fkUsuario")
    private List<Notificacion> notificacionList;
    @JsonManagedReference
    @OneToMany(mappedBy = "fkUsuario")
    private List<Publicacion> publicacionList;
    @OneToMany(mappedBy = "fkUsuarioDestinatario")
    private List<Destinatario> destinatarioList;
    @JoinColumn(name = "fk_perfil", referencedColumnName = "perfil_id")
    @ManyToOne
    private Perfil fkPerfil;
    @JoinColumn(name = "fk_persona", referencedColumnName = "persona_id")
    @ManyToOne
    private Persona fkPersona;
    @JoinColumn(name = "fk_ranking", referencedColumnName = "ranking_id")
    @ManyToOne
    private Ranking fkRanking;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioEmisor")
    private List<Mensaje> mensajeList;
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaService signInProvider;
    
    private String rutaImagen;
    
}
