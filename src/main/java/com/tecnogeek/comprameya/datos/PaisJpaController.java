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
import com.tecnogeek.comprameya.entidad.Continente;
import com.tecnogeek.comprameya.entidad.Estado;
import com.tecnogeek.comprameya.entidad.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class PaisJpaController implements Serializable {

    public PaisJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) throws RollbackFailureException, Exception {
        if (pais.getEstadoList() == null) {
            pais.setEstadoList(new ArrayList<Estado>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Continente fkContinente = pais.getFkContinente();
            if (fkContinente != null) {
                fkContinente = em.getReference(fkContinente.getClass(), fkContinente.getContinenteId());
                pais.setFkContinente(fkContinente);
            }
            List<Estado> attachedEstadoList = new ArrayList<Estado>();
            for (Estado estadoListEstadoToAttach : pais.getEstadoList()) {
                estadoListEstadoToAttach = em.getReference(estadoListEstadoToAttach.getClass(), estadoListEstadoToAttach.getEstadoId());
                attachedEstadoList.add(estadoListEstadoToAttach);
            }
            pais.setEstadoList(attachedEstadoList);
            em.persist(pais);
            if (fkContinente != null) {
                fkContinente.getPaisList().add(pais);
                fkContinente = em.merge(fkContinente);
            }
            for (Estado estadoListEstado : pais.getEstadoList()) {
                Pais oldFkPaisOfEstadoListEstado = estadoListEstado.getFkPais();
                estadoListEstado.setFkPais(pais);
                estadoListEstado = em.merge(estadoListEstado);
                if (oldFkPaisOfEstadoListEstado != null) {
                    oldFkPaisOfEstadoListEstado.getEstadoList().remove(estadoListEstado);
                    oldFkPaisOfEstadoListEstado = em.merge(oldFkPaisOfEstadoListEstado);
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

    public void edit(Pais pais) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pais persistentPais = em.find(Pais.class, pais.getPaisId());
            Continente fkContinenteOld = persistentPais.getFkContinente();
            Continente fkContinenteNew = pais.getFkContinente();
            List<Estado> estadoListOld = persistentPais.getEstadoList();
            List<Estado> estadoListNew = pais.getEstadoList();
            if (fkContinenteNew != null) {
                fkContinenteNew = em.getReference(fkContinenteNew.getClass(), fkContinenteNew.getContinenteId());
                pais.setFkContinente(fkContinenteNew);
            }
            List<Estado> attachedEstadoListNew = new ArrayList<Estado>();
            for (Estado estadoListNewEstadoToAttach : estadoListNew) {
                estadoListNewEstadoToAttach = em.getReference(estadoListNewEstadoToAttach.getClass(), estadoListNewEstadoToAttach.getEstadoId());
                attachedEstadoListNew.add(estadoListNewEstadoToAttach);
            }
            estadoListNew = attachedEstadoListNew;
            pais.setEstadoList(estadoListNew);
            pais = em.merge(pais);
            if (fkContinenteOld != null && !fkContinenteOld.equals(fkContinenteNew)) {
                fkContinenteOld.getPaisList().remove(pais);
                fkContinenteOld = em.merge(fkContinenteOld);
            }
            if (fkContinenteNew != null && !fkContinenteNew.equals(fkContinenteOld)) {
                fkContinenteNew.getPaisList().add(pais);
                fkContinenteNew = em.merge(fkContinenteNew);
            }
            for (Estado estadoListOldEstado : estadoListOld) {
                if (!estadoListNew.contains(estadoListOldEstado)) {
                    estadoListOldEstado.setFkPais(null);
                    estadoListOldEstado = em.merge(estadoListOldEstado);
                }
            }
            for (Estado estadoListNewEstado : estadoListNew) {
                if (!estadoListOld.contains(estadoListNewEstado)) {
                    Pais oldFkPaisOfEstadoListNewEstado = estadoListNewEstado.getFkPais();
                    estadoListNewEstado.setFkPais(pais);
                    estadoListNewEstado = em.merge(estadoListNewEstado);
                    if (oldFkPaisOfEstadoListNewEstado != null && !oldFkPaisOfEstadoListNewEstado.equals(pais)) {
                        oldFkPaisOfEstadoListNewEstado.getEstadoList().remove(estadoListNewEstado);
                        oldFkPaisOfEstadoListNewEstado = em.merge(oldFkPaisOfEstadoListNewEstado);
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
                Long id = pais.getPaisId();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getPaisId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            Continente fkContinente = pais.getFkContinente();
            if (fkContinente != null) {
                fkContinente.getPaisList().remove(pais);
                fkContinente = em.merge(fkContinente);
            }
            List<Estado> estadoList = pais.getEstadoList();
            for (Estado estadoListEstado : estadoList) {
                estadoListEstado.setFkPais(null);
                estadoListEstado = em.merge(estadoListEstado);
            }
            em.remove(pais);
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

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
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

    public Pais findPais(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
