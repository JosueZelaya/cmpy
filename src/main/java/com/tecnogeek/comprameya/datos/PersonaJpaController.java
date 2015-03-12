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
import com.tecnogeek.comprameya.entidad.Ubicacion;
import com.tecnogeek.comprameya.entidad.Empleado;
import com.tecnogeek.comprameya.entidad.Persona;
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
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws RollbackFailureException, Exception {
        if (persona.getEmpleadoList() == null) {
            persona.setEmpleadoList(new ArrayList<Empleado>());
        }
        if (persona.getUsuarioList() == null) {
            persona.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ubicacion fkUbicacion = persona.getFkUbicacion();
            if (fkUbicacion != null) {
                fkUbicacion = em.getReference(fkUbicacion.getClass(), fkUbicacion.getUbicacionId());
                persona.setFkUbicacion(fkUbicacion);
            }
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : persona.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getEmpleadoId());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            persona.setEmpleadoList(attachedEmpleadoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : persona.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuarioId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            persona.setUsuarioList(attachedUsuarioList);
            em.persist(persona);
            if (fkUbicacion != null) {
                fkUbicacion.getPersonaList().add(persona);
                fkUbicacion = em.merge(fkUbicacion);
            }
            for (Empleado empleadoListEmpleado : persona.getEmpleadoList()) {
                Persona oldFkPersonaOfEmpleadoListEmpleado = empleadoListEmpleado.getFkPersona();
                empleadoListEmpleado.setFkPersona(persona);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldFkPersonaOfEmpleadoListEmpleado != null) {
                    oldFkPersonaOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldFkPersonaOfEmpleadoListEmpleado = em.merge(oldFkPersonaOfEmpleadoListEmpleado);
                }
            }
            for (Usuario usuarioListUsuario : persona.getUsuarioList()) {
                Persona oldFkPersonaOfUsuarioListUsuario = usuarioListUsuario.getFkPersona();
                usuarioListUsuario.setFkPersona(persona);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldFkPersonaOfUsuarioListUsuario != null) {
                    oldFkPersonaOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldFkPersonaOfUsuarioListUsuario = em.merge(oldFkPersonaOfUsuarioListUsuario);
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

    public void edit(Persona persona) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Persona persistentPersona = em.find(Persona.class, persona.getPersonaId());
            Ubicacion fkUbicacionOld = persistentPersona.getFkUbicacion();
            Ubicacion fkUbicacionNew = persona.getFkUbicacion();
            List<Empleado> empleadoListOld = persistentPersona.getEmpleadoList();
            List<Empleado> empleadoListNew = persona.getEmpleadoList();
            List<Usuario> usuarioListOld = persistentPersona.getUsuarioList();
            List<Usuario> usuarioListNew = persona.getUsuarioList();
            if (fkUbicacionNew != null) {
                fkUbicacionNew = em.getReference(fkUbicacionNew.getClass(), fkUbicacionNew.getUbicacionId());
                persona.setFkUbicacion(fkUbicacionNew);
            }
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getEmpleadoId());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            persona.setEmpleadoList(empleadoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuarioId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            persona.setUsuarioList(usuarioListNew);
            persona = em.merge(persona);
            if (fkUbicacionOld != null && !fkUbicacionOld.equals(fkUbicacionNew)) {
                fkUbicacionOld.getPersonaList().remove(persona);
                fkUbicacionOld = em.merge(fkUbicacionOld);
            }
            if (fkUbicacionNew != null && !fkUbicacionNew.equals(fkUbicacionOld)) {
                fkUbicacionNew.getPersonaList().add(persona);
                fkUbicacionNew = em.merge(fkUbicacionNew);
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setFkPersona(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Persona oldFkPersonaOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getFkPersona();
                    empleadoListNewEmpleado.setFkPersona(persona);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldFkPersonaOfEmpleadoListNewEmpleado != null && !oldFkPersonaOfEmpleadoListNewEmpleado.equals(persona)) {
                        oldFkPersonaOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldFkPersonaOfEmpleadoListNewEmpleado = em.merge(oldFkPersonaOfEmpleadoListNewEmpleado);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setFkPersona(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Persona oldFkPersonaOfUsuarioListNewUsuario = usuarioListNewUsuario.getFkPersona();
                    usuarioListNewUsuario.setFkPersona(persona);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldFkPersonaOfUsuarioListNewUsuario != null && !oldFkPersonaOfUsuarioListNewUsuario.equals(persona)) {
                        oldFkPersonaOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldFkPersonaOfUsuarioListNewUsuario = em.merge(oldFkPersonaOfUsuarioListNewUsuario);
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
                Long id = persona.getPersonaId();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getPersonaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            Ubicacion fkUbicacion = persona.getFkUbicacion();
            if (fkUbicacion != null) {
                fkUbicacion.getPersonaList().remove(persona);
                fkUbicacion = em.merge(fkUbicacion);
            }
            List<Empleado> empleadoList = persona.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setFkPersona(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
            }
            List<Usuario> usuarioList = persona.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setFkPersona(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(persona);
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

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
