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
import com.tecnogeek.comprameya.entidad.Empresa;
import com.tecnogeek.comprameya.entidad.TipoEmpresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class TipoEmpresaJpaController implements Serializable {

    public TipoEmpresaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEmpresa tipoEmpresa) throws RollbackFailureException, Exception {
        if (tipoEmpresa.getEmpresaList() == null) {
            tipoEmpresa.setEmpresaList(new ArrayList<Empresa>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Empresa> attachedEmpresaList = new ArrayList<Empresa>();
            for (Empresa empresaListEmpresaToAttach : tipoEmpresa.getEmpresaList()) {
                empresaListEmpresaToAttach = em.getReference(empresaListEmpresaToAttach.getClass(), empresaListEmpresaToAttach.getEmpresaId());
                attachedEmpresaList.add(empresaListEmpresaToAttach);
            }
            tipoEmpresa.setEmpresaList(attachedEmpresaList);
            em.persist(tipoEmpresa);
            for (Empresa empresaListEmpresa : tipoEmpresa.getEmpresaList()) {
                TipoEmpresa oldFkTipoEmpresaOfEmpresaListEmpresa = empresaListEmpresa.getFkTipoEmpresa();
                empresaListEmpresa.setFkTipoEmpresa(tipoEmpresa);
                empresaListEmpresa = em.merge(empresaListEmpresa);
                if (oldFkTipoEmpresaOfEmpresaListEmpresa != null) {
                    oldFkTipoEmpresaOfEmpresaListEmpresa.getEmpresaList().remove(empresaListEmpresa);
                    oldFkTipoEmpresaOfEmpresaListEmpresa = em.merge(oldFkTipoEmpresaOfEmpresaListEmpresa);
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

    public void edit(TipoEmpresa tipoEmpresa) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoEmpresa persistentTipoEmpresa = em.find(TipoEmpresa.class, tipoEmpresa.getTipoEmpresaId());
            List<Empresa> empresaListOld = persistentTipoEmpresa.getEmpresaList();
            List<Empresa> empresaListNew = tipoEmpresa.getEmpresaList();
            List<Empresa> attachedEmpresaListNew = new ArrayList<Empresa>();
            for (Empresa empresaListNewEmpresaToAttach : empresaListNew) {
                empresaListNewEmpresaToAttach = em.getReference(empresaListNewEmpresaToAttach.getClass(), empresaListNewEmpresaToAttach.getEmpresaId());
                attachedEmpresaListNew.add(empresaListNewEmpresaToAttach);
            }
            empresaListNew = attachedEmpresaListNew;
            tipoEmpresa.setEmpresaList(empresaListNew);
            tipoEmpresa = em.merge(tipoEmpresa);
            for (Empresa empresaListOldEmpresa : empresaListOld) {
                if (!empresaListNew.contains(empresaListOldEmpresa)) {
                    empresaListOldEmpresa.setFkTipoEmpresa(null);
                    empresaListOldEmpresa = em.merge(empresaListOldEmpresa);
                }
            }
            for (Empresa empresaListNewEmpresa : empresaListNew) {
                if (!empresaListOld.contains(empresaListNewEmpresa)) {
                    TipoEmpresa oldFkTipoEmpresaOfEmpresaListNewEmpresa = empresaListNewEmpresa.getFkTipoEmpresa();
                    empresaListNewEmpresa.setFkTipoEmpresa(tipoEmpresa);
                    empresaListNewEmpresa = em.merge(empresaListNewEmpresa);
                    if (oldFkTipoEmpresaOfEmpresaListNewEmpresa != null && !oldFkTipoEmpresaOfEmpresaListNewEmpresa.equals(tipoEmpresa)) {
                        oldFkTipoEmpresaOfEmpresaListNewEmpresa.getEmpresaList().remove(empresaListNewEmpresa);
                        oldFkTipoEmpresaOfEmpresaListNewEmpresa = em.merge(oldFkTipoEmpresaOfEmpresaListNewEmpresa);
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
                Long id = tipoEmpresa.getTipoEmpresaId();
                if (findTipoEmpresa(id) == null) {
                    throw new NonexistentEntityException("The tipoEmpresa with id " + id + " no longer exists.");
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
            TipoEmpresa tipoEmpresa;
            try {
                tipoEmpresa = em.getReference(TipoEmpresa.class, id);
                tipoEmpresa.getTipoEmpresaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEmpresa with id " + id + " no longer exists.", enfe);
            }
            List<Empresa> empresaList = tipoEmpresa.getEmpresaList();
            for (Empresa empresaListEmpresa : empresaList) {
                empresaListEmpresa.setFkTipoEmpresa(null);
                empresaListEmpresa = em.merge(empresaListEmpresa);
            }
            em.remove(tipoEmpresa);
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

    public List<TipoEmpresa> findTipoEmpresaEntities() {
        return findTipoEmpresaEntities(true, -1, -1);
    }

    public List<TipoEmpresa> findTipoEmpresaEntities(int maxResults, int firstResult) {
        return findTipoEmpresaEntities(false, maxResults, firstResult);
    }

    private List<TipoEmpresa> findTipoEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoEmpresa.class));
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

    public TipoEmpresa findTipoEmpresa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoEmpresa> rt = cq.from(TipoEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
