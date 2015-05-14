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
import com.tecnogeek.comprameya.entidad.TipoEmpresa;
import com.tecnogeek.comprameya.entidad.Empleado;
import com.tecnogeek.comprameya.entidad.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) throws RollbackFailureException, Exception {
        if (empresa.getEmpleadoList() == null) {
            empresa.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoEmpresa fkTipoEmpresa = empresa.getFkTipoEmpresa();
            if (fkTipoEmpresa != null) {
                fkTipoEmpresa = em.getReference(fkTipoEmpresa.getClass(), fkTipoEmpresa.getTipoEmpresaId());
                empresa.setFkTipoEmpresa(fkTipoEmpresa);
            }
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : empresa.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getEmpleadoId());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            empresa.setEmpleadoList(attachedEmpleadoList);
            em.persist(empresa);
            if (fkTipoEmpresa != null) {
                fkTipoEmpresa.getEmpresaList().add(empresa);
                fkTipoEmpresa = em.merge(fkTipoEmpresa);
            }
            for (Empleado empleadoListEmpleado : empresa.getEmpleadoList()) {
                Empresa oldFkEmpresaOfEmpleadoListEmpleado = empleadoListEmpleado.getFkEmpresa();
                empleadoListEmpleado.setFkEmpresa(empresa);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldFkEmpresaOfEmpleadoListEmpleado != null) {
                    oldFkEmpresaOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldFkEmpresaOfEmpleadoListEmpleado = em.merge(oldFkEmpresaOfEmpleadoListEmpleado);
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

    public void edit(Empresa empresa) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getEmpresaId());
            TipoEmpresa fkTipoEmpresaOld = persistentEmpresa.getFkTipoEmpresa();
            TipoEmpresa fkTipoEmpresaNew = empresa.getFkTipoEmpresa();
            List<Empleado> empleadoListOld = persistentEmpresa.getEmpleadoList();
            List<Empleado> empleadoListNew = empresa.getEmpleadoList();
            if (fkTipoEmpresaNew != null) {
                fkTipoEmpresaNew = em.getReference(fkTipoEmpresaNew.getClass(), fkTipoEmpresaNew.getTipoEmpresaId());
                empresa.setFkTipoEmpresa(fkTipoEmpresaNew);
            }
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getEmpleadoId());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            empresa.setEmpleadoList(empleadoListNew);
            empresa = em.merge(empresa);
            if (fkTipoEmpresaOld != null && !fkTipoEmpresaOld.equals(fkTipoEmpresaNew)) {
                fkTipoEmpresaOld.getEmpresaList().remove(empresa);
                fkTipoEmpresaOld = em.merge(fkTipoEmpresaOld);
            }
            if (fkTipoEmpresaNew != null && !fkTipoEmpresaNew.equals(fkTipoEmpresaOld)) {
                fkTipoEmpresaNew.getEmpresaList().add(empresa);
                fkTipoEmpresaNew = em.merge(fkTipoEmpresaNew);
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setFkEmpresa(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Empresa oldFkEmpresaOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getFkEmpresa();
                    empleadoListNewEmpleado.setFkEmpresa(empresa);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldFkEmpresaOfEmpleadoListNewEmpleado != null && !oldFkEmpresaOfEmpleadoListNewEmpleado.equals(empresa)) {
                        oldFkEmpresaOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldFkEmpresaOfEmpleadoListNewEmpleado = em.merge(oldFkEmpresaOfEmpleadoListNewEmpleado);
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
                Long id = empresa.getEmpresaId();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getEmpresaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            TipoEmpresa fkTipoEmpresa = empresa.getFkTipoEmpresa();
            if (fkTipoEmpresa != null) {
                fkTipoEmpresa.getEmpresaList().remove(empresa);
                fkTipoEmpresa = em.merge(fkTipoEmpresa);
            }
            List<Empleado> empleadoList = empresa.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setFkEmpresa(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
            }
            em.remove(empresa);
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

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
