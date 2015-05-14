/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Ranking;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class RankingJpaController implements Serializable {

    public RankingJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ranking ranking) throws RollbackFailureException, Exception {
        if (ranking.getUsuarioList() == null) {
            ranking.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : ranking.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuarioId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            ranking.setUsuarioList(attachedUsuarioList);
            em.persist(ranking);
            for (Usuario usuarioListUsuario : ranking.getUsuarioList()) {
                Ranking oldFkRankingOfUsuarioListUsuario = usuarioListUsuario.getFkRanking();
                usuarioListUsuario.setFkRanking(ranking);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldFkRankingOfUsuarioListUsuario != null) {
                    oldFkRankingOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldFkRankingOfUsuarioListUsuario = em.merge(oldFkRankingOfUsuarioListUsuario);
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

    public void edit(Ranking ranking) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ranking persistentRanking = em.find(Ranking.class, ranking.getRankingId());
            List<Usuario> usuarioListOld = persistentRanking.getUsuarioList();
            List<Usuario> usuarioListNew = ranking.getUsuarioList();
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuarioId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            ranking.setUsuarioList(usuarioListNew);
            ranking = em.merge(ranking);
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setFkRanking(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Ranking oldFkRankingOfUsuarioListNewUsuario = usuarioListNewUsuario.getFkRanking();
                    usuarioListNewUsuario.setFkRanking(ranking);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldFkRankingOfUsuarioListNewUsuario != null && !oldFkRankingOfUsuarioListNewUsuario.equals(ranking)) {
                        oldFkRankingOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldFkRankingOfUsuarioListNewUsuario = em.merge(oldFkRankingOfUsuarioListNewUsuario);
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
                Long id = ranking.getRankingId();
                if (findRanking(id) == null) {
                    throw new NonexistentEntityException("The ranking with id " + id + " no longer exists.");
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
            Ranking ranking;
            try {
                ranking = em.getReference(Ranking.class, id);
                ranking.getRankingId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ranking with id " + id + " no longer exists.", enfe);
            }
            List<Usuario> usuarioList = ranking.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setFkRanking(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(ranking);
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

    public List<Ranking> findRankingEntities() {
        return findRankingEntities(true, -1, -1);
    }

    public List<Ranking> findRankingEntities(int maxResults, int firstResult) {
        return findRankingEntities(false, maxResults, firstResult);
    }

    private List<Ranking> findRankingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ranking.class));
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

    public Ranking findRanking(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ranking.class, id);
        } finally {
            em.close();
        }
    }

    public int getRankingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ranking> rt = cq.from(Ranking.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
