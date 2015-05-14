/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Empresa;
import com.tecnogeek.comprameya.entidad.Persona;
import com.tecnogeek.comprameya.entidad.Puesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Empresa fkEmpresa = empleado.getFkEmpresa();
            if (fkEmpresa != null) {
                fkEmpresa = em.getReference(fkEmpresa.getClass(), fkEmpresa.getEmpresaId());
                empleado.setFkEmpresa(fkEmpresa);
            }
            Persona fkPersona = empleado.getFkPersona();
            if (fkPersona != null) {
                fkPersona = em.getReference(fkPersona.getClass(), fkPersona.getPersonaId());
                empleado.setFkPersona(fkPersona);
            }
            Puesto fkPuesto = empleado.getFkPuesto();
            if (fkPuesto != null) {
                fkPuesto = em.getReference(fkPuesto.getClass(), fkPuesto.getPuestoId());
                empleado.setFkPuesto(fkPuesto);
            }
            em.persist(empleado);
            if (fkEmpresa != null) {
                fkEmpresa.getEmpleadoList().add(empleado);
                fkEmpresa = em.merge(fkEmpresa);
            }
            if (fkPersona != null) {
                fkPersona.getEmpleadoList().add(empleado);
                fkPersona = em.merge(fkPersona);
            }
            if (fkPuesto != null) {
                fkPuesto.getEmpleadoList().add(empleado);
                fkPuesto = em.merge(fkPuesto);
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

    public void edit(Empleado empleado) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getEmpleadoId());
            Empresa fkEmpresaOld = persistentEmpleado.getFkEmpresa();
            Empresa fkEmpresaNew = empleado.getFkEmpresa();
            Persona fkPersonaOld = persistentEmpleado.getFkPersona();
            Persona fkPersonaNew = empleado.getFkPersona();
            Puesto fkPuestoOld = persistentEmpleado.getFkPuesto();
            Puesto fkPuestoNew = empleado.getFkPuesto();
            if (fkEmpresaNew != null) {
                fkEmpresaNew = em.getReference(fkEmpresaNew.getClass(), fkEmpresaNew.getEmpresaId());
                empleado.setFkEmpresa(fkEmpresaNew);
            }
            if (fkPersonaNew != null) {
                fkPersonaNew = em.getReference(fkPersonaNew.getClass(), fkPersonaNew.getPersonaId());
                empleado.setFkPersona(fkPersonaNew);
            }
            if (fkPuestoNew != null) {
                fkPuestoNew = em.getReference(fkPuestoNew.getClass(), fkPuestoNew.getPuestoId());
                empleado.setFkPuesto(fkPuestoNew);
            }
            empleado = em.merge(empleado);
            if (fkEmpresaOld != null && !fkEmpresaOld.equals(fkEmpresaNew)) {
                fkEmpresaOld.getEmpleadoList().remove(empleado);
                fkEmpresaOld = em.merge(fkEmpresaOld);
            }
            if (fkEmpresaNew != null && !fkEmpresaNew.equals(fkEmpresaOld)) {
                fkEmpresaNew.getEmpleadoList().add(empleado);
                fkEmpresaNew = em.merge(fkEmpresaNew);
            }
            if (fkPersonaOld != null && !fkPersonaOld.equals(fkPersonaNew)) {
                fkPersonaOld.getEmpleadoList().remove(empleado);
                fkPersonaOld = em.merge(fkPersonaOld);
            }
            if (fkPersonaNew != null && !fkPersonaNew.equals(fkPersonaOld)) {
                fkPersonaNew.getEmpleadoList().add(empleado);
                fkPersonaNew = em.merge(fkPersonaNew);
            }
            if (fkPuestoOld != null && !fkPuestoOld.equals(fkPuestoNew)) {
                fkPuestoOld.getEmpleadoList().remove(empleado);
                fkPuestoOld = em.merge(fkPuestoOld);
            }
            if (fkPuestoNew != null && !fkPuestoNew.equals(fkPuestoOld)) {
                fkPuestoNew.getEmpleadoList().add(empleado);
                fkPuestoNew = em.merge(fkPuestoNew);
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
                Long id = empleado.getEmpleadoId();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getEmpleadoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Empresa fkEmpresa = empleado.getFkEmpresa();
            if (fkEmpresa != null) {
                fkEmpresa.getEmpleadoList().remove(empleado);
                fkEmpresa = em.merge(fkEmpresa);
            }
            Persona fkPersona = empleado.getFkPersona();
            if (fkPersona != null) {
                fkPersona.getEmpleadoList().remove(empleado);
                fkPersona = em.merge(fkPersona);
            }
            Puesto fkPuesto = empleado.getFkPuesto();
            if (fkPuesto != null) {
                fkPuesto.getEmpleadoList().remove(empleado);
                fkPuesto = em.merge(fkPuesto);
            }
            em.remove(empleado);
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

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
