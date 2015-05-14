/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Comentario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class ComentarioJpaController implements Serializable {

    public ComentarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentario comentario) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Publicacion fkPublicacion = comentario.getFkPublicacion();
            if (fkPublicacion != null) {
                fkPublicacion = em.getReference(fkPublicacion.getClass(), fkPublicacion.getPublicacionId());
                comentario.setFkPublicacion(fkPublicacion);
            }
            Usuario fkUsuario = comentario.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario = em.getReference(fkUsuario.getClass(), fkUsuario.getUsuarioId());
                comentario.setFkUsuario(fkUsuario);
            }
            em.persist(comentario);
            if (fkPublicacion != null) {
                fkPublicacion.getComentarioList().add(comentario);
                fkPublicacion = em.merge(fkPublicacion);
            }
            if (fkUsuario != null) {
                fkUsuario.getComentarioList().add(comentario);
                fkUsuario = em.merge(fkUsuario);
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

    public void edit(Comentario comentario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Comentario persistentComentario = em.find(Comentario.class, comentario.getComentarioId());
            Publicacion fkPublicacionOld = persistentComentario.getFkPublicacion();
            Publicacion fkPublicacionNew = comentario.getFkPublicacion();
            Usuario fkUsuarioOld = persistentComentario.getFkUsuario();
            Usuario fkUsuarioNew = comentario.getFkUsuario();
            if (fkPublicacionNew != null) {
                fkPublicacionNew = em.getReference(fkPublicacionNew.getClass(), fkPublicacionNew.getPublicacionId());
                comentario.setFkPublicacion(fkPublicacionNew);
            }
            if (fkUsuarioNew != null) {
                fkUsuarioNew = em.getReference(fkUsuarioNew.getClass(), fkUsuarioNew.getUsuarioId());
                comentario.setFkUsuario(fkUsuarioNew);
            }
            comentario = em.merge(comentario);
            if (fkPublicacionOld != null && !fkPublicacionOld.equals(fkPublicacionNew)) {
                fkPublicacionOld.getComentarioList().remove(comentario);
                fkPublicacionOld = em.merge(fkPublicacionOld);
            }
            if (fkPublicacionNew != null && !fkPublicacionNew.equals(fkPublicacionOld)) {
                fkPublicacionNew.getComentarioList().add(comentario);
                fkPublicacionNew = em.merge(fkPublicacionNew);
            }
            if (fkUsuarioOld != null && !fkUsuarioOld.equals(fkUsuarioNew)) {
                fkUsuarioOld.getComentarioList().remove(comentario);
                fkUsuarioOld = em.merge(fkUsuarioOld);
            }
            if (fkUsuarioNew != null && !fkUsuarioNew.equals(fkUsuarioOld)) {
                fkUsuarioNew.getComentarioList().add(comentario);
                fkUsuarioNew = em.merge(fkUsuarioNew);
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
                Long id = comentario.getComentarioId();
                if (findComentario(id) == null) {
                    throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
                comentario.getComentarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.", enfe);
            }
            Publicacion fkPublicacion = comentario.getFkPublicacion();
            if (fkPublicacion != null) {
                fkPublicacion.getComentarioList().remove(comentario);
                fkPublicacion = em.merge(fkPublicacion);
            }
            Usuario fkUsuario = comentario.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario.getComentarioList().remove(comentario);
                fkUsuario = em.merge(fkUsuario);
            }
            em.remove(comentario);
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

    public List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }

    public List<Comentario> findComentarioEntities(int maxResults, int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }

    private List<Comentario> findComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentario.class));
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

    public Comentario findComentario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentario> rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
