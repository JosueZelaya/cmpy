/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Continente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ContinenteJpaController implements Serializable {

    public ContinenteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Continente continente) throws RollbackFailureException, Exception {
        if (continente.getPaisList() == null) {
            continente.setPaisList(new ArrayList<Pais>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Pais> attachedPaisList = new ArrayList<Pais>();
            for (Pais paisListPaisToAttach : continente.getPaisList()) {
                paisListPaisToAttach = em.getReference(paisListPaisToAttach.getClass(), paisListPaisToAttach.getPaisId());
                attachedPaisList.add(paisListPaisToAttach);
            }
            continente.setPaisList(attachedPaisList);
            em.persist(continente);
            for (Pais paisListPais : continente.getPaisList()) {
                Continente oldFkContinenteOfPaisListPais = paisListPais.getFkContinente();
                paisListPais.setFkContinente(continente);
                paisListPais = em.merge(paisListPais);
                if (oldFkContinenteOfPaisListPais != null) {
                    oldFkContinenteOfPaisListPais.getPaisList().remove(paisListPais);
                    oldFkContinenteOfPaisListPais = em.merge(oldFkContinenteOfPaisListPais);
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

    public void edit(Continente continente) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Continente persistentContinente = em.find(Continente.class, continente.getContinenteId());
            List<Pais> paisListOld = persistentContinente.getPaisList();
            List<Pais> paisListNew = continente.getPaisList();
            List<Pais> attachedPaisListNew = new ArrayList<Pais>();
            for (Pais paisListNewPaisToAttach : paisListNew) {
                paisListNewPaisToAttach = em.getReference(paisListNewPaisToAttach.getClass(), paisListNewPaisToAttach.getPaisId());
                attachedPaisListNew.add(paisListNewPaisToAttach);
            }
            paisListNew = attachedPaisListNew;
            continente.setPaisList(paisListNew);
            continente = em.merge(continente);
            for (Pais paisListOldPais : paisListOld) {
                if (!paisListNew.contains(paisListOldPais)) {
                    paisListOldPais.setFkContinente(null);
                    paisListOldPais = em.merge(paisListOldPais);
                }
            }
            for (Pais paisListNewPais : paisListNew) {
                if (!paisListOld.contains(paisListNewPais)) {
                    Continente oldFkContinenteOfPaisListNewPais = paisListNewPais.getFkContinente();
                    paisListNewPais.setFkContinente(continente);
                    paisListNewPais = em.merge(paisListNewPais);
                    if (oldFkContinenteOfPaisListNewPais != null && !oldFkContinenteOfPaisListNewPais.equals(continente)) {
                        oldFkContinenteOfPaisListNewPais.getPaisList().remove(paisListNewPais);
                        oldFkContinenteOfPaisListNewPais = em.merge(oldFkContinenteOfPaisListNewPais);
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
                Long id = continente.getContinenteId();
                if (findContinente(id) == null) {
                    throw new NonexistentEntityException("The continente with id " + id + " no longer exists.");
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
            Continente continente;
            try {
                continente = em.getReference(Continente.class, id);
                continente.getContinenteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The continente with id " + id + " no longer exists.", enfe);
            }
            List<Pais> paisList = continente.getPaisList();
            for (Pais paisListPais : paisList) {
                paisListPais.setFkContinente(null);
                paisListPais = em.merge(paisListPais);
            }
            em.remove(continente);
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

    public List<Continente> findContinenteEntities() {
        return findContinenteEntities(true, -1, -1);
    }

    public List<Continente> findContinenteEntities(int maxResults, int firstResult) {
        return findContinenteEntities(false, maxResults, firstResult);
    }

    private List<Continente> findContinenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Continente.class));
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

    public Continente findContinente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Continente.class, id);
        } finally {
            em.close();
        }
    }

    public int getContinenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Continente> rt = cq.from(Continente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
