/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Suscriptor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class SuscriptorJpaController implements Serializable {

    public SuscriptorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suscriptor suscriptor) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario fkUsuarioProveedor = suscriptor.getFkUsuarioProveedor();
            if (fkUsuarioProveedor != null) {
                fkUsuarioProveedor = em.getReference(fkUsuarioProveedor.getClass(), fkUsuarioProveedor.getUsuarioId());
                suscriptor.setFkUsuarioProveedor(fkUsuarioProveedor);
            }
            Usuario fkUsuarioSuscriptor = suscriptor.getFkUsuarioSuscriptor();
            if (fkUsuarioSuscriptor != null) {
                fkUsuarioSuscriptor = em.getReference(fkUsuarioSuscriptor.getClass(), fkUsuarioSuscriptor.getUsuarioId());
                suscriptor.setFkUsuarioSuscriptor(fkUsuarioSuscriptor);
            }
            em.persist(suscriptor);
            if (fkUsuarioProveedor != null) {
                fkUsuarioProveedor.getSuscriptorList().add(suscriptor);
                fkUsuarioProveedor = em.merge(fkUsuarioProveedor);
            }
            if (fkUsuarioSuscriptor != null) {
                fkUsuarioSuscriptor.getSuscriptorList().add(suscriptor);
                fkUsuarioSuscriptor = em.merge(fkUsuarioSuscriptor);
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

    public void edit(Suscriptor suscriptor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Suscriptor persistentSuscriptor = em.find(Suscriptor.class, suscriptor.getSuscriptorId());
            Usuario fkUsuarioProveedorOld = persistentSuscriptor.getFkUsuarioProveedor();
            Usuario fkUsuarioProveedorNew = suscriptor.getFkUsuarioProveedor();
            Usuario fkUsuarioSuscriptorOld = persistentSuscriptor.getFkUsuarioSuscriptor();
            Usuario fkUsuarioSuscriptorNew = suscriptor.getFkUsuarioSuscriptor();
            if (fkUsuarioProveedorNew != null) {
                fkUsuarioProveedorNew = em.getReference(fkUsuarioProveedorNew.getClass(), fkUsuarioProveedorNew.getUsuarioId());
                suscriptor.setFkUsuarioProveedor(fkUsuarioProveedorNew);
            }
            if (fkUsuarioSuscriptorNew != null) {
                fkUsuarioSuscriptorNew = em.getReference(fkUsuarioSuscriptorNew.getClass(), fkUsuarioSuscriptorNew.getUsuarioId());
                suscriptor.setFkUsuarioSuscriptor(fkUsuarioSuscriptorNew);
            }
            suscriptor = em.merge(suscriptor);
            if (fkUsuarioProveedorOld != null && !fkUsuarioProveedorOld.equals(fkUsuarioProveedorNew)) {
                fkUsuarioProveedorOld.getSuscriptorList().remove(suscriptor);
                fkUsuarioProveedorOld = em.merge(fkUsuarioProveedorOld);
            }
            if (fkUsuarioProveedorNew != null && !fkUsuarioProveedorNew.equals(fkUsuarioProveedorOld)) {
                fkUsuarioProveedorNew.getSuscriptorList().add(suscriptor);
                fkUsuarioProveedorNew = em.merge(fkUsuarioProveedorNew);
            }
            if (fkUsuarioSuscriptorOld != null && !fkUsuarioSuscriptorOld.equals(fkUsuarioSuscriptorNew)) {
                fkUsuarioSuscriptorOld.getSuscriptorList().remove(suscriptor);
                fkUsuarioSuscriptorOld = em.merge(fkUsuarioSuscriptorOld);
            }
            if (fkUsuarioSuscriptorNew != null && !fkUsuarioSuscriptorNew.equals(fkUsuarioSuscriptorOld)) {
                fkUsuarioSuscriptorNew.getSuscriptorList().add(suscriptor);
                fkUsuarioSuscriptorNew = em.merge(fkUsuarioSuscriptorNew);
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
                Long id = suscriptor.getSuscriptorId();
                if (findSuscriptor(id) == null) {
                    throw new NonexistentEntityException("The suscriptor with id " + id + " no longer exists.");
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
            Suscriptor suscriptor;
            try {
                suscriptor = em.getReference(Suscriptor.class, id);
                suscriptor.getSuscriptorId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suscriptor with id " + id + " no longer exists.", enfe);
            }
            Usuario fkUsuarioProveedor = suscriptor.getFkUsuarioProveedor();
            if (fkUsuarioProveedor != null) {
                fkUsuarioProveedor.getSuscriptorList().remove(suscriptor);
                fkUsuarioProveedor = em.merge(fkUsuarioProveedor);
            }
            Usuario fkUsuarioSuscriptor = suscriptor.getFkUsuarioSuscriptor();
            if (fkUsuarioSuscriptor != null) {
                fkUsuarioSuscriptor.getSuscriptorList().remove(suscriptor);
                fkUsuarioSuscriptor = em.merge(fkUsuarioSuscriptor);
            }
            em.remove(suscriptor);
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

    public List<Suscriptor> findSuscriptorEntities() {
        return findSuscriptorEntities(true, -1, -1);
    }

    public List<Suscriptor> findSuscriptorEntities(int maxResults, int firstResult) {
        return findSuscriptorEntities(false, maxResults, firstResult);
    }

    private List<Suscriptor> findSuscriptorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Suscriptor.class));
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

    public Suscriptor findSuscriptor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suscriptor.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuscriptorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Suscriptor> rt = cq.from(Suscriptor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
