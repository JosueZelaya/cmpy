/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Notificacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.TipoNotificacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class NotificacionJpaController implements Serializable {

    public NotificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notificacion notificacion) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoNotificacion fkTipoNotificacion = notificacion.getFkTipoNotificacion();
            if (fkTipoNotificacion != null) {
                fkTipoNotificacion = em.getReference(fkTipoNotificacion.getClass(), fkTipoNotificacion.getTipoNotificacionId());
                notificacion.setFkTipoNotificacion(fkTipoNotificacion);
            }
            Usuario fkUsuario = notificacion.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario = em.getReference(fkUsuario.getClass(), fkUsuario.getUsuarioId());
                notificacion.setFkUsuario(fkUsuario);
            }
            em.persist(notificacion);
            if (fkTipoNotificacion != null) {
                fkTipoNotificacion.getNotificacionList().add(notificacion);
                fkTipoNotificacion = em.merge(fkTipoNotificacion);
            }
            if (fkUsuario != null) {
                fkUsuario.getNotificacionList().add(notificacion);
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

    public void edit(Notificacion notificacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Notificacion persistentNotificacion = em.find(Notificacion.class, notificacion.getNotificacionId());
            TipoNotificacion fkTipoNotificacionOld = persistentNotificacion.getFkTipoNotificacion();
            TipoNotificacion fkTipoNotificacionNew = notificacion.getFkTipoNotificacion();
            Usuario fkUsuarioOld = persistentNotificacion.getFkUsuario();
            Usuario fkUsuarioNew = notificacion.getFkUsuario();
            if (fkTipoNotificacionNew != null) {
                fkTipoNotificacionNew = em.getReference(fkTipoNotificacionNew.getClass(), fkTipoNotificacionNew.getTipoNotificacionId());
                notificacion.setFkTipoNotificacion(fkTipoNotificacionNew);
            }
            if (fkUsuarioNew != null) {
                fkUsuarioNew = em.getReference(fkUsuarioNew.getClass(), fkUsuarioNew.getUsuarioId());
                notificacion.setFkUsuario(fkUsuarioNew);
            }
            notificacion = em.merge(notificacion);
            if (fkTipoNotificacionOld != null && !fkTipoNotificacionOld.equals(fkTipoNotificacionNew)) {
                fkTipoNotificacionOld.getNotificacionList().remove(notificacion);
                fkTipoNotificacionOld = em.merge(fkTipoNotificacionOld);
            }
            if (fkTipoNotificacionNew != null && !fkTipoNotificacionNew.equals(fkTipoNotificacionOld)) {
                fkTipoNotificacionNew.getNotificacionList().add(notificacion);
                fkTipoNotificacionNew = em.merge(fkTipoNotificacionNew);
            }
            if (fkUsuarioOld != null && !fkUsuarioOld.equals(fkUsuarioNew)) {
                fkUsuarioOld.getNotificacionList().remove(notificacion);
                fkUsuarioOld = em.merge(fkUsuarioOld);
            }
            if (fkUsuarioNew != null && !fkUsuarioNew.equals(fkUsuarioOld)) {
                fkUsuarioNew.getNotificacionList().add(notificacion);
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
                Long id = notificacion.getNotificacionId();
                if (findNotificacion(id) == null) {
                    throw new NonexistentEntityException("The notificacion with id " + id + " no longer exists.");
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
            Notificacion notificacion;
            try {
                notificacion = em.getReference(Notificacion.class, id);
                notificacion.getNotificacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacion with id " + id + " no longer exists.", enfe);
            }
            TipoNotificacion fkTipoNotificacion = notificacion.getFkTipoNotificacion();
            if (fkTipoNotificacion != null) {
                fkTipoNotificacion.getNotificacionList().remove(notificacion);
                fkTipoNotificacion = em.merge(fkTipoNotificacion);
            }
            Usuario fkUsuario = notificacion.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario.getNotificacionList().remove(notificacion);
                fkUsuario = em.merge(fkUsuario);
            }
            em.remove(notificacion);
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

    public List<Notificacion> findNotificacionEntities() {
        return findNotificacionEntities(true, -1, -1);
    }

    public List<Notificacion> findNotificacionEntities(int maxResults, int firstResult) {
        return findNotificacionEntities(false, maxResults, firstResult);
    }

    private List<Notificacion> findNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notificacion.class));
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

    public Notificacion findNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notificacion> rt = cq.from(Notificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
