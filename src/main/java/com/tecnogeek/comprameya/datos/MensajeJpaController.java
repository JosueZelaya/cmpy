/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import com.tecnogeek.comprameya.entidad.Mensaje;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class MensajeJpaController implements Serializable {

    public MensajeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mensaje mensaje) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario fkUsuarioEmisor = mensaje.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor = em.getReference(fkUsuarioEmisor.getClass(), fkUsuarioEmisor.getUsuarioId());
                mensaje.setFkUsuarioEmisor(fkUsuarioEmisor);
            }
            Usuario fkUsuarioReceptor = mensaje.getFkUsuarioReceptor();
            if (fkUsuarioReceptor != null) {
                fkUsuarioReceptor = em.getReference(fkUsuarioReceptor.getClass(), fkUsuarioReceptor.getUsuarioId());
                mensaje.setFkUsuarioReceptor(fkUsuarioReceptor);
            }
            em.persist(mensaje);
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getMensajeList().add(mensaje);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            if (fkUsuarioReceptor != null) {
                fkUsuarioReceptor.getMensajeList().add(mensaje);
                fkUsuarioReceptor = em.merge(fkUsuarioReceptor);
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

    public void edit(Mensaje mensaje) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Mensaje persistentMensaje = em.find(Mensaje.class, mensaje.getMensajeId());
            Usuario fkUsuarioEmisorOld = persistentMensaje.getFkUsuarioEmisor();
            Usuario fkUsuarioEmisorNew = mensaje.getFkUsuarioEmisor();
            Usuario fkUsuarioReceptorOld = persistentMensaje.getFkUsuarioReceptor();
            Usuario fkUsuarioReceptorNew = mensaje.getFkUsuarioReceptor();
            if (fkUsuarioEmisorNew != null) {
                fkUsuarioEmisorNew = em.getReference(fkUsuarioEmisorNew.getClass(), fkUsuarioEmisorNew.getUsuarioId());
                mensaje.setFkUsuarioEmisor(fkUsuarioEmisorNew);
            }
            if (fkUsuarioReceptorNew != null) {
                fkUsuarioReceptorNew = em.getReference(fkUsuarioReceptorNew.getClass(), fkUsuarioReceptorNew.getUsuarioId());
                mensaje.setFkUsuarioReceptor(fkUsuarioReceptorNew);
            }
            mensaje = em.merge(mensaje);
            if (fkUsuarioEmisorOld != null && !fkUsuarioEmisorOld.equals(fkUsuarioEmisorNew)) {
                fkUsuarioEmisorOld.getMensajeList().remove(mensaje);
                fkUsuarioEmisorOld = em.merge(fkUsuarioEmisorOld);
            }
            if (fkUsuarioEmisorNew != null && !fkUsuarioEmisorNew.equals(fkUsuarioEmisorOld)) {
                fkUsuarioEmisorNew.getMensajeList().add(mensaje);
                fkUsuarioEmisorNew = em.merge(fkUsuarioEmisorNew);
            }
            if (fkUsuarioReceptorOld != null && !fkUsuarioReceptorOld.equals(fkUsuarioReceptorNew)) {
                fkUsuarioReceptorOld.getMensajeList().remove(mensaje);
                fkUsuarioReceptorOld = em.merge(fkUsuarioReceptorOld);
            }
            if (fkUsuarioReceptorNew != null && !fkUsuarioReceptorNew.equals(fkUsuarioReceptorOld)) {
                fkUsuarioReceptorNew.getMensajeList().add(mensaje);
                fkUsuarioReceptorNew = em.merge(fkUsuarioReceptorNew);
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
                Long id = mensaje.getMensajeId();
                if (findMensaje(id) == null) {
                    throw new NonexistentEntityException("The mensaje with id " + id + " no longer exists.");
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
            Mensaje mensaje;
            try {
                mensaje = em.getReference(Mensaje.class, id);
                mensaje.getMensajeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensaje with id " + id + " no longer exists.", enfe);
            }
            Usuario fkUsuarioEmisor = mensaje.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getMensajeList().remove(mensaje);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            Usuario fkUsuarioReceptor = mensaje.getFkUsuarioReceptor();
            if (fkUsuarioReceptor != null) {
                fkUsuarioReceptor.getMensajeList().remove(mensaje);
                fkUsuarioReceptor = em.merge(fkUsuarioReceptor);
            }
            em.remove(mensaje);
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

    public List<Mensaje> findMensajeEntities() {
        return findMensajeEntities(true, -1, -1);
    }

    public List<Mensaje> findMensajeEntities(int maxResults, int firstResult) {
        return findMensajeEntities(false, maxResults, firstResult);
    }

    private List<Mensaje> findMensajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mensaje.class));
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

    public Mensaje findMensaje(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mensaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mensaje> rt = cq.from(Mensaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
