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
import com.tecnogeek.comprameya.entidad.Caracteristica;
import com.tecnogeek.comprameya.entidad.TipoCaracteristica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class TipoCaracteristicaJpaController implements Serializable {

    public TipoCaracteristicaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoCaracteristica tipoCaracteristica) throws RollbackFailureException, Exception {
        if (tipoCaracteristica.getCaracteristicaList() == null) {
            tipoCaracteristica.setCaracteristicaList(new ArrayList<Caracteristica>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Caracteristica> attachedCaracteristicaList = new ArrayList<Caracteristica>();
            for (Caracteristica caracteristicaListCaracteristicaToAttach : tipoCaracteristica.getCaracteristicaList()) {
                caracteristicaListCaracteristicaToAttach = em.getReference(caracteristicaListCaracteristicaToAttach.getClass(), caracteristicaListCaracteristicaToAttach.getCaracteristicaId());
                attachedCaracteristicaList.add(caracteristicaListCaracteristicaToAttach);
            }
            tipoCaracteristica.setCaracteristicaList(attachedCaracteristicaList);
            em.persist(tipoCaracteristica);
            for (Caracteristica caracteristicaListCaracteristica : tipoCaracteristica.getCaracteristicaList()) {
                TipoCaracteristica oldFkTipoCaracteristicaOfCaracteristicaListCaracteristica = caracteristicaListCaracteristica.getFkTipoCaracteristica();
                caracteristicaListCaracteristica.setFkTipoCaracteristica(tipoCaracteristica);
                caracteristicaListCaracteristica = em.merge(caracteristicaListCaracteristica);
                if (oldFkTipoCaracteristicaOfCaracteristicaListCaracteristica != null) {
                    oldFkTipoCaracteristicaOfCaracteristicaListCaracteristica.getCaracteristicaList().remove(caracteristicaListCaracteristica);
                    oldFkTipoCaracteristicaOfCaracteristicaListCaracteristica = em.merge(oldFkTipoCaracteristicaOfCaracteristicaListCaracteristica);
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

    public void edit(TipoCaracteristica tipoCaracteristica) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoCaracteristica persistentTipoCaracteristica = em.find(TipoCaracteristica.class, tipoCaracteristica.getTipoCaracteristicaId());
            List<Caracteristica> caracteristicaListOld = persistentTipoCaracteristica.getCaracteristicaList();
            List<Caracteristica> caracteristicaListNew = tipoCaracteristica.getCaracteristicaList();
            List<Caracteristica> attachedCaracteristicaListNew = new ArrayList<Caracteristica>();
            for (Caracteristica caracteristicaListNewCaracteristicaToAttach : caracteristicaListNew) {
                caracteristicaListNewCaracteristicaToAttach = em.getReference(caracteristicaListNewCaracteristicaToAttach.getClass(), caracteristicaListNewCaracteristicaToAttach.getCaracteristicaId());
                attachedCaracteristicaListNew.add(caracteristicaListNewCaracteristicaToAttach);
            }
            caracteristicaListNew = attachedCaracteristicaListNew;
            tipoCaracteristica.setCaracteristicaList(caracteristicaListNew);
            tipoCaracteristica = em.merge(tipoCaracteristica);
            for (Caracteristica caracteristicaListOldCaracteristica : caracteristicaListOld) {
                if (!caracteristicaListNew.contains(caracteristicaListOldCaracteristica)) {
                    caracteristicaListOldCaracteristica.setFkTipoCaracteristica(null);
                    caracteristicaListOldCaracteristica = em.merge(caracteristicaListOldCaracteristica);
                }
            }
            for (Caracteristica caracteristicaListNewCaracteristica : caracteristicaListNew) {
                if (!caracteristicaListOld.contains(caracteristicaListNewCaracteristica)) {
                    TipoCaracteristica oldFkTipoCaracteristicaOfCaracteristicaListNewCaracteristica = caracteristicaListNewCaracteristica.getFkTipoCaracteristica();
                    caracteristicaListNewCaracteristica.setFkTipoCaracteristica(tipoCaracteristica);
                    caracteristicaListNewCaracteristica = em.merge(caracteristicaListNewCaracteristica);
                    if (oldFkTipoCaracteristicaOfCaracteristicaListNewCaracteristica != null && !oldFkTipoCaracteristicaOfCaracteristicaListNewCaracteristica.equals(tipoCaracteristica)) {
                        oldFkTipoCaracteristicaOfCaracteristicaListNewCaracteristica.getCaracteristicaList().remove(caracteristicaListNewCaracteristica);
                        oldFkTipoCaracteristicaOfCaracteristicaListNewCaracteristica = em.merge(oldFkTipoCaracteristicaOfCaracteristicaListNewCaracteristica);
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
                Long id = tipoCaracteristica.getTipoCaracteristicaId();
                if (findTipoCaracteristica(id) == null) {
                    throw new NonexistentEntityException("The tipoCaracteristica with id " + id + " no longer exists.");
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
            TipoCaracteristica tipoCaracteristica;
            try {
                tipoCaracteristica = em.getReference(TipoCaracteristica.class, id);
                tipoCaracteristica.getTipoCaracteristicaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoCaracteristica with id " + id + " no longer exists.", enfe);
            }
            List<Caracteristica> caracteristicaList = tipoCaracteristica.getCaracteristicaList();
            for (Caracteristica caracteristicaListCaracteristica : caracteristicaList) {
                caracteristicaListCaracteristica.setFkTipoCaracteristica(null);
                caracteristicaListCaracteristica = em.merge(caracteristicaListCaracteristica);
            }
            em.remove(tipoCaracteristica);
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

    public List<TipoCaracteristica> findTipoCaracteristicaEntities() {
        return findTipoCaracteristicaEntities(true, -1, -1);
    }

    public List<TipoCaracteristica> findTipoCaracteristicaEntities(int maxResults, int firstResult) {
        return findTipoCaracteristicaEntities(false, maxResults, firstResult);
    }

    private List<TipoCaracteristica> findTipoCaracteristicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoCaracteristica.class));
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

    public TipoCaracteristica findTipoCaracteristica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoCaracteristica.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoCaracteristicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoCaracteristica> rt = cq.from(TipoCaracteristica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
