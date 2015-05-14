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
import com.tecnogeek.comprameya.entidad.Ciudad;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Persona;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class UbicacionJpaController implements Serializable {

    public UbicacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ubicacion ubicacion) throws RollbackFailureException, Exception {
        if (ubicacion.getPersonaList() == null) {
            ubicacion.setPersonaList(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ciudad fkCiudad = ubicacion.getFkCiudad();
            if (fkCiudad != null) {
                fkCiudad = em.getReference(fkCiudad.getClass(), fkCiudad.getCiudadId());
                ubicacion.setFkCiudad(fkCiudad);
            }
            Publicacion fkPublicacion = ubicacion.getFkPublicacion();
            if (fkPublicacion != null) {
                fkPublicacion = em.getReference(fkPublicacion.getClass(), fkPublicacion.getPublicacionId());
                ubicacion.setFkPublicacion(fkPublicacion);
            }
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : ubicacion.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getPersonaId());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            ubicacion.setPersonaList(attachedPersonaList);
            em.persist(ubicacion);
            if (fkCiudad != null) {
                fkCiudad.getUbicacionList().add(ubicacion);
                fkCiudad = em.merge(fkCiudad);
            }
            if (fkPublicacion != null) {
                fkPublicacion.getUbicacionList().add(ubicacion);
                fkPublicacion = em.merge(fkPublicacion);
            }
            for (Persona personaListPersona : ubicacion.getPersonaList()) {
                Ubicacion oldFkUbicacionOfPersonaListPersona = personaListPersona.getFkUbicacion();
                personaListPersona.setFkUbicacion(ubicacion);
                personaListPersona = em.merge(personaListPersona);
                if (oldFkUbicacionOfPersonaListPersona != null) {
                    oldFkUbicacionOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldFkUbicacionOfPersonaListPersona = em.merge(oldFkUbicacionOfPersonaListPersona);
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

    public void edit(Ubicacion ubicacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ubicacion persistentUbicacion = em.find(Ubicacion.class, ubicacion.getUbicacionId());
            Ciudad fkCiudadOld = persistentUbicacion.getFkCiudad();
            Ciudad fkCiudadNew = ubicacion.getFkCiudad();
            Publicacion fkPublicacionOld = persistentUbicacion.getFkPublicacion();
            Publicacion fkPublicacionNew = ubicacion.getFkPublicacion();
            List<Persona> personaListOld = persistentUbicacion.getPersonaList();
            List<Persona> personaListNew = ubicacion.getPersonaList();
            if (fkCiudadNew != null) {
                fkCiudadNew = em.getReference(fkCiudadNew.getClass(), fkCiudadNew.getCiudadId());
                ubicacion.setFkCiudad(fkCiudadNew);
            }
            if (fkPublicacionNew != null) {
                fkPublicacionNew = em.getReference(fkPublicacionNew.getClass(), fkPublicacionNew.getPublicacionId());
                ubicacion.setFkPublicacion(fkPublicacionNew);
            }
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getPersonaId());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            ubicacion.setPersonaList(personaListNew);
            ubicacion = em.merge(ubicacion);
            if (fkCiudadOld != null && !fkCiudadOld.equals(fkCiudadNew)) {
                fkCiudadOld.getUbicacionList().remove(ubicacion);
                fkCiudadOld = em.merge(fkCiudadOld);
            }
            if (fkCiudadNew != null && !fkCiudadNew.equals(fkCiudadOld)) {
                fkCiudadNew.getUbicacionList().add(ubicacion);
                fkCiudadNew = em.merge(fkCiudadNew);
            }
            if (fkPublicacionOld != null && !fkPublicacionOld.equals(fkPublicacionNew)) {
                fkPublicacionOld.getUbicacionList().remove(ubicacion);
                fkPublicacionOld = em.merge(fkPublicacionOld);
            }
            if (fkPublicacionNew != null && !fkPublicacionNew.equals(fkPublicacionOld)) {
                fkPublicacionNew.getUbicacionList().add(ubicacion);
                fkPublicacionNew = em.merge(fkPublicacionNew);
            }
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    personaListOldPersona.setFkUbicacion(null);
                    personaListOldPersona = em.merge(personaListOldPersona);
                }
            }
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    Ubicacion oldFkUbicacionOfPersonaListNewPersona = personaListNewPersona.getFkUbicacion();
                    personaListNewPersona.setFkUbicacion(ubicacion);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldFkUbicacionOfPersonaListNewPersona != null && !oldFkUbicacionOfPersonaListNewPersona.equals(ubicacion)) {
                        oldFkUbicacionOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldFkUbicacionOfPersonaListNewPersona = em.merge(oldFkUbicacionOfPersonaListNewPersona);
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
                Long id = ubicacion.getUbicacionId();
                if (findUbicacion(id) == null) {
                    throw new NonexistentEntityException("The ubicacion with id " + id + " no longer exists.");
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
            Ubicacion ubicacion;
            try {
                ubicacion = em.getReference(Ubicacion.class, id);
                ubicacion.getUbicacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ubicacion with id " + id + " no longer exists.", enfe);
            }
            Ciudad fkCiudad = ubicacion.getFkCiudad();
            if (fkCiudad != null) {
                fkCiudad.getUbicacionList().remove(ubicacion);
                fkCiudad = em.merge(fkCiudad);
            }
            Publicacion fkPublicacion = ubicacion.getFkPublicacion();
            if (fkPublicacion != null) {
                fkPublicacion.getUbicacionList().remove(ubicacion);
                fkPublicacion = em.merge(fkPublicacion);
            }
            List<Persona> personaList = ubicacion.getPersonaList();
            for (Persona personaListPersona : personaList) {
                personaListPersona.setFkUbicacion(null);
                personaListPersona = em.merge(personaListPersona);
            }
            em.remove(ubicacion);
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

    public List<Ubicacion> findUbicacionEntities() {
        return findUbicacionEntities(true, -1, -1);
    }

    public List<Ubicacion> findUbicacionEntities(int maxResults, int firstResult) {
        return findUbicacionEntities(false, maxResults, firstResult);
    }

    private List<Ubicacion> findUbicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ubicacion.class));
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

    public Ubicacion findUbicacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ubicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUbicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ubicacion> rt = cq.from(Ubicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
