/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.TipoNotificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class TipoNotificacionJpaController implements Serializable {

    public TipoNotificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoNotificacion tipoNotificacion) throws RollbackFailureException, Exception {
        if (tipoNotificacion.getNotificacionList() == null) {
            tipoNotificacion.setNotificacionList(new ArrayList<Notificacion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Notificacion> attachedNotificacionList = new ArrayList<Notificacion>();
            for (Notificacion notificacionListNotificacionToAttach : tipoNotificacion.getNotificacionList()) {
                notificacionListNotificacionToAttach = em.getReference(notificacionListNotificacionToAttach.getClass(), notificacionListNotificacionToAttach.getNotificacionId());
                attachedNotificacionList.add(notificacionListNotificacionToAttach);
            }
            tipoNotificacion.setNotificacionList(attachedNotificacionList);
            em.persist(tipoNotificacion);
            for (Notificacion notificacionListNotificacion : tipoNotificacion.getNotificacionList()) {
                TipoNotificacion oldFkTipoNotificacionOfNotificacionListNotificacion = notificacionListNotificacion.getFkTipoNotificacion();
                notificacionListNotificacion.setFkTipoNotificacion(tipoNotificacion);
                notificacionListNotificacion = em.merge(notificacionListNotificacion);
                if (oldFkTipoNotificacionOfNotificacionListNotificacion != null) {
                    oldFkTipoNotificacionOfNotificacionListNotificacion.getNotificacionList().remove(notificacionListNotificacion);
                    oldFkTipoNotificacionOfNotificacionListNotificacion = em.merge(oldFkTipoNotificacionOfNotificacionListNotificacion);
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

    public void edit(TipoNotificacion tipoNotificacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoNotificacion persistentTipoNotificacion = em.find(TipoNotificacion.class, tipoNotificacion.getTipoNotificacionId());
            List<Notificacion> notificacionListOld = persistentTipoNotificacion.getNotificacionList();
            List<Notificacion> notificacionListNew = tipoNotificacion.getNotificacionList();
            List<Notificacion> attachedNotificacionListNew = new ArrayList<Notificacion>();
            for (Notificacion notificacionListNewNotificacionToAttach : notificacionListNew) {
                notificacionListNewNotificacionToAttach = em.getReference(notificacionListNewNotificacionToAttach.getClass(), notificacionListNewNotificacionToAttach.getNotificacionId());
                attachedNotificacionListNew.add(notificacionListNewNotificacionToAttach);
            }
            notificacionListNew = attachedNotificacionListNew;
            tipoNotificacion.setNotificacionList(notificacionListNew);
            tipoNotificacion = em.merge(tipoNotificacion);
            for (Notificacion notificacionListOldNotificacion : notificacionListOld) {
                if (!notificacionListNew.contains(notificacionListOldNotificacion)) {
                    notificacionListOldNotificacion.setFkTipoNotificacion(null);
                    notificacionListOldNotificacion = em.merge(notificacionListOldNotificacion);
                }
            }
            for (Notificacion notificacionListNewNotificacion : notificacionListNew) {
                if (!notificacionListOld.contains(notificacionListNewNotificacion)) {
                    TipoNotificacion oldFkTipoNotificacionOfNotificacionListNewNotificacion = notificacionListNewNotificacion.getFkTipoNotificacion();
                    notificacionListNewNotificacion.setFkTipoNotificacion(tipoNotificacion);
                    notificacionListNewNotificacion = em.merge(notificacionListNewNotificacion);
                    if (oldFkTipoNotificacionOfNotificacionListNewNotificacion != null && !oldFkTipoNotificacionOfNotificacionListNewNotificacion.equals(tipoNotificacion)) {
                        oldFkTipoNotificacionOfNotificacionListNewNotificacion.getNotificacionList().remove(notificacionListNewNotificacion);
                        oldFkTipoNotificacionOfNotificacionListNewNotificacion = em.merge(oldFkTipoNotificacionOfNotificacionListNewNotificacion);
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
                Long id = tipoNotificacion.getTipoNotificacionId();
                if (findTipoNotificacion(id) == null) {
                    throw new NonexistentEntityException("The tipoNotificacion with id " + id + " no longer exists.");
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
            TipoNotificacion tipoNotificacion;
            try {
                tipoNotificacion = em.getReference(TipoNotificacion.class, id);
                tipoNotificacion.getTipoNotificacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoNotificacion with id " + id + " no longer exists.", enfe);
            }
            List<Notificacion> notificacionList = tipoNotificacion.getNotificacionList();
            for (Notificacion notificacionListNotificacion : notificacionList) {
                notificacionListNotificacion.setFkTipoNotificacion(null);
                notificacionListNotificacion = em.merge(notificacionListNotificacion);
            }
            em.remove(tipoNotificacion);
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

    public List<TipoNotificacion> findTipoNotificacionEntities() {
        return findTipoNotificacionEntities(true, -1, -1);
    }

    public List<TipoNotificacion> findTipoNotificacionEntities(int maxResults, int firstResult) {
        return findTipoNotificacionEntities(false, maxResults, firstResult);
    }

    private List<TipoNotificacion> findTipoNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoNotificacion.class));
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

    public TipoNotificacion findTipoNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoNotificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoNotificacion> rt = cq.from(TipoNotificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
