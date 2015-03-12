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
import com.tecnogeek.comprameya.entidad.Grupo;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.TipoRecurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class RecursoJpaController implements Serializable {

    public RecursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recurso recurso) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grupo fkGrupo = recurso.getFkGrupo();
            if (fkGrupo != null) {
                fkGrupo = em.getReference(fkGrupo.getClass(), fkGrupo.getGrupoId());
                recurso.setFkGrupo(fkGrupo);
            }
            Perfil fkPerfil = recurso.getFkPerfil();
            if (fkPerfil != null) {
                fkPerfil = em.getReference(fkPerfil.getClass(), fkPerfil.getPerfilId());
                recurso.setFkPerfil(fkPerfil);
            }
            TipoRecurso fkTipoRecurso = recurso.getFkTipoRecurso();
            if (fkTipoRecurso != null) {
                fkTipoRecurso = em.getReference(fkTipoRecurso.getClass(), fkTipoRecurso.getTipoRecusoId());
                recurso.setFkTipoRecurso(fkTipoRecurso);
            }
            em.persist(recurso);
            if (fkGrupo != null) {
                fkGrupo.getRecursoList().add(recurso);
                fkGrupo = em.merge(fkGrupo);
            }
            if (fkPerfil != null) {
                fkPerfil.getRecursoList().add(recurso);
                fkPerfil = em.merge(fkPerfil);
            }
            if (fkTipoRecurso != null) {
                fkTipoRecurso.getRecursoList().add(recurso);
                fkTipoRecurso = em.merge(fkTipoRecurso);
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

    public void edit(Recurso recurso) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Recurso persistentRecurso = em.find(Recurso.class, recurso.getRecursoId());
            Grupo fkGrupoOld = persistentRecurso.getFkGrupo();
            Grupo fkGrupoNew = recurso.getFkGrupo();
            Perfil fkPerfilOld = persistentRecurso.getFkPerfil();
            Perfil fkPerfilNew = recurso.getFkPerfil();
            TipoRecurso fkTipoRecursoOld = persistentRecurso.getFkTipoRecurso();
            TipoRecurso fkTipoRecursoNew = recurso.getFkTipoRecurso();
            if (fkGrupoNew != null) {
                fkGrupoNew = em.getReference(fkGrupoNew.getClass(), fkGrupoNew.getGrupoId());
                recurso.setFkGrupo(fkGrupoNew);
            }
            if (fkPerfilNew != null) {
                fkPerfilNew = em.getReference(fkPerfilNew.getClass(), fkPerfilNew.getPerfilId());
                recurso.setFkPerfil(fkPerfilNew);
            }
            if (fkTipoRecursoNew != null) {
                fkTipoRecursoNew = em.getReference(fkTipoRecursoNew.getClass(), fkTipoRecursoNew.getTipoRecusoId());
                recurso.setFkTipoRecurso(fkTipoRecursoNew);
            }
            recurso = em.merge(recurso);
            if (fkGrupoOld != null && !fkGrupoOld.equals(fkGrupoNew)) {
                fkGrupoOld.getRecursoList().remove(recurso);
                fkGrupoOld = em.merge(fkGrupoOld);
            }
            if (fkGrupoNew != null && !fkGrupoNew.equals(fkGrupoOld)) {
                fkGrupoNew.getRecursoList().add(recurso);
                fkGrupoNew = em.merge(fkGrupoNew);
            }
            if (fkPerfilOld != null && !fkPerfilOld.equals(fkPerfilNew)) {
                fkPerfilOld.getRecursoList().remove(recurso);
                fkPerfilOld = em.merge(fkPerfilOld);
            }
            if (fkPerfilNew != null && !fkPerfilNew.equals(fkPerfilOld)) {
                fkPerfilNew.getRecursoList().add(recurso);
                fkPerfilNew = em.merge(fkPerfilNew);
            }
            if (fkTipoRecursoOld != null && !fkTipoRecursoOld.equals(fkTipoRecursoNew)) {
                fkTipoRecursoOld.getRecursoList().remove(recurso);
                fkTipoRecursoOld = em.merge(fkTipoRecursoOld);
            }
            if (fkTipoRecursoNew != null && !fkTipoRecursoNew.equals(fkTipoRecursoOld)) {
                fkTipoRecursoNew.getRecursoList().add(recurso);
                fkTipoRecursoNew = em.merge(fkTipoRecursoNew);
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
                Long id = recurso.getRecursoId();
                if (findRecurso(id) == null) {
                    throw new NonexistentEntityException("The recurso with id " + id + " no longer exists.");
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
            Recurso recurso;
            try {
                recurso = em.getReference(Recurso.class, id);
                recurso.getRecursoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recurso with id " + id + " no longer exists.", enfe);
            }
            Grupo fkGrupo = recurso.getFkGrupo();
            if (fkGrupo != null) {
                fkGrupo.getRecursoList().remove(recurso);
                fkGrupo = em.merge(fkGrupo);
            }
            Perfil fkPerfil = recurso.getFkPerfil();
            if (fkPerfil != null) {
                fkPerfil.getRecursoList().remove(recurso);
                fkPerfil = em.merge(fkPerfil);
            }
            TipoRecurso fkTipoRecurso = recurso.getFkTipoRecurso();
            if (fkTipoRecurso != null) {
                fkTipoRecurso.getRecursoList().remove(recurso);
                fkTipoRecurso = em.merge(fkTipoRecurso);
            }
            em.remove(recurso);
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

    public List<Recurso> findRecursoEntities() {
        return findRecursoEntities(true, -1, -1);
    }

    public List<Recurso> findRecursoEntities(int maxResults, int firstResult) {
        return findRecursoEntities(false, maxResults, firstResult);
    }

    private List<Recurso> findRecursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recurso.class));
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

    public Recurso findRecurso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recurso> rt = cq.from(Recurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
