/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Marca;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Modelo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class MarcaJpaController implements Serializable {

    public MarcaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) throws RollbackFailureException, Exception {
        if (marca.getModeloList() == null) {
            marca.setModeloList(new ArrayList<Modelo>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Modelo> attachedModeloList = new ArrayList<Modelo>();
            for (Modelo modeloListModeloToAttach : marca.getModeloList()) {
                modeloListModeloToAttach = em.getReference(modeloListModeloToAttach.getClass(), modeloListModeloToAttach.getModeloId());
                attachedModeloList.add(modeloListModeloToAttach);
            }
            marca.setModeloList(attachedModeloList);
            em.persist(marca);
            for (Modelo modeloListModelo : marca.getModeloList()) {
                Marca oldFkMarcaOfModeloListModelo = modeloListModelo.getFkMarca();
                modeloListModelo.setFkMarca(marca);
                modeloListModelo = em.merge(modeloListModelo);
                if (oldFkMarcaOfModeloListModelo != null) {
                    oldFkMarcaOfModeloListModelo.getModeloList().remove(modeloListModelo);
                    oldFkMarcaOfModeloListModelo = em.merge(oldFkMarcaOfModeloListModelo);
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

    public void edit(Marca marca) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Marca persistentMarca = em.find(Marca.class, marca.getMarcaId());
            List<Modelo> modeloListOld = persistentMarca.getModeloList();
            List<Modelo> modeloListNew = marca.getModeloList();
            List<Modelo> attachedModeloListNew = new ArrayList<Modelo>();
            for (Modelo modeloListNewModeloToAttach : modeloListNew) {
                modeloListNewModeloToAttach = em.getReference(modeloListNewModeloToAttach.getClass(), modeloListNewModeloToAttach.getModeloId());
                attachedModeloListNew.add(modeloListNewModeloToAttach);
            }
            modeloListNew = attachedModeloListNew;
            marca.setModeloList(modeloListNew);
            marca = em.merge(marca);
            for (Modelo modeloListOldModelo : modeloListOld) {
                if (!modeloListNew.contains(modeloListOldModelo)) {
                    modeloListOldModelo.setFkMarca(null);
                    modeloListOldModelo = em.merge(modeloListOldModelo);
                }
            }
            for (Modelo modeloListNewModelo : modeloListNew) {
                if (!modeloListOld.contains(modeloListNewModelo)) {
                    Marca oldFkMarcaOfModeloListNewModelo = modeloListNewModelo.getFkMarca();
                    modeloListNewModelo.setFkMarca(marca);
                    modeloListNewModelo = em.merge(modeloListNewModelo);
                    if (oldFkMarcaOfModeloListNewModelo != null && !oldFkMarcaOfModeloListNewModelo.equals(marca)) {
                        oldFkMarcaOfModeloListNewModelo.getModeloList().remove(modeloListNewModelo);
                        oldFkMarcaOfModeloListNewModelo = em.merge(oldFkMarcaOfModeloListNewModelo);
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
                Long id = marca.getMarcaId();
                if (findMarca(id) == null) {
                    throw new NonexistentEntityException("The marca with id " + id + " no longer exists.");
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
            Marca marca;
            try {
                marca = em.getReference(Marca.class, id);
                marca.getMarcaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marca with id " + id + " no longer exists.", enfe);
            }
            List<Modelo> modeloList = marca.getModeloList();
            for (Modelo modeloListModelo : modeloList) {
                modeloListModelo.setFkMarca(null);
                modeloListModelo = em.merge(modeloListModelo);
            }
            em.remove(marca);
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

    public List<Marca> findMarcaEntities() {
        return findMarcaEntities(true, -1, -1);
    }

    public List<Marca> findMarcaEntities(int maxResults, int firstResult) {
        return findMarcaEntities(false, maxResults, firstResult);
    }

    private List<Marca> findMarcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marca.class));
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

    public Marca findMarca(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marca> rt = cq.from(Marca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
