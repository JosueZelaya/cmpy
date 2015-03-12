/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.IllegalOrphanException;
import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Persona;
import com.tecnogeek.comprameya.entidad.Ranking;
import com.tecnogeek.comprameya.entidad.Voto;
import java.util.ArrayList;
import java.util.List;
import com.tecnogeek.comprameya.entidad.Suscriptor;
import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws RollbackFailureException, Exception {
        if (usuario.getVotoList() == null) {
            usuario.setVotoList(new ArrayList<Voto>());
        }
        if (usuario.getVotoList1() == null) {
            usuario.setVotoList1(new ArrayList<Voto>());
        }
        if (usuario.getSuscriptorList() == null) {
            usuario.setSuscriptorList(new ArrayList<Suscriptor>());
        }
        if (usuario.getSuscriptorList1() == null) {
            usuario.setSuscriptorList1(new ArrayList<Suscriptor>());
        }
        if (usuario.getComentarioList() == null) {
            usuario.setComentarioList(new ArrayList<Comentario>());
        }
        if (usuario.getNotificacionList() == null) {
            usuario.setNotificacionList(new ArrayList<Notificacion>());
        }
        if (usuario.getPublicacionList() == null) {
            usuario.setPublicacionList(new ArrayList<Publicacion>());
        }
        if (usuario.getMensajeList() == null) {
            usuario.setMensajeList(new ArrayList<Mensaje>());
        }
        if (usuario.getMensajeList1() == null) {
            usuario.setMensajeList1(new ArrayList<Mensaje>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Perfil fkPerfil = usuario.getFkPerfil();
            if (fkPerfil != null) {
                fkPerfil = em.getReference(fkPerfil.getClass(), fkPerfil.getPerfilId());
                usuario.setFkPerfil(fkPerfil);
            }
            Persona fkPersona = usuario.getFkPersona();
            if (fkPersona != null) {
                fkPersona = em.getReference(fkPersona.getClass(), fkPersona.getPersonaId());
                usuario.setFkPersona(fkPersona);
            }
            Ranking fkRanking = usuario.getFkRanking();
            if (fkRanking != null) {
                fkRanking = em.getReference(fkRanking.getClass(), fkRanking.getRankingId());
                usuario.setFkRanking(fkRanking);
            }
            List<Voto> attachedVotoList = new ArrayList<Voto>();
            for (Voto votoListVotoToAttach : usuario.getVotoList()) {
                votoListVotoToAttach = em.getReference(votoListVotoToAttach.getClass(), votoListVotoToAttach.getVotoId());
                attachedVotoList.add(votoListVotoToAttach);
            }
            usuario.setVotoList(attachedVotoList);
            List<Voto> attachedVotoList1 = new ArrayList<Voto>();
            for (Voto votoList1VotoToAttach : usuario.getVotoList1()) {
                votoList1VotoToAttach = em.getReference(votoList1VotoToAttach.getClass(), votoList1VotoToAttach.getVotoId());
                attachedVotoList1.add(votoList1VotoToAttach);
            }
            usuario.setVotoList1(attachedVotoList1);
            List<Suscriptor> attachedSuscriptorList = new ArrayList<Suscriptor>();
            for (Suscriptor suscriptorListSuscriptorToAttach : usuario.getSuscriptorList()) {
                suscriptorListSuscriptorToAttach = em.getReference(suscriptorListSuscriptorToAttach.getClass(), suscriptorListSuscriptorToAttach.getSuscriptorId());
                attachedSuscriptorList.add(suscriptorListSuscriptorToAttach);
            }
            usuario.setSuscriptorList(attachedSuscriptorList);
            List<Suscriptor> attachedSuscriptorList1 = new ArrayList<Suscriptor>();
            for (Suscriptor suscriptorList1SuscriptorToAttach : usuario.getSuscriptorList1()) {
                suscriptorList1SuscriptorToAttach = em.getReference(suscriptorList1SuscriptorToAttach.getClass(), suscriptorList1SuscriptorToAttach.getSuscriptorId());
                attachedSuscriptorList1.add(suscriptorList1SuscriptorToAttach);
            }
            usuario.setSuscriptorList1(attachedSuscriptorList1);
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : usuario.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getComentarioId());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            usuario.setComentarioList(attachedComentarioList);
            List<Notificacion> attachedNotificacionList = new ArrayList<Notificacion>();
            for (Notificacion notificacionListNotificacionToAttach : usuario.getNotificacionList()) {
                notificacionListNotificacionToAttach = em.getReference(notificacionListNotificacionToAttach.getClass(), notificacionListNotificacionToAttach.getNotificacionId());
                attachedNotificacionList.add(notificacionListNotificacionToAttach);
            }
            usuario.setNotificacionList(attachedNotificacionList);
            List<Publicacion> attachedPublicacionList = new ArrayList<Publicacion>();
            for (Publicacion publicacionListPublicacionToAttach : usuario.getPublicacionList()) {
                publicacionListPublicacionToAttach = em.getReference(publicacionListPublicacionToAttach.getClass(), publicacionListPublicacionToAttach.getPublicacionId());
                attachedPublicacionList.add(publicacionListPublicacionToAttach);
            }
            usuario.setPublicacionList(attachedPublicacionList);
            List<Mensaje> attachedMensajeList = new ArrayList<Mensaje>();
            for (Mensaje mensajeListMensajeToAttach : usuario.getMensajeList()) {
                mensajeListMensajeToAttach = em.getReference(mensajeListMensajeToAttach.getClass(), mensajeListMensajeToAttach.getMensajeId());
                attachedMensajeList.add(mensajeListMensajeToAttach);
            }
            usuario.setMensajeList(attachedMensajeList);
            List<Mensaje> attachedMensajeList1 = new ArrayList<Mensaje>();
            for (Mensaje mensajeList1MensajeToAttach : usuario.getMensajeList1()) {
                mensajeList1MensajeToAttach = em.getReference(mensajeList1MensajeToAttach.getClass(), mensajeList1MensajeToAttach.getMensajeId());
                attachedMensajeList1.add(mensajeList1MensajeToAttach);
            }
            usuario.setMensajeList1(attachedMensajeList1);
            em.persist(usuario);
            if (fkPerfil != null) {
                fkPerfil.getUsuarioList().add(usuario);
                fkPerfil = em.merge(fkPerfil);
            }
            if (fkPersona != null) {
                fkPersona.getUsuarioList().add(usuario);
                fkPersona = em.merge(fkPersona);
            }
            if (fkRanking != null) {
                fkRanking.getUsuarioList().add(usuario);
                fkRanking = em.merge(fkRanking);
            }
            for (Voto votoListVoto : usuario.getVotoList()) {
                Usuario oldFkUsuarioVotanteOfVotoListVoto = votoListVoto.getFkUsuarioVotante();
                votoListVoto.setFkUsuarioVotante(usuario);
                votoListVoto = em.merge(votoListVoto);
                if (oldFkUsuarioVotanteOfVotoListVoto != null) {
                    oldFkUsuarioVotanteOfVotoListVoto.getVotoList().remove(votoListVoto);
                    oldFkUsuarioVotanteOfVotoListVoto = em.merge(oldFkUsuarioVotanteOfVotoListVoto);
                }
            }
            for (Voto votoList1Voto : usuario.getVotoList1()) {
                Usuario oldFkUsuarioEvaluadoOfVotoList1Voto = votoList1Voto.getFkUsuarioEvaluado();
                votoList1Voto.setFkUsuarioEvaluado(usuario);
                votoList1Voto = em.merge(votoList1Voto);
                if (oldFkUsuarioEvaluadoOfVotoList1Voto != null) {
                    oldFkUsuarioEvaluadoOfVotoList1Voto.getVotoList1().remove(votoList1Voto);
                    oldFkUsuarioEvaluadoOfVotoList1Voto = em.merge(oldFkUsuarioEvaluadoOfVotoList1Voto);
                }
            }
            for (Suscriptor suscriptorListSuscriptor : usuario.getSuscriptorList()) {
                Usuario oldFkUsuarioProveedorOfSuscriptorListSuscriptor = suscriptorListSuscriptor.getFkUsuarioProveedor();
                suscriptorListSuscriptor.setFkUsuarioProveedor(usuario);
                suscriptorListSuscriptor = em.merge(suscriptorListSuscriptor);
                if (oldFkUsuarioProveedorOfSuscriptorListSuscriptor != null) {
                    oldFkUsuarioProveedorOfSuscriptorListSuscriptor.getSuscriptorList().remove(suscriptorListSuscriptor);
                    oldFkUsuarioProveedorOfSuscriptorListSuscriptor = em.merge(oldFkUsuarioProveedorOfSuscriptorListSuscriptor);
                }
            }
            for (Suscriptor suscriptorList1Suscriptor : usuario.getSuscriptorList1()) {
                Usuario oldFkUsuarioSuscriptorOfSuscriptorList1Suscriptor = suscriptorList1Suscriptor.getFkUsuarioSuscriptor();
                suscriptorList1Suscriptor.setFkUsuarioSuscriptor(usuario);
                suscriptorList1Suscriptor = em.merge(suscriptorList1Suscriptor);
                if (oldFkUsuarioSuscriptorOfSuscriptorList1Suscriptor != null) {
                    oldFkUsuarioSuscriptorOfSuscriptorList1Suscriptor.getSuscriptorList1().remove(suscriptorList1Suscriptor);
                    oldFkUsuarioSuscriptorOfSuscriptorList1Suscriptor = em.merge(oldFkUsuarioSuscriptorOfSuscriptorList1Suscriptor);
                }
            }
            for (Comentario comentarioListComentario : usuario.getComentarioList()) {
                Usuario oldFkUsuarioOfComentarioListComentario = comentarioListComentario.getFkUsuario();
                comentarioListComentario.setFkUsuario(usuario);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldFkUsuarioOfComentarioListComentario != null) {
                    oldFkUsuarioOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldFkUsuarioOfComentarioListComentario = em.merge(oldFkUsuarioOfComentarioListComentario);
                }
            }
            for (Notificacion notificacionListNotificacion : usuario.getNotificacionList()) {
                Usuario oldFkUsuarioOfNotificacionListNotificacion = notificacionListNotificacion.getFkUsuario();
                notificacionListNotificacion.setFkUsuario(usuario);
                notificacionListNotificacion = em.merge(notificacionListNotificacion);
                if (oldFkUsuarioOfNotificacionListNotificacion != null) {
                    oldFkUsuarioOfNotificacionListNotificacion.getNotificacionList().remove(notificacionListNotificacion);
                    oldFkUsuarioOfNotificacionListNotificacion = em.merge(oldFkUsuarioOfNotificacionListNotificacion);
                }
            }
            for (Publicacion publicacionListPublicacion : usuario.getPublicacionList()) {
                Usuario oldFkUsuarioOfPublicacionListPublicacion = publicacionListPublicacion.getFkUsuario();
                publicacionListPublicacion.setFkUsuario(usuario);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
                if (oldFkUsuarioOfPublicacionListPublicacion != null) {
                    oldFkUsuarioOfPublicacionListPublicacion.getPublicacionList().remove(publicacionListPublicacion);
                    oldFkUsuarioOfPublicacionListPublicacion = em.merge(oldFkUsuarioOfPublicacionListPublicacion);
                }
            }
            for (Mensaje mensajeListMensaje : usuario.getMensajeList()) {
                Usuario oldFkUsuarioEmisorOfMensajeListMensaje = mensajeListMensaje.getFkUsuarioEmisor();
                mensajeListMensaje.setFkUsuarioEmisor(usuario);
                mensajeListMensaje = em.merge(mensajeListMensaje);
                if (oldFkUsuarioEmisorOfMensajeListMensaje != null) {
                    oldFkUsuarioEmisorOfMensajeListMensaje.getMensajeList().remove(mensajeListMensaje);
                    oldFkUsuarioEmisorOfMensajeListMensaje = em.merge(oldFkUsuarioEmisorOfMensajeListMensaje);
                }
            }
            for (Mensaje mensajeList1Mensaje : usuario.getMensajeList1()) {
                Usuario oldFkUsuarioReceptorOfMensajeList1Mensaje = mensajeList1Mensaje.getFkUsuarioReceptor();
                mensajeList1Mensaje.setFkUsuarioReceptor(usuario);
                mensajeList1Mensaje = em.merge(mensajeList1Mensaje);
                if (oldFkUsuarioReceptorOfMensajeList1Mensaje != null) {
                    oldFkUsuarioReceptorOfMensajeList1Mensaje.getMensajeList1().remove(mensajeList1Mensaje);
                    oldFkUsuarioReceptorOfMensajeList1Mensaje = em.merge(oldFkUsuarioReceptorOfMensajeList1Mensaje);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuarioId());
            Perfil fkPerfilOld = persistentUsuario.getFkPerfil();
            Perfil fkPerfilNew = usuario.getFkPerfil();
            Persona fkPersonaOld = persistentUsuario.getFkPersona();
            Persona fkPersonaNew = usuario.getFkPersona();
            Ranking fkRankingOld = persistentUsuario.getFkRanking();
            Ranking fkRankingNew = usuario.getFkRanking();
            List<Voto> votoListOld = persistentUsuario.getVotoList();
            List<Voto> votoListNew = usuario.getVotoList();
            List<Voto> votoList1Old = persistentUsuario.getVotoList1();
            List<Voto> votoList1New = usuario.getVotoList1();
            List<Suscriptor> suscriptorListOld = persistentUsuario.getSuscriptorList();
            List<Suscriptor> suscriptorListNew = usuario.getSuscriptorList();
            List<Suscriptor> suscriptorList1Old = persistentUsuario.getSuscriptorList1();
            List<Suscriptor> suscriptorList1New = usuario.getSuscriptorList1();
            List<Comentario> comentarioListOld = persistentUsuario.getComentarioList();
            List<Comentario> comentarioListNew = usuario.getComentarioList();
            List<Notificacion> notificacionListOld = persistentUsuario.getNotificacionList();
            List<Notificacion> notificacionListNew = usuario.getNotificacionList();
            List<Publicacion> publicacionListOld = persistentUsuario.getPublicacionList();
            List<Publicacion> publicacionListNew = usuario.getPublicacionList();
            List<Mensaje> mensajeListOld = persistentUsuario.getMensajeList();
            List<Mensaje> mensajeListNew = usuario.getMensajeList();
            List<Mensaje> mensajeList1Old = persistentUsuario.getMensajeList1();
            List<Mensaje> mensajeList1New = usuario.getMensajeList1();
            List<String> illegalOrphanMessages = null;
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentario " + comentarioListOldComentario + " since its fkUsuario field is not nullable.");
                }
            }
            for (Mensaje mensajeListOldMensaje : mensajeListOld) {
                if (!mensajeListNew.contains(mensajeListOldMensaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensaje " + mensajeListOldMensaje + " since its fkUsuarioEmisor field is not nullable.");
                }
            }
            for (Mensaje mensajeList1OldMensaje : mensajeList1Old) {
                if (!mensajeList1New.contains(mensajeList1OldMensaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensaje " + mensajeList1OldMensaje + " since its fkUsuarioReceptor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkPerfilNew != null) {
                fkPerfilNew = em.getReference(fkPerfilNew.getClass(), fkPerfilNew.getPerfilId());
                usuario.setFkPerfil(fkPerfilNew);
            }
            if (fkPersonaNew != null) {
                fkPersonaNew = em.getReference(fkPersonaNew.getClass(), fkPersonaNew.getPersonaId());
                usuario.setFkPersona(fkPersonaNew);
            }
            if (fkRankingNew != null) {
                fkRankingNew = em.getReference(fkRankingNew.getClass(), fkRankingNew.getRankingId());
                usuario.setFkRanking(fkRankingNew);
            }
            List<Voto> attachedVotoListNew = new ArrayList<Voto>();
            for (Voto votoListNewVotoToAttach : votoListNew) {
                votoListNewVotoToAttach = em.getReference(votoListNewVotoToAttach.getClass(), votoListNewVotoToAttach.getVotoId());
                attachedVotoListNew.add(votoListNewVotoToAttach);
            }
            votoListNew = attachedVotoListNew;
            usuario.setVotoList(votoListNew);
            List<Voto> attachedVotoList1New = new ArrayList<Voto>();
            for (Voto votoList1NewVotoToAttach : votoList1New) {
                votoList1NewVotoToAttach = em.getReference(votoList1NewVotoToAttach.getClass(), votoList1NewVotoToAttach.getVotoId());
                attachedVotoList1New.add(votoList1NewVotoToAttach);
            }
            votoList1New = attachedVotoList1New;
            usuario.setVotoList1(votoList1New);
            List<Suscriptor> attachedSuscriptorListNew = new ArrayList<Suscriptor>();
            for (Suscriptor suscriptorListNewSuscriptorToAttach : suscriptorListNew) {
                suscriptorListNewSuscriptorToAttach = em.getReference(suscriptorListNewSuscriptorToAttach.getClass(), suscriptorListNewSuscriptorToAttach.getSuscriptorId());
                attachedSuscriptorListNew.add(suscriptorListNewSuscriptorToAttach);
            }
            suscriptorListNew = attachedSuscriptorListNew;
            usuario.setSuscriptorList(suscriptorListNew);
            List<Suscriptor> attachedSuscriptorList1New = new ArrayList<Suscriptor>();
            for (Suscriptor suscriptorList1NewSuscriptorToAttach : suscriptorList1New) {
                suscriptorList1NewSuscriptorToAttach = em.getReference(suscriptorList1NewSuscriptorToAttach.getClass(), suscriptorList1NewSuscriptorToAttach.getSuscriptorId());
                attachedSuscriptorList1New.add(suscriptorList1NewSuscriptorToAttach);
            }
            suscriptorList1New = attachedSuscriptorList1New;
            usuario.setSuscriptorList1(suscriptorList1New);
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getComentarioId());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            usuario.setComentarioList(comentarioListNew);
            List<Notificacion> attachedNotificacionListNew = new ArrayList<Notificacion>();
            for (Notificacion notificacionListNewNotificacionToAttach : notificacionListNew) {
                notificacionListNewNotificacionToAttach = em.getReference(notificacionListNewNotificacionToAttach.getClass(), notificacionListNewNotificacionToAttach.getNotificacionId());
                attachedNotificacionListNew.add(notificacionListNewNotificacionToAttach);
            }
            notificacionListNew = attachedNotificacionListNew;
            usuario.setNotificacionList(notificacionListNew);
            List<Publicacion> attachedPublicacionListNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionListNewPublicacionToAttach : publicacionListNew) {
                publicacionListNewPublicacionToAttach = em.getReference(publicacionListNewPublicacionToAttach.getClass(), publicacionListNewPublicacionToAttach.getPublicacionId());
                attachedPublicacionListNew.add(publicacionListNewPublicacionToAttach);
            }
            publicacionListNew = attachedPublicacionListNew;
            usuario.setPublicacionList(publicacionListNew);
            List<Mensaje> attachedMensajeListNew = new ArrayList<Mensaje>();
            for (Mensaje mensajeListNewMensajeToAttach : mensajeListNew) {
                mensajeListNewMensajeToAttach = em.getReference(mensajeListNewMensajeToAttach.getClass(), mensajeListNewMensajeToAttach.getMensajeId());
                attachedMensajeListNew.add(mensajeListNewMensajeToAttach);
            }
            mensajeListNew = attachedMensajeListNew;
            usuario.setMensajeList(mensajeListNew);
            List<Mensaje> attachedMensajeList1New = new ArrayList<Mensaje>();
            for (Mensaje mensajeList1NewMensajeToAttach : mensajeList1New) {
                mensajeList1NewMensajeToAttach = em.getReference(mensajeList1NewMensajeToAttach.getClass(), mensajeList1NewMensajeToAttach.getMensajeId());
                attachedMensajeList1New.add(mensajeList1NewMensajeToAttach);
            }
            mensajeList1New = attachedMensajeList1New;
            usuario.setMensajeList1(mensajeList1New);
            usuario = em.merge(usuario);
            if (fkPerfilOld != null && !fkPerfilOld.equals(fkPerfilNew)) {
                fkPerfilOld.getUsuarioList().remove(usuario);
                fkPerfilOld = em.merge(fkPerfilOld);
            }
            if (fkPerfilNew != null && !fkPerfilNew.equals(fkPerfilOld)) {
                fkPerfilNew.getUsuarioList().add(usuario);
                fkPerfilNew = em.merge(fkPerfilNew);
            }
            if (fkPersonaOld != null && !fkPersonaOld.equals(fkPersonaNew)) {
                fkPersonaOld.getUsuarioList().remove(usuario);
                fkPersonaOld = em.merge(fkPersonaOld);
            }
            if (fkPersonaNew != null && !fkPersonaNew.equals(fkPersonaOld)) {
                fkPersonaNew.getUsuarioList().add(usuario);
                fkPersonaNew = em.merge(fkPersonaNew);
            }
            if (fkRankingOld != null && !fkRankingOld.equals(fkRankingNew)) {
                fkRankingOld.getUsuarioList().remove(usuario);
                fkRankingOld = em.merge(fkRankingOld);
            }
            if (fkRankingNew != null && !fkRankingNew.equals(fkRankingOld)) {
                fkRankingNew.getUsuarioList().add(usuario);
                fkRankingNew = em.merge(fkRankingNew);
            }
            for (Voto votoListOldVoto : votoListOld) {
                if (!votoListNew.contains(votoListOldVoto)) {
                    votoListOldVoto.setFkUsuarioVotante(null);
                    votoListOldVoto = em.merge(votoListOldVoto);
                }
            }
            for (Voto votoListNewVoto : votoListNew) {
                if (!votoListOld.contains(votoListNewVoto)) {
                    Usuario oldFkUsuarioVotanteOfVotoListNewVoto = votoListNewVoto.getFkUsuarioVotante();
                    votoListNewVoto.setFkUsuarioVotante(usuario);
                    votoListNewVoto = em.merge(votoListNewVoto);
                    if (oldFkUsuarioVotanteOfVotoListNewVoto != null && !oldFkUsuarioVotanteOfVotoListNewVoto.equals(usuario)) {
                        oldFkUsuarioVotanteOfVotoListNewVoto.getVotoList().remove(votoListNewVoto);
                        oldFkUsuarioVotanteOfVotoListNewVoto = em.merge(oldFkUsuarioVotanteOfVotoListNewVoto);
                    }
                }
            }
            for (Voto votoList1OldVoto : votoList1Old) {
                if (!votoList1New.contains(votoList1OldVoto)) {
                    votoList1OldVoto.setFkUsuarioEvaluado(null);
                    votoList1OldVoto = em.merge(votoList1OldVoto);
                }
            }
            for (Voto votoList1NewVoto : votoList1New) {
                if (!votoList1Old.contains(votoList1NewVoto)) {
                    Usuario oldFkUsuarioEvaluadoOfVotoList1NewVoto = votoList1NewVoto.getFkUsuarioEvaluado();
                    votoList1NewVoto.setFkUsuarioEvaluado(usuario);
                    votoList1NewVoto = em.merge(votoList1NewVoto);
                    if (oldFkUsuarioEvaluadoOfVotoList1NewVoto != null && !oldFkUsuarioEvaluadoOfVotoList1NewVoto.equals(usuario)) {
                        oldFkUsuarioEvaluadoOfVotoList1NewVoto.getVotoList1().remove(votoList1NewVoto);
                        oldFkUsuarioEvaluadoOfVotoList1NewVoto = em.merge(oldFkUsuarioEvaluadoOfVotoList1NewVoto);
                    }
                }
            }
            for (Suscriptor suscriptorListOldSuscriptor : suscriptorListOld) {
                if (!suscriptorListNew.contains(suscriptorListOldSuscriptor)) {
                    suscriptorListOldSuscriptor.setFkUsuarioProveedor(null);
                    suscriptorListOldSuscriptor = em.merge(suscriptorListOldSuscriptor);
                }
            }
            for (Suscriptor suscriptorListNewSuscriptor : suscriptorListNew) {
                if (!suscriptorListOld.contains(suscriptorListNewSuscriptor)) {
                    Usuario oldFkUsuarioProveedorOfSuscriptorListNewSuscriptor = suscriptorListNewSuscriptor.getFkUsuarioProveedor();
                    suscriptorListNewSuscriptor.setFkUsuarioProveedor(usuario);
                    suscriptorListNewSuscriptor = em.merge(suscriptorListNewSuscriptor);
                    if (oldFkUsuarioProveedorOfSuscriptorListNewSuscriptor != null && !oldFkUsuarioProveedorOfSuscriptorListNewSuscriptor.equals(usuario)) {
                        oldFkUsuarioProveedorOfSuscriptorListNewSuscriptor.getSuscriptorList().remove(suscriptorListNewSuscriptor);
                        oldFkUsuarioProveedorOfSuscriptorListNewSuscriptor = em.merge(oldFkUsuarioProveedorOfSuscriptorListNewSuscriptor);
                    }
                }
            }
            for (Suscriptor suscriptorList1OldSuscriptor : suscriptorList1Old) {
                if (!suscriptorList1New.contains(suscriptorList1OldSuscriptor)) {
                    suscriptorList1OldSuscriptor.setFkUsuarioSuscriptor(null);
                    suscriptorList1OldSuscriptor = em.merge(suscriptorList1OldSuscriptor);
                }
            }
            for (Suscriptor suscriptorList1NewSuscriptor : suscriptorList1New) {
                if (!suscriptorList1Old.contains(suscriptorList1NewSuscriptor)) {
                    Usuario oldFkUsuarioSuscriptorOfSuscriptorList1NewSuscriptor = suscriptorList1NewSuscriptor.getFkUsuarioSuscriptor();
                    suscriptorList1NewSuscriptor.setFkUsuarioSuscriptor(usuario);
                    suscriptorList1NewSuscriptor = em.merge(suscriptorList1NewSuscriptor);
                    if (oldFkUsuarioSuscriptorOfSuscriptorList1NewSuscriptor != null && !oldFkUsuarioSuscriptorOfSuscriptorList1NewSuscriptor.equals(usuario)) {
                        oldFkUsuarioSuscriptorOfSuscriptorList1NewSuscriptor.getSuscriptorList1().remove(suscriptorList1NewSuscriptor);
                        oldFkUsuarioSuscriptorOfSuscriptorList1NewSuscriptor = em.merge(oldFkUsuarioSuscriptorOfSuscriptorList1NewSuscriptor);
                    }
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Usuario oldFkUsuarioOfComentarioListNewComentario = comentarioListNewComentario.getFkUsuario();
                    comentarioListNewComentario.setFkUsuario(usuario);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldFkUsuarioOfComentarioListNewComentario != null && !oldFkUsuarioOfComentarioListNewComentario.equals(usuario)) {
                        oldFkUsuarioOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldFkUsuarioOfComentarioListNewComentario = em.merge(oldFkUsuarioOfComentarioListNewComentario);
                    }
                }
            }
            for (Notificacion notificacionListOldNotificacion : notificacionListOld) {
                if (!notificacionListNew.contains(notificacionListOldNotificacion)) {
                    notificacionListOldNotificacion.setFkUsuario(null);
                    notificacionListOldNotificacion = em.merge(notificacionListOldNotificacion);
                }
            }
            for (Notificacion notificacionListNewNotificacion : notificacionListNew) {
                if (!notificacionListOld.contains(notificacionListNewNotificacion)) {
                    Usuario oldFkUsuarioOfNotificacionListNewNotificacion = notificacionListNewNotificacion.getFkUsuario();
                    notificacionListNewNotificacion.setFkUsuario(usuario);
                    notificacionListNewNotificacion = em.merge(notificacionListNewNotificacion);
                    if (oldFkUsuarioOfNotificacionListNewNotificacion != null && !oldFkUsuarioOfNotificacionListNewNotificacion.equals(usuario)) {
                        oldFkUsuarioOfNotificacionListNewNotificacion.getNotificacionList().remove(notificacionListNewNotificacion);
                        oldFkUsuarioOfNotificacionListNewNotificacion = em.merge(oldFkUsuarioOfNotificacionListNewNotificacion);
                    }
                }
            }
            for (Publicacion publicacionListOldPublicacion : publicacionListOld) {
                if (!publicacionListNew.contains(publicacionListOldPublicacion)) {
                    publicacionListOldPublicacion.setFkUsuario(null);
                    publicacionListOldPublicacion = em.merge(publicacionListOldPublicacion);
                }
            }
            for (Publicacion publicacionListNewPublicacion : publicacionListNew) {
                if (!publicacionListOld.contains(publicacionListNewPublicacion)) {
                    Usuario oldFkUsuarioOfPublicacionListNewPublicacion = publicacionListNewPublicacion.getFkUsuario();
                    publicacionListNewPublicacion.setFkUsuario(usuario);
                    publicacionListNewPublicacion = em.merge(publicacionListNewPublicacion);
                    if (oldFkUsuarioOfPublicacionListNewPublicacion != null && !oldFkUsuarioOfPublicacionListNewPublicacion.equals(usuario)) {
                        oldFkUsuarioOfPublicacionListNewPublicacion.getPublicacionList().remove(publicacionListNewPublicacion);
                        oldFkUsuarioOfPublicacionListNewPublicacion = em.merge(oldFkUsuarioOfPublicacionListNewPublicacion);
                    }
                }
            }
            for (Mensaje mensajeListNewMensaje : mensajeListNew) {
                if (!mensajeListOld.contains(mensajeListNewMensaje)) {
                    Usuario oldFkUsuarioEmisorOfMensajeListNewMensaje = mensajeListNewMensaje.getFkUsuarioEmisor();
                    mensajeListNewMensaje.setFkUsuarioEmisor(usuario);
                    mensajeListNewMensaje = em.merge(mensajeListNewMensaje);
                    if (oldFkUsuarioEmisorOfMensajeListNewMensaje != null && !oldFkUsuarioEmisorOfMensajeListNewMensaje.equals(usuario)) {
                        oldFkUsuarioEmisorOfMensajeListNewMensaje.getMensajeList().remove(mensajeListNewMensaje);
                        oldFkUsuarioEmisorOfMensajeListNewMensaje = em.merge(oldFkUsuarioEmisorOfMensajeListNewMensaje);
                    }
                }
            }
            for (Mensaje mensajeList1NewMensaje : mensajeList1New) {
                if (!mensajeList1Old.contains(mensajeList1NewMensaje)) {
                    Usuario oldFkUsuarioReceptorOfMensajeList1NewMensaje = mensajeList1NewMensaje.getFkUsuarioReceptor();
                    mensajeList1NewMensaje.setFkUsuarioReceptor(usuario);
                    mensajeList1NewMensaje = em.merge(mensajeList1NewMensaje);
                    if (oldFkUsuarioReceptorOfMensajeList1NewMensaje != null && !oldFkUsuarioReceptorOfMensajeList1NewMensaje.equals(usuario)) {
                        oldFkUsuarioReceptorOfMensajeList1NewMensaje.getMensajeList1().remove(mensajeList1NewMensaje);
                        oldFkUsuarioReceptorOfMensajeList1NewMensaje = em.merge(oldFkUsuarioReceptorOfMensajeList1NewMensaje);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getUsuarioId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comentario> comentarioListOrphanCheck = usuario.getComentarioList();
            for (Comentario comentarioListOrphanCheckComentario : comentarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Comentario " + comentarioListOrphanCheckComentario + " in its comentarioList field has a non-nullable fkUsuario field.");
            }
            List<Mensaje> mensajeListOrphanCheck = usuario.getMensajeList();
            for (Mensaje mensajeListOrphanCheckMensaje : mensajeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensaje " + mensajeListOrphanCheckMensaje + " in its mensajeList field has a non-nullable fkUsuarioEmisor field.");
            }
            List<Mensaje> mensajeList1OrphanCheck = usuario.getMensajeList1();
            for (Mensaje mensajeList1OrphanCheckMensaje : mensajeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensaje " + mensajeList1OrphanCheckMensaje + " in its mensajeList1 field has a non-nullable fkUsuarioReceptor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Perfil fkPerfil = usuario.getFkPerfil();
            if (fkPerfil != null) {
                fkPerfil.getUsuarioList().remove(usuario);
                fkPerfil = em.merge(fkPerfil);
            }
            Persona fkPersona = usuario.getFkPersona();
            if (fkPersona != null) {
                fkPersona.getUsuarioList().remove(usuario);
                fkPersona = em.merge(fkPersona);
            }
            Ranking fkRanking = usuario.getFkRanking();
            if (fkRanking != null) {
                fkRanking.getUsuarioList().remove(usuario);
                fkRanking = em.merge(fkRanking);
            }
            List<Voto> votoList = usuario.getVotoList();
            for (Voto votoListVoto : votoList) {
                votoListVoto.setFkUsuarioVotante(null);
                votoListVoto = em.merge(votoListVoto);
            }
            List<Voto> votoList1 = usuario.getVotoList1();
            for (Voto votoList1Voto : votoList1) {
                votoList1Voto.setFkUsuarioEvaluado(null);
                votoList1Voto = em.merge(votoList1Voto);
            }
            List<Suscriptor> suscriptorList = usuario.getSuscriptorList();
            for (Suscriptor suscriptorListSuscriptor : suscriptorList) {
                suscriptorListSuscriptor.setFkUsuarioProveedor(null);
                suscriptorListSuscriptor = em.merge(suscriptorListSuscriptor);
            }
            List<Suscriptor> suscriptorList1 = usuario.getSuscriptorList1();
            for (Suscriptor suscriptorList1Suscriptor : suscriptorList1) {
                suscriptorList1Suscriptor.setFkUsuarioSuscriptor(null);
                suscriptorList1Suscriptor = em.merge(suscriptorList1Suscriptor);
            }
            List<Notificacion> notificacionList = usuario.getNotificacionList();
            for (Notificacion notificacionListNotificacion : notificacionList) {
                notificacionListNotificacion.setFkUsuario(null);
                notificacionListNotificacion = em.merge(notificacionListNotificacion);
            }
            List<Publicacion> publicacionList = usuario.getPublicacionList();
            for (Publicacion publicacionListPublicacion : publicacionList) {
                publicacionListPublicacion.setFkUsuario(null);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
            }
            em.remove(usuario);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
