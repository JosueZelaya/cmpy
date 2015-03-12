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
import com.tecnogeek.comprameya.entidad.Pais;
import com.tecnogeek.comprameya.entidad.Ciudad;
import com.tecnogeek.comprameya.entidad.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class EstadoJpaController implements Serializable {

    public EstadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) throws RollbackFailureException, Exception {
        if (estado.getCiudadList() == null) {
            estado.setCiudadList(new ArrayList<Ciudad>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pais fkPais = estado.getFkPais();
            if (fkPais != null) {
                fkPais = em.getReference(fkPais.getClass(), fkPais.getPaisId());
                estado.setFkPais(fkPais);
            }
            List<Ciudad> attachedCiudadList = new ArrayList<Ciudad>();
            for (Ciudad ciudadListCiudadToAttach : estado.getCiudadList()) {
                ciudadListCiudadToAttach = em.getReference(ciudadListCiudadToAttach.getClass(), ciudadListCiudadToAttach.getCiudadId());
                attachedCiudadList.add(ciudadListCiudadToAttach);
            }
            estado.setCiudadList(attachedCiudadList);
            em.persist(estado);
            if (fkPais != null) {
                fkPais.getEstadoList().add(estado);
                fkPais = em.merge(fkPais);
            }
            for (Ciudad ciudadListCiudad : estado.getCiudadList()) {
                Estado oldFkEstadoOfCiudadListCiudad = ciudadListCiudad.getFkEstado();
                ciudadListCiudad.setFkEstado(estado);
                ciudadListCiudad = em.merge(ciudadListCiudad);
                if (oldFkEstadoOfCiudadListCiudad != null) {
                    oldFkEstadoOfCiudadListCiudad.getCiudadList().remove(ciudadListCiudad);
                    oldFkEstadoOfCiudadListCiudad = em.merge(oldFkEstadoOfCiudadListCiudad);
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

    public void edit(Estado estado) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado persistentEstado = em.find(Estado.class, estado.getEstadoId());
            Pais fkPaisOld = persistentEstado.getFkPais();
            Pais fkPaisNew = estado.getFkPais();
            List<Ciudad> ciudadListOld = persistentEstado.getCiudadList();
            List<Ciudad> ciudadListNew = estado.getCiudadList();
            if (fkPaisNew != null) {
                fkPaisNew = em.getReference(fkPaisNew.getClass(), fkPaisNew.getPaisId());
                estado.setFkPais(fkPaisNew);
            }
            List<Ciudad> attachedCiudadListNew = new ArrayList<Ciudad>();
            for (Ciudad ciudadListNewCiudadToAttach : ciudadListNew) {
                ciudadListNewCiudadToAttach = em.getReference(ciudadListNewCiudadToAttach.getClass(), ciudadListNewCiudadToAttach.getCiudadId());
                attachedCiudadListNew.add(ciudadListNewCiudadToAttach);
            }
            ciudadListNew = attachedCiudadListNew;
            estado.setCiudadList(ciudadListNew);
            estado = em.merge(estado);
            if (fkPaisOld != null && !fkPaisOld.equals(fkPaisNew)) {
                fkPaisOld.getEstadoList().remove(estado);
                fkPaisOld = em.merge(fkPaisOld);
            }
            if (fkPaisNew != null && !fkPaisNew.equals(fkPaisOld)) {
                fkPaisNew.getEstadoList().add(estado);
                fkPaisNew = em.merge(fkPaisNew);
            }
            for (Ciudad ciudadListOldCiudad : ciudadListOld) {
                if (!ciudadListNew.contains(ciudadListOldCiudad)) {
                    ciudadListOldCiudad.setFkEstado(null);
                    ciudadListOldCiudad = em.merge(ciudadListOldCiudad);
                }
            }
            for (Ciudad ciudadListNewCiudad : ciudadListNew) {
                if (!ciudadListOld.contains(ciudadListNewCiudad)) {
                    Estado oldFkEstadoOfCiudadListNewCiudad = ciudadListNewCiudad.getFkEstado();
                    ciudadListNewCiudad.setFkEstado(estado);
                    ciudadListNewCiudad = em.merge(ciudadListNewCiudad);
                    if (oldFkEstadoOfCiudadListNewCiudad != null && !oldFkEstadoOfCiudadListNewCiudad.equals(estado)) {
                        oldFkEstadoOfCiudadListNewCiudad.getCiudadList().remove(ciudadListNewCiudad);
                        oldFkEstadoOfCiudadListNewCiudad = em.merge(oldFkEstadoOfCiudadListNewCiudad);
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
                Long id = estado.getEstadoId();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getEstadoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            Pais fkPais = estado.getFkPais();
            if (fkPais != null) {
                fkPais.getEstadoList().remove(estado);
                fkPais = em.merge(fkPais);
            }
            List<Ciudad> ciudadList = estado.getCiudadList();
            for (Ciudad ciudadListCiudad : ciudadList) {
                ciudadListCiudad.setFkEstado(null);
                ciudadListCiudad = em.merge(ciudadListCiudad);
            }
            em.remove(estado);
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

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
