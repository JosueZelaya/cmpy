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
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.TipoRecurso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class TipoRecursoJpaController implements Serializable {

    public TipoRecursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoRecurso tipoRecurso) throws RollbackFailureException, Exception {
        if (tipoRecurso.getRecursoList() == null) {
            tipoRecurso.setRecursoList(new ArrayList<Recurso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Recurso> attachedRecursoList = new ArrayList<Recurso>();
            for (Recurso recursoListRecursoToAttach : tipoRecurso.getRecursoList()) {
                recursoListRecursoToAttach = em.getReference(recursoListRecursoToAttach.getClass(), recursoListRecursoToAttach.getRecursoId());
                attachedRecursoList.add(recursoListRecursoToAttach);
            }
            tipoRecurso.setRecursoList(attachedRecursoList);
            em.persist(tipoRecurso);
            for (Recurso recursoListRecurso : tipoRecurso.getRecursoList()) {
                TipoRecurso oldFkTipoRecursoOfRecursoListRecurso = recursoListRecurso.getFkTipoRecurso();
                recursoListRecurso.setFkTipoRecurso(tipoRecurso);
                recursoListRecurso = em.merge(recursoListRecurso);
                if (oldFkTipoRecursoOfRecursoListRecurso != null) {
                    oldFkTipoRecursoOfRecursoListRecurso.getRecursoList().remove(recursoListRecurso);
                    oldFkTipoRecursoOfRecursoListRecurso = em.merge(oldFkTipoRecursoOfRecursoListRecurso);
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

    public void edit(TipoRecurso tipoRecurso) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoRecurso persistentTipoRecurso = em.find(TipoRecurso.class, tipoRecurso.getTipoRecusoId());
            List<Recurso> recursoListOld = persistentTipoRecurso.getRecursoList();
            List<Recurso> recursoListNew = tipoRecurso.getRecursoList();
            List<Recurso> attachedRecursoListNew = new ArrayList<Recurso>();
            for (Recurso recursoListNewRecursoToAttach : recursoListNew) {
                recursoListNewRecursoToAttach = em.getReference(recursoListNewRecursoToAttach.getClass(), recursoListNewRecursoToAttach.getRecursoId());
                attachedRecursoListNew.add(recursoListNewRecursoToAttach);
            }
            recursoListNew = attachedRecursoListNew;
            tipoRecurso.setRecursoList(recursoListNew);
            tipoRecurso = em.merge(tipoRecurso);
            for (Recurso recursoListOldRecurso : recursoListOld) {
                if (!recursoListNew.contains(recursoListOldRecurso)) {
                    recursoListOldRecurso.setFkTipoRecurso(null);
                    recursoListOldRecurso = em.merge(recursoListOldRecurso);
                }
            }
            for (Recurso recursoListNewRecurso : recursoListNew) {
                if (!recursoListOld.contains(recursoListNewRecurso)) {
                    TipoRecurso oldFkTipoRecursoOfRecursoListNewRecurso = recursoListNewRecurso.getFkTipoRecurso();
                    recursoListNewRecurso.setFkTipoRecurso(tipoRecurso);
                    recursoListNewRecurso = em.merge(recursoListNewRecurso);
                    if (oldFkTipoRecursoOfRecursoListNewRecurso != null && !oldFkTipoRecursoOfRecursoListNewRecurso.equals(tipoRecurso)) {
                        oldFkTipoRecursoOfRecursoListNewRecurso.getRecursoList().remove(recursoListNewRecurso);
                        oldFkTipoRecursoOfRecursoListNewRecurso = em.merge(oldFkTipoRecursoOfRecursoListNewRecurso);
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
                Long id = tipoRecurso.getTipoRecusoId();
                if (findTipoRecurso(id) == null) {
                    throw new NonexistentEntityException("The tipoRecurso with id " + id + " no longer exists.");
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
            TipoRecurso tipoRecurso;
            try {
                tipoRecurso = em.getReference(TipoRecurso.class, id);
                tipoRecurso.getTipoRecusoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoRecurso with id " + id + " no longer exists.", enfe);
            }
            List<Recurso> recursoList = tipoRecurso.getRecursoList();
            for (Recurso recursoListRecurso : recursoList) {
                recursoListRecurso.setFkTipoRecurso(null);
                recursoListRecurso = em.merge(recursoListRecurso);
            }
            em.remove(tipoRecurso);
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

    public List<TipoRecurso> findTipoRecursoEntities() {
        return findTipoRecursoEntities(true, -1, -1);
    }

    public List<TipoRecurso> findTipoRecursoEntities(int maxResults, int firstResult) {
        return findTipoRecursoEntities(false, maxResults, firstResult);
    }

    private List<TipoRecurso> findTipoRecursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoRecurso.class));
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

    public TipoRecurso findTipoRecurso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoRecurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoRecursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoRecurso> rt = cq.from(TipoRecurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
