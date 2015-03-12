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
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class TipoPublicacionJpaController implements Serializable {

    public TipoPublicacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPublicacion tipoPublicacion) throws RollbackFailureException, Exception {
        if (tipoPublicacion.getPublicacionList() == null) {
            tipoPublicacion.setPublicacionList(new ArrayList<Publicacion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Publicacion> attachedPublicacionList = new ArrayList<Publicacion>();
            for (Publicacion publicacionListPublicacionToAttach : tipoPublicacion.getPublicacionList()) {
                publicacionListPublicacionToAttach = em.getReference(publicacionListPublicacionToAttach.getClass(), publicacionListPublicacionToAttach.getPublicacionId());
                attachedPublicacionList.add(publicacionListPublicacionToAttach);
            }
            tipoPublicacion.setPublicacionList(attachedPublicacionList);
            em.persist(tipoPublicacion);
            for (Publicacion publicacionListPublicacion : tipoPublicacion.getPublicacionList()) {
                TipoPublicacion oldFkTipoPublicacionOfPublicacionListPublicacion = publicacionListPublicacion.getFkTipoPublicacion();
                publicacionListPublicacion.setFkTipoPublicacion(tipoPublicacion);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
                if (oldFkTipoPublicacionOfPublicacionListPublicacion != null) {
                    oldFkTipoPublicacionOfPublicacionListPublicacion.getPublicacionList().remove(publicacionListPublicacion);
                    oldFkTipoPublicacionOfPublicacionListPublicacion = em.merge(oldFkTipoPublicacionOfPublicacionListPublicacion);
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

    public void edit(TipoPublicacion tipoPublicacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoPublicacion persistentTipoPublicacion = em.find(TipoPublicacion.class, tipoPublicacion.getTipoPublicacionId());
            List<Publicacion> publicacionListOld = persistentTipoPublicacion.getPublicacionList();
            List<Publicacion> publicacionListNew = tipoPublicacion.getPublicacionList();
            List<Publicacion> attachedPublicacionListNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionListNewPublicacionToAttach : publicacionListNew) {
                publicacionListNewPublicacionToAttach = em.getReference(publicacionListNewPublicacionToAttach.getClass(), publicacionListNewPublicacionToAttach.getPublicacionId());
                attachedPublicacionListNew.add(publicacionListNewPublicacionToAttach);
            }
            publicacionListNew = attachedPublicacionListNew;
            tipoPublicacion.setPublicacionList(publicacionListNew);
            tipoPublicacion = em.merge(tipoPublicacion);
            for (Publicacion publicacionListOldPublicacion : publicacionListOld) {
                if (!publicacionListNew.contains(publicacionListOldPublicacion)) {
                    publicacionListOldPublicacion.setFkTipoPublicacion(null);
                    publicacionListOldPublicacion = em.merge(publicacionListOldPublicacion);
                }
            }
            for (Publicacion publicacionListNewPublicacion : publicacionListNew) {
                if (!publicacionListOld.contains(publicacionListNewPublicacion)) {
                    TipoPublicacion oldFkTipoPublicacionOfPublicacionListNewPublicacion = publicacionListNewPublicacion.getFkTipoPublicacion();
                    publicacionListNewPublicacion.setFkTipoPublicacion(tipoPublicacion);
                    publicacionListNewPublicacion = em.merge(publicacionListNewPublicacion);
                    if (oldFkTipoPublicacionOfPublicacionListNewPublicacion != null && !oldFkTipoPublicacionOfPublicacionListNewPublicacion.equals(tipoPublicacion)) {
                        oldFkTipoPublicacionOfPublicacionListNewPublicacion.getPublicacionList().remove(publicacionListNewPublicacion);
                        oldFkTipoPublicacionOfPublicacionListNewPublicacion = em.merge(oldFkTipoPublicacionOfPublicacionListNewPublicacion);
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
                Long id = tipoPublicacion.getTipoPublicacionId();
                if (findTipoPublicacion(id) == null) {
                    throw new NonexistentEntityException("The tipoPublicacion with id " + id + " no longer exists.");
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
            TipoPublicacion tipoPublicacion;
            try {
                tipoPublicacion = em.getReference(TipoPublicacion.class, id);
                tipoPublicacion.getTipoPublicacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPublicacion with id " + id + " no longer exists.", enfe);
            }
            List<Publicacion> publicacionList = tipoPublicacion.getPublicacionList();
            for (Publicacion publicacionListPublicacion : publicacionList) {
                publicacionListPublicacion.setFkTipoPublicacion(null);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
            }
            em.remove(tipoPublicacion);
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

    public List<TipoPublicacion> findTipoPublicacionEntities() {
        return findTipoPublicacionEntities(true, -1, -1);
    }

    public List<TipoPublicacion> findTipoPublicacionEntities(int maxResults, int firstResult) {
        return findTipoPublicacionEntities(false, maxResults, firstResult);
    }

    private List<TipoPublicacion> findTipoPublicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPublicacion.class));
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

    public TipoPublicacion findTipoPublicacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPublicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPublicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPublicacion> rt = cq.from(TipoPublicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
