/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Ciudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Estado;
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
public class CiudadJpaController implements Serializable {

    public CiudadJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudad ciudad) throws RollbackFailureException, Exception {
        if (ciudad.getUbicacionList() == null) {
            ciudad.setUbicacionList(new ArrayList<Ubicacion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado fkEstado = ciudad.getFkEstado();
            if (fkEstado != null) {
                fkEstado = em.getReference(fkEstado.getClass(), fkEstado.getEstadoId());
                ciudad.setFkEstado(fkEstado);
            }
            List<Ubicacion> attachedUbicacionList = new ArrayList<Ubicacion>();
            for (Ubicacion ubicacionListUbicacionToAttach : ciudad.getUbicacionList()) {
                ubicacionListUbicacionToAttach = em.getReference(ubicacionListUbicacionToAttach.getClass(), ubicacionListUbicacionToAttach.getUbicacionId());
                attachedUbicacionList.add(ubicacionListUbicacionToAttach);
            }
            ciudad.setUbicacionList(attachedUbicacionList);
            em.persist(ciudad);
            if (fkEstado != null) {
                fkEstado.getCiudadList().add(ciudad);
                fkEstado = em.merge(fkEstado);
            }
            for (Ubicacion ubicacionListUbicacion : ciudad.getUbicacionList()) {
                Ciudad oldFkCiudadOfUbicacionListUbicacion = ubicacionListUbicacion.getFkCiudad();
                ubicacionListUbicacion.setFkCiudad(ciudad);
                ubicacionListUbicacion = em.merge(ubicacionListUbicacion);
                if (oldFkCiudadOfUbicacionListUbicacion != null) {
                    oldFkCiudadOfUbicacionListUbicacion.getUbicacionList().remove(ubicacionListUbicacion);
                    oldFkCiudadOfUbicacionListUbicacion = em.merge(oldFkCiudadOfUbicacionListUbicacion);
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

    public void edit(Ciudad ciudad) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ciudad persistentCiudad = em.find(Ciudad.class, ciudad.getCiudadId());
            Estado fkEstadoOld = persistentCiudad.getFkEstado();
            Estado fkEstadoNew = ciudad.getFkEstado();
            List<Ubicacion> ubicacionListOld = persistentCiudad.getUbicacionList();
            List<Ubicacion> ubicacionListNew = ciudad.getUbicacionList();
            if (fkEstadoNew != null) {
                fkEstadoNew = em.getReference(fkEstadoNew.getClass(), fkEstadoNew.getEstadoId());
                ciudad.setFkEstado(fkEstadoNew);
            }
            List<Ubicacion> attachedUbicacionListNew = new ArrayList<Ubicacion>();
            for (Ubicacion ubicacionListNewUbicacionToAttach : ubicacionListNew) {
                ubicacionListNewUbicacionToAttach = em.getReference(ubicacionListNewUbicacionToAttach.getClass(), ubicacionListNewUbicacionToAttach.getUbicacionId());
                attachedUbicacionListNew.add(ubicacionListNewUbicacionToAttach);
            }
            ubicacionListNew = attachedUbicacionListNew;
            ciudad.setUbicacionList(ubicacionListNew);
            ciudad = em.merge(ciudad);
            if (fkEstadoOld != null && !fkEstadoOld.equals(fkEstadoNew)) {
                fkEstadoOld.getCiudadList().remove(ciudad);
                fkEstadoOld = em.merge(fkEstadoOld);
            }
            if (fkEstadoNew != null && !fkEstadoNew.equals(fkEstadoOld)) {
                fkEstadoNew.getCiudadList().add(ciudad);
                fkEstadoNew = em.merge(fkEstadoNew);
            }
            for (Ubicacion ubicacionListOldUbicacion : ubicacionListOld) {
                if (!ubicacionListNew.contains(ubicacionListOldUbicacion)) {
                    ubicacionListOldUbicacion.setFkCiudad(null);
                    ubicacionListOldUbicacion = em.merge(ubicacionListOldUbicacion);
                }
            }
            for (Ubicacion ubicacionListNewUbicacion : ubicacionListNew) {
                if (!ubicacionListOld.contains(ubicacionListNewUbicacion)) {
                    Ciudad oldFkCiudadOfUbicacionListNewUbicacion = ubicacionListNewUbicacion.getFkCiudad();
                    ubicacionListNewUbicacion.setFkCiudad(ciudad);
                    ubicacionListNewUbicacion = em.merge(ubicacionListNewUbicacion);
                    if (oldFkCiudadOfUbicacionListNewUbicacion != null && !oldFkCiudadOfUbicacionListNewUbicacion.equals(ciudad)) {
                        oldFkCiudadOfUbicacionListNewUbicacion.getUbicacionList().remove(ubicacionListNewUbicacion);
                        oldFkCiudadOfUbicacionListNewUbicacion = em.merge(oldFkCiudadOfUbicacionListNewUbicacion);
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
                Long id = ciudad.getCiudadId();
                if (findCiudad(id) == null) {
                    throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.");
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
            Ciudad ciudad;
            try {
                ciudad = em.getReference(Ciudad.class, id);
                ciudad.getCiudadId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.", enfe);
            }
            Estado fkEstado = ciudad.getFkEstado();
            if (fkEstado != null) {
                fkEstado.getCiudadList().remove(ciudad);
                fkEstado = em.merge(fkEstado);
            }
            List<Ubicacion> ubicacionList = ciudad.getUbicacionList();
            for (Ubicacion ubicacionListUbicacion : ubicacionList) {
                ubicacionListUbicacion.setFkCiudad(null);
                ubicacionListUbicacion = em.merge(ubicacionListUbicacion);
            }
            em.remove(ciudad);
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

    public List<Ciudad> findCiudadEntities() {
        return findCiudadEntities(true, -1, -1);
    }

    public List<Ciudad> findCiudadEntities(int maxResults, int firstResult) {
        return findCiudadEntities(false, maxResults, firstResult);
    }

    private List<Ciudad> findCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudad.class));
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

    public Ciudad findCiudad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudad> rt = cq.from(Ciudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
