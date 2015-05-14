/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Caracteristica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Producto;
import com.tecnogeek.comprameya.entidad.TipoCaracteristica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class CaracteristicaJpaController implements Serializable {

    public CaracteristicaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caracteristica caracteristica) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto fkProducto = caracteristica.getFkProducto();
            if (fkProducto != null) {
                fkProducto = em.getReference(fkProducto.getClass(), fkProducto.getProductoId());
                caracteristica.setFkProducto(fkProducto);
            }
            TipoCaracteristica fkTipoCaracteristica = caracteristica.getFkTipoCaracteristica();
            if (fkTipoCaracteristica != null) {
                fkTipoCaracteristica = em.getReference(fkTipoCaracteristica.getClass(), fkTipoCaracteristica.getTipoCaracteristicaId());
                caracteristica.setFkTipoCaracteristica(fkTipoCaracteristica);
            }
            em.persist(caracteristica);
            if (fkProducto != null) {
                fkProducto.getCaracteristicaList().add(caracteristica);
                fkProducto = em.merge(fkProducto);
            }
            if (fkTipoCaracteristica != null) {
                fkTipoCaracteristica.getCaracteristicaList().add(caracteristica);
                fkTipoCaracteristica = em.merge(fkTipoCaracteristica);
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

    public void edit(Caracteristica caracteristica) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Caracteristica persistentCaracteristica = em.find(Caracteristica.class, caracteristica.getCaracteristicaId());
            Producto fkProductoOld = persistentCaracteristica.getFkProducto();
            Producto fkProductoNew = caracteristica.getFkProducto();
            TipoCaracteristica fkTipoCaracteristicaOld = persistentCaracteristica.getFkTipoCaracteristica();
            TipoCaracteristica fkTipoCaracteristicaNew = caracteristica.getFkTipoCaracteristica();
            if (fkProductoNew != null) {
                fkProductoNew = em.getReference(fkProductoNew.getClass(), fkProductoNew.getProductoId());
                caracteristica.setFkProducto(fkProductoNew);
            }
            if (fkTipoCaracteristicaNew != null) {
                fkTipoCaracteristicaNew = em.getReference(fkTipoCaracteristicaNew.getClass(), fkTipoCaracteristicaNew.getTipoCaracteristicaId());
                caracteristica.setFkTipoCaracteristica(fkTipoCaracteristicaNew);
            }
            caracteristica = em.merge(caracteristica);
            if (fkProductoOld != null && !fkProductoOld.equals(fkProductoNew)) {
                fkProductoOld.getCaracteristicaList().remove(caracteristica);
                fkProductoOld = em.merge(fkProductoOld);
            }
            if (fkProductoNew != null && !fkProductoNew.equals(fkProductoOld)) {
                fkProductoNew.getCaracteristicaList().add(caracteristica);
                fkProductoNew = em.merge(fkProductoNew);
            }
            if (fkTipoCaracteristicaOld != null && !fkTipoCaracteristicaOld.equals(fkTipoCaracteristicaNew)) {
                fkTipoCaracteristicaOld.getCaracteristicaList().remove(caracteristica);
                fkTipoCaracteristicaOld = em.merge(fkTipoCaracteristicaOld);
            }
            if (fkTipoCaracteristicaNew != null && !fkTipoCaracteristicaNew.equals(fkTipoCaracteristicaOld)) {
                fkTipoCaracteristicaNew.getCaracteristicaList().add(caracteristica);
                fkTipoCaracteristicaNew = em.merge(fkTipoCaracteristicaNew);
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
                Long id = caracteristica.getCaracteristicaId();
                if (findCaracteristica(id) == null) {
                    throw new NonexistentEntityException("The caracteristica with id " + id + " no longer exists.");
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
            Caracteristica caracteristica;
            try {
                caracteristica = em.getReference(Caracteristica.class, id);
                caracteristica.getCaracteristicaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caracteristica with id " + id + " no longer exists.", enfe);
            }
            Producto fkProducto = caracteristica.getFkProducto();
            if (fkProducto != null) {
                fkProducto.getCaracteristicaList().remove(caracteristica);
                fkProducto = em.merge(fkProducto);
            }
            TipoCaracteristica fkTipoCaracteristica = caracteristica.getFkTipoCaracteristica();
            if (fkTipoCaracteristica != null) {
                fkTipoCaracteristica.getCaracteristicaList().remove(caracteristica);
                fkTipoCaracteristica = em.merge(fkTipoCaracteristica);
            }
            em.remove(caracteristica);
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

    public List<Caracteristica> findCaracteristicaEntities() {
        return findCaracteristicaEntities(true, -1, -1);
    }

    public List<Caracteristica> findCaracteristicaEntities(int maxResults, int firstResult) {
        return findCaracteristicaEntities(false, maxResults, firstResult);
    }

    private List<Caracteristica> findCaracteristicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caracteristica.class));
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

    public Caracteristica findCaracteristica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caracteristica.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaracteristicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caracteristica> rt = cq.from(Caracteristica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
