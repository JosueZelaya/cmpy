/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Grupo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Recurso;
import java.util.ArrayList;
import java.util.List;
import com.tecnogeek.comprameya.entidad.Perfil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) throws RollbackFailureException, Exception {
        if (grupo.getRecursoList() == null) {
            grupo.setRecursoList(new ArrayList<Recurso>());
        }
        if (grupo.getPerfilList() == null) {
            grupo.setPerfilList(new ArrayList<Perfil>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Recurso> attachedRecursoList = new ArrayList<Recurso>();
            for (Recurso recursoListRecursoToAttach : grupo.getRecursoList()) {
                recursoListRecursoToAttach = em.getReference(recursoListRecursoToAttach.getClass(), recursoListRecursoToAttach.getRecursoId());
                attachedRecursoList.add(recursoListRecursoToAttach);
            }
            grupo.setRecursoList(attachedRecursoList);
            List<Perfil> attachedPerfilList = new ArrayList<Perfil>();
            for (Perfil perfilListPerfilToAttach : grupo.getPerfilList()) {
                perfilListPerfilToAttach = em.getReference(perfilListPerfilToAttach.getClass(), perfilListPerfilToAttach.getPerfilId());
                attachedPerfilList.add(perfilListPerfilToAttach);
            }
            grupo.setPerfilList(attachedPerfilList);
            em.persist(grupo);
            for (Recurso recursoListRecurso : grupo.getRecursoList()) {
                Grupo oldFkGrupoOfRecursoListRecurso = recursoListRecurso.getFkGrupo();
                recursoListRecurso.setFkGrupo(grupo);
                recursoListRecurso = em.merge(recursoListRecurso);
                if (oldFkGrupoOfRecursoListRecurso != null) {
                    oldFkGrupoOfRecursoListRecurso.getRecursoList().remove(recursoListRecurso);
                    oldFkGrupoOfRecursoListRecurso = em.merge(oldFkGrupoOfRecursoListRecurso);
                }
            }
            for (Perfil perfilListPerfil : grupo.getPerfilList()) {
                Grupo oldFkGrupoOfPerfilListPerfil = perfilListPerfil.getFkGrupo();
                perfilListPerfil.setFkGrupo(grupo);
                perfilListPerfil = em.merge(perfilListPerfil);
                if (oldFkGrupoOfPerfilListPerfil != null) {
                    oldFkGrupoOfPerfilListPerfil.getPerfilList().remove(perfilListPerfil);
                    oldFkGrupoOfPerfilListPerfil = em.merge(oldFkGrupoOfPerfilListPerfil);
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

    public void edit(Grupo grupo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getGrupoId());
            List<Recurso> recursoListOld = persistentGrupo.getRecursoList();
            List<Recurso> recursoListNew = grupo.getRecursoList();
            List<Perfil> perfilListOld = persistentGrupo.getPerfilList();
            List<Perfil> perfilListNew = grupo.getPerfilList();
            List<Recurso> attachedRecursoListNew = new ArrayList<Recurso>();
            for (Recurso recursoListNewRecursoToAttach : recursoListNew) {
                recursoListNewRecursoToAttach = em.getReference(recursoListNewRecursoToAttach.getClass(), recursoListNewRecursoToAttach.getRecursoId());
                attachedRecursoListNew.add(recursoListNewRecursoToAttach);
            }
            recursoListNew = attachedRecursoListNew;
            grupo.setRecursoList(recursoListNew);
            List<Perfil> attachedPerfilListNew = new ArrayList<Perfil>();
            for (Perfil perfilListNewPerfilToAttach : perfilListNew) {
                perfilListNewPerfilToAttach = em.getReference(perfilListNewPerfilToAttach.getClass(), perfilListNewPerfilToAttach.getPerfilId());
                attachedPerfilListNew.add(perfilListNewPerfilToAttach);
            }
            perfilListNew = attachedPerfilListNew;
            grupo.setPerfilList(perfilListNew);
            grupo = em.merge(grupo);
            for (Recurso recursoListOldRecurso : recursoListOld) {
                if (!recursoListNew.contains(recursoListOldRecurso)) {
                    recursoListOldRecurso.setFkGrupo(null);
                    recursoListOldRecurso = em.merge(recursoListOldRecurso);
                }
            }
            for (Recurso recursoListNewRecurso : recursoListNew) {
                if (!recursoListOld.contains(recursoListNewRecurso)) {
                    Grupo oldFkGrupoOfRecursoListNewRecurso = recursoListNewRecurso.getFkGrupo();
                    recursoListNewRecurso.setFkGrupo(grupo);
                    recursoListNewRecurso = em.merge(recursoListNewRecurso);
                    if (oldFkGrupoOfRecursoListNewRecurso != null && !oldFkGrupoOfRecursoListNewRecurso.equals(grupo)) {
                        oldFkGrupoOfRecursoListNewRecurso.getRecursoList().remove(recursoListNewRecurso);
                        oldFkGrupoOfRecursoListNewRecurso = em.merge(oldFkGrupoOfRecursoListNewRecurso);
                    }
                }
            }
            for (Perfil perfilListOldPerfil : perfilListOld) {
                if (!perfilListNew.contains(perfilListOldPerfil)) {
                    perfilListOldPerfil.setFkGrupo(null);
                    perfilListOldPerfil = em.merge(perfilListOldPerfil);
                }
            }
            for (Perfil perfilListNewPerfil : perfilListNew) {
                if (!perfilListOld.contains(perfilListNewPerfil)) {
                    Grupo oldFkGrupoOfPerfilListNewPerfil = perfilListNewPerfil.getFkGrupo();
                    perfilListNewPerfil.setFkGrupo(grupo);
                    perfilListNewPerfil = em.merge(perfilListNewPerfil);
                    if (oldFkGrupoOfPerfilListNewPerfil != null && !oldFkGrupoOfPerfilListNewPerfil.equals(grupo)) {
                        oldFkGrupoOfPerfilListNewPerfil.getPerfilList().remove(perfilListNewPerfil);
                        oldFkGrupoOfPerfilListNewPerfil = em.merge(oldFkGrupoOfPerfilListNewPerfil);
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
                Long id = grupo.getGrupoId();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getGrupoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            List<Recurso> recursoList = grupo.getRecursoList();
            for (Recurso recursoListRecurso : recursoList) {
                recursoListRecurso.setFkGrupo(null);
                recursoListRecurso = em.merge(recursoListRecurso);
            }
            List<Perfil> perfilList = grupo.getPerfilList();
            for (Perfil perfilListPerfil : perfilList) {
                perfilListPerfil.setFkGrupo(null);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            em.remove(grupo);
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

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
