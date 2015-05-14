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
import java.util.ArrayList;
import java.util.List;
import com.tecnogeek.comprameya.entidad.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) throws RollbackFailureException, Exception {
        if (perfil.getRecursoList() == null) {
            perfil.setRecursoList(new ArrayList<Recurso>());
        }
        if (perfil.getUsuarioList() == null) {
            perfil.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grupo fkGrupo = perfil.getFkGrupo();
            if (fkGrupo != null) {
                fkGrupo = em.getReference(fkGrupo.getClass(), fkGrupo.getGrupoId());
                perfil.setFkGrupo(fkGrupo);
            }
            List<Recurso> attachedRecursoList = new ArrayList<Recurso>();
            for (Recurso recursoListRecursoToAttach : perfil.getRecursoList()) {
                recursoListRecursoToAttach = em.getReference(recursoListRecursoToAttach.getClass(), recursoListRecursoToAttach.getRecursoId());
                attachedRecursoList.add(recursoListRecursoToAttach);
            }
            perfil.setRecursoList(attachedRecursoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : perfil.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuarioId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            perfil.setUsuarioList(attachedUsuarioList);
            em.persist(perfil);
            if (fkGrupo != null) {
                fkGrupo.getPerfilList().add(perfil);
                fkGrupo = em.merge(fkGrupo);
            }
            for (Recurso recursoListRecurso : perfil.getRecursoList()) {
                Perfil oldFkPerfilOfRecursoListRecurso = recursoListRecurso.getFkPerfil();
                recursoListRecurso.setFkPerfil(perfil);
                recursoListRecurso = em.merge(recursoListRecurso);
                if (oldFkPerfilOfRecursoListRecurso != null) {
                    oldFkPerfilOfRecursoListRecurso.getRecursoList().remove(recursoListRecurso);
                    oldFkPerfilOfRecursoListRecurso = em.merge(oldFkPerfilOfRecursoListRecurso);
                }
            }
            for (Usuario usuarioListUsuario : perfil.getUsuarioList()) {
                Perfil oldFkPerfilOfUsuarioListUsuario = usuarioListUsuario.getFkPerfil();
                usuarioListUsuario.setFkPerfil(perfil);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldFkPerfilOfUsuarioListUsuario != null) {
                    oldFkPerfilOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldFkPerfilOfUsuarioListUsuario = em.merge(oldFkPerfilOfUsuarioListUsuario);
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

    public void edit(Perfil perfil) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getPerfilId());
            Grupo fkGrupoOld = persistentPerfil.getFkGrupo();
            Grupo fkGrupoNew = perfil.getFkGrupo();
            List<Recurso> recursoListOld = persistentPerfil.getRecursoList();
            List<Recurso> recursoListNew = perfil.getRecursoList();
            List<Usuario> usuarioListOld = persistentPerfil.getUsuarioList();
            List<Usuario> usuarioListNew = perfil.getUsuarioList();
            if (fkGrupoNew != null) {
                fkGrupoNew = em.getReference(fkGrupoNew.getClass(), fkGrupoNew.getGrupoId());
                perfil.setFkGrupo(fkGrupoNew);
            }
            List<Recurso> attachedRecursoListNew = new ArrayList<Recurso>();
            for (Recurso recursoListNewRecursoToAttach : recursoListNew) {
                recursoListNewRecursoToAttach = em.getReference(recursoListNewRecursoToAttach.getClass(), recursoListNewRecursoToAttach.getRecursoId());
                attachedRecursoListNew.add(recursoListNewRecursoToAttach);
            }
            recursoListNew = attachedRecursoListNew;
            perfil.setRecursoList(recursoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuarioId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            perfil.setUsuarioList(usuarioListNew);
            perfil = em.merge(perfil);
            if (fkGrupoOld != null && !fkGrupoOld.equals(fkGrupoNew)) {
                fkGrupoOld.getPerfilList().remove(perfil);
                fkGrupoOld = em.merge(fkGrupoOld);
            }
            if (fkGrupoNew != null && !fkGrupoNew.equals(fkGrupoOld)) {
                fkGrupoNew.getPerfilList().add(perfil);
                fkGrupoNew = em.merge(fkGrupoNew);
            }
            for (Recurso recursoListOldRecurso : recursoListOld) {
                if (!recursoListNew.contains(recursoListOldRecurso)) {
                    recursoListOldRecurso.setFkPerfil(null);
                    recursoListOldRecurso = em.merge(recursoListOldRecurso);
                }
            }
            for (Recurso recursoListNewRecurso : recursoListNew) {
                if (!recursoListOld.contains(recursoListNewRecurso)) {
                    Perfil oldFkPerfilOfRecursoListNewRecurso = recursoListNewRecurso.getFkPerfil();
                    recursoListNewRecurso.setFkPerfil(perfil);
                    recursoListNewRecurso = em.merge(recursoListNewRecurso);
                    if (oldFkPerfilOfRecursoListNewRecurso != null && !oldFkPerfilOfRecursoListNewRecurso.equals(perfil)) {
                        oldFkPerfilOfRecursoListNewRecurso.getRecursoList().remove(recursoListNewRecurso);
                        oldFkPerfilOfRecursoListNewRecurso = em.merge(oldFkPerfilOfRecursoListNewRecurso);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setFkPerfil(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Perfil oldFkPerfilOfUsuarioListNewUsuario = usuarioListNewUsuario.getFkPerfil();
                    usuarioListNewUsuario.setFkPerfil(perfil);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldFkPerfilOfUsuarioListNewUsuario != null && !oldFkPerfilOfUsuarioListNewUsuario.equals(perfil)) {
                        oldFkPerfilOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldFkPerfilOfUsuarioListNewUsuario = em.merge(oldFkPerfilOfUsuarioListNewUsuario);
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
                Long id = perfil.getPerfilId();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getPerfilId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            Grupo fkGrupo = perfil.getFkGrupo();
            if (fkGrupo != null) {
                fkGrupo.getPerfilList().remove(perfil);
                fkGrupo = em.merge(fkGrupo);
            }
            List<Recurso> recursoList = perfil.getRecursoList();
            for (Recurso recursoListRecurso : recursoList) {
                recursoListRecurso.setFkPerfil(null);
                recursoListRecurso = em.merge(recursoListRecurso);
            }
            List<Usuario> usuarioList = perfil.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setFkPerfil(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(perfil);
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

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
