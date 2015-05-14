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
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.entidad.Voto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class VotoJpaController implements Serializable {

    public VotoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Voto voto) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario fkUsuarioVotante = voto.getFkUsuarioVotante();
            if (fkUsuarioVotante != null) {
                fkUsuarioVotante = em.getReference(fkUsuarioVotante.getClass(), fkUsuarioVotante.getUsuarioId());
                voto.setFkUsuarioVotante(fkUsuarioVotante);
            }
            Usuario fkUsuarioEvaluado = voto.getFkUsuarioEvaluado();
            if (fkUsuarioEvaluado != null) {
                fkUsuarioEvaluado = em.getReference(fkUsuarioEvaluado.getClass(), fkUsuarioEvaluado.getUsuarioId());
                voto.setFkUsuarioEvaluado(fkUsuarioEvaluado);
            }
            em.persist(voto);
            if (fkUsuarioVotante != null) {
                fkUsuarioVotante.getVotoList().add(voto);
                fkUsuarioVotante = em.merge(fkUsuarioVotante);
            }
            if (fkUsuarioEvaluado != null) {
                fkUsuarioEvaluado.getVotoList().add(voto);
                fkUsuarioEvaluado = em.merge(fkUsuarioEvaluado);
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

    public void edit(Voto voto) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Voto persistentVoto = em.find(Voto.class, voto.getVotoId());
            Usuario fkUsuarioVotanteOld = persistentVoto.getFkUsuarioVotante();
            Usuario fkUsuarioVotanteNew = voto.getFkUsuarioVotante();
            Usuario fkUsuarioEvaluadoOld = persistentVoto.getFkUsuarioEvaluado();
            Usuario fkUsuarioEvaluadoNew = voto.getFkUsuarioEvaluado();
            if (fkUsuarioVotanteNew != null) {
                fkUsuarioVotanteNew = em.getReference(fkUsuarioVotanteNew.getClass(), fkUsuarioVotanteNew.getUsuarioId());
                voto.setFkUsuarioVotante(fkUsuarioVotanteNew);
            }
            if (fkUsuarioEvaluadoNew != null) {
                fkUsuarioEvaluadoNew = em.getReference(fkUsuarioEvaluadoNew.getClass(), fkUsuarioEvaluadoNew.getUsuarioId());
                voto.setFkUsuarioEvaluado(fkUsuarioEvaluadoNew);
            }
            voto = em.merge(voto);
            if (fkUsuarioVotanteOld != null && !fkUsuarioVotanteOld.equals(fkUsuarioVotanteNew)) {
                fkUsuarioVotanteOld.getVotoList().remove(voto);
                fkUsuarioVotanteOld = em.merge(fkUsuarioVotanteOld);
            }
            if (fkUsuarioVotanteNew != null && !fkUsuarioVotanteNew.equals(fkUsuarioVotanteOld)) {
                fkUsuarioVotanteNew.getVotoList().add(voto);
                fkUsuarioVotanteNew = em.merge(fkUsuarioVotanteNew);
            }
            if (fkUsuarioEvaluadoOld != null && !fkUsuarioEvaluadoOld.equals(fkUsuarioEvaluadoNew)) {
                fkUsuarioEvaluadoOld.getVotoList().remove(voto);
                fkUsuarioEvaluadoOld = em.merge(fkUsuarioEvaluadoOld);
            }
            if (fkUsuarioEvaluadoNew != null && !fkUsuarioEvaluadoNew.equals(fkUsuarioEvaluadoOld)) {
                fkUsuarioEvaluadoNew.getVotoList().add(voto);
                fkUsuarioEvaluadoNew = em.merge(fkUsuarioEvaluadoNew);
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
                Long id = voto.getVotoId();
                if (findVoto(id) == null) {
                    throw new NonexistentEntityException("The voto with id " + id + " no longer exists.");
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
            Voto voto;
            try {
                voto = em.getReference(Voto.class, id);
                voto.getVotoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The voto with id " + id + " no longer exists.", enfe);
            }
            Usuario fkUsuarioVotante = voto.getFkUsuarioVotante();
            if (fkUsuarioVotante != null) {
                fkUsuarioVotante.getVotoList().remove(voto);
                fkUsuarioVotante = em.merge(fkUsuarioVotante);
            }
            Usuario fkUsuarioEvaluado = voto.getFkUsuarioEvaluado();
            if (fkUsuarioEvaluado != null) {
                fkUsuarioEvaluado.getVotoList().remove(voto);
                fkUsuarioEvaluado = em.merge(fkUsuarioEvaluado);
            }
            em.remove(voto);
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

    public List<Voto> findVotoEntities() {
        return findVotoEntities(true, -1, -1);
    }

    public List<Voto> findVotoEntities(int maxResults, int firstResult) {
        return findVotoEntities(false, maxResults, firstResult);
    }

    private List<Voto> findVotoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Voto.class));
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

    public Voto findVoto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Voto.class, id);
        } finally {
            em.close();
        }
    }

    public int getVotoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Voto> rt = cq.from(Voto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
