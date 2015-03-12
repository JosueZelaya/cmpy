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
import com.tecnogeek.comprameya.entidad.Categoria;
import com.tecnogeek.comprameya.entidad.Modelo;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Caracteristica;
import com.tecnogeek.comprameya.entidad.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws RollbackFailureException, Exception {
        if (producto.getCaracteristicaList() == null) {
            producto.setCaracteristicaList(new ArrayList<Caracteristica>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria fkSubTipoProducto = producto.getFkSubTipoProducto();
            if (fkSubTipoProducto != null) {
                fkSubTipoProducto = em.getReference(fkSubTipoProducto.getClass(), fkSubTipoProducto.getCategoriaId());
                producto.setFkSubTipoProducto(fkSubTipoProducto);
            }
            Modelo fkModelo = producto.getFkModelo();
            if (fkModelo != null) {
                fkModelo = em.getReference(fkModelo.getClass(), fkModelo.getModeloId());
                producto.setFkModelo(fkModelo);
            }
            Publicacion fkPublicacion = producto.getFkPublicacion();
            if (fkPublicacion != null) {
                fkPublicacion = em.getReference(fkPublicacion.getClass(), fkPublicacion.getPublicacionId());
                producto.setFkPublicacion(fkPublicacion);
            }
            List<Caracteristica> attachedCaracteristicaList = new ArrayList<Caracteristica>();
            for (Caracteristica caracteristicaListCaracteristicaToAttach : producto.getCaracteristicaList()) {
                caracteristicaListCaracteristicaToAttach = em.getReference(caracteristicaListCaracteristicaToAttach.getClass(), caracteristicaListCaracteristicaToAttach.getCaracteristicaId());
                attachedCaracteristicaList.add(caracteristicaListCaracteristicaToAttach);
            }
            producto.setCaracteristicaList(attachedCaracteristicaList);
            em.persist(producto);
            if (fkSubTipoProducto != null) {
                fkSubTipoProducto.getProductoList().add(producto);
                fkSubTipoProducto = em.merge(fkSubTipoProducto);
            }
            if (fkModelo != null) {
                fkModelo.getProductoList().add(producto);
                fkModelo = em.merge(fkModelo);
            }
            if (fkPublicacion != null) {
                fkPublicacion.getProductoList().add(producto);
                fkPublicacion = em.merge(fkPublicacion);
            }
            for (Caracteristica caracteristicaListCaracteristica : producto.getCaracteristicaList()) {
                Producto oldFkProductoOfCaracteristicaListCaracteristica = caracteristicaListCaracteristica.getFkProducto();
                caracteristicaListCaracteristica.setFkProducto(producto);
                caracteristicaListCaracteristica = em.merge(caracteristicaListCaracteristica);
                if (oldFkProductoOfCaracteristicaListCaracteristica != null) {
                    oldFkProductoOfCaracteristicaListCaracteristica.getCaracteristicaList().remove(caracteristicaListCaracteristica);
                    oldFkProductoOfCaracteristicaListCaracteristica = em.merge(oldFkProductoOfCaracteristicaListCaracteristica);
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

    public void edit(Producto producto) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto persistentProducto = em.find(Producto.class, producto.getProductoId());
            Categoria fkSubTipoProductoOld = persistentProducto.getFkSubTipoProducto();
            Categoria fkSubTipoProductoNew = producto.getFkSubTipoProducto();
            Modelo fkModeloOld = persistentProducto.getFkModelo();
            Modelo fkModeloNew = producto.getFkModelo();
            Publicacion fkPublicacionOld = persistentProducto.getFkPublicacion();
            Publicacion fkPublicacionNew = producto.getFkPublicacion();
            List<Caracteristica> caracteristicaListOld = persistentProducto.getCaracteristicaList();
            List<Caracteristica> caracteristicaListNew = producto.getCaracteristicaList();
            if (fkSubTipoProductoNew != null) {
                fkSubTipoProductoNew = em.getReference(fkSubTipoProductoNew.getClass(), fkSubTipoProductoNew.getCategoriaId());
                producto.setFkSubTipoProducto(fkSubTipoProductoNew);
            }
            if (fkModeloNew != null) {
                fkModeloNew = em.getReference(fkModeloNew.getClass(), fkModeloNew.getModeloId());
                producto.setFkModelo(fkModeloNew);
            }
            if (fkPublicacionNew != null) {
                fkPublicacionNew = em.getReference(fkPublicacionNew.getClass(), fkPublicacionNew.getPublicacionId());
                producto.setFkPublicacion(fkPublicacionNew);
            }
            List<Caracteristica> attachedCaracteristicaListNew = new ArrayList<Caracteristica>();
            for (Caracteristica caracteristicaListNewCaracteristicaToAttach : caracteristicaListNew) {
                caracteristicaListNewCaracteristicaToAttach = em.getReference(caracteristicaListNewCaracteristicaToAttach.getClass(), caracteristicaListNewCaracteristicaToAttach.getCaracteristicaId());
                attachedCaracteristicaListNew.add(caracteristicaListNewCaracteristicaToAttach);
            }
            caracteristicaListNew = attachedCaracteristicaListNew;
            producto.setCaracteristicaList(caracteristicaListNew);
            producto = em.merge(producto);
            if (fkSubTipoProductoOld != null && !fkSubTipoProductoOld.equals(fkSubTipoProductoNew)) {
                fkSubTipoProductoOld.getProductoList().remove(producto);
                fkSubTipoProductoOld = em.merge(fkSubTipoProductoOld);
            }
            if (fkSubTipoProductoNew != null && !fkSubTipoProductoNew.equals(fkSubTipoProductoOld)) {
                fkSubTipoProductoNew.getProductoList().add(producto);
                fkSubTipoProductoNew = em.merge(fkSubTipoProductoNew);
            }
            if (fkModeloOld != null && !fkModeloOld.equals(fkModeloNew)) {
                fkModeloOld.getProductoList().remove(producto);
                fkModeloOld = em.merge(fkModeloOld);
            }
            if (fkModeloNew != null && !fkModeloNew.equals(fkModeloOld)) {
                fkModeloNew.getProductoList().add(producto);
                fkModeloNew = em.merge(fkModeloNew);
            }
            if (fkPublicacionOld != null && !fkPublicacionOld.equals(fkPublicacionNew)) {
                fkPublicacionOld.getProductoList().remove(producto);
                fkPublicacionOld = em.merge(fkPublicacionOld);
            }
            if (fkPublicacionNew != null && !fkPublicacionNew.equals(fkPublicacionOld)) {
                fkPublicacionNew.getProductoList().add(producto);
                fkPublicacionNew = em.merge(fkPublicacionNew);
            }
            for (Caracteristica caracteristicaListOldCaracteristica : caracteristicaListOld) {
                if (!caracteristicaListNew.contains(caracteristicaListOldCaracteristica)) {
                    caracteristicaListOldCaracteristica.setFkProducto(null);
                    caracteristicaListOldCaracteristica = em.merge(caracteristicaListOldCaracteristica);
                }
            }
            for (Caracteristica caracteristicaListNewCaracteristica : caracteristicaListNew) {
                if (!caracteristicaListOld.contains(caracteristicaListNewCaracteristica)) {
                    Producto oldFkProductoOfCaracteristicaListNewCaracteristica = caracteristicaListNewCaracteristica.getFkProducto();
                    caracteristicaListNewCaracteristica.setFkProducto(producto);
                    caracteristicaListNewCaracteristica = em.merge(caracteristicaListNewCaracteristica);
                    if (oldFkProductoOfCaracteristicaListNewCaracteristica != null && !oldFkProductoOfCaracteristicaListNewCaracteristica.equals(producto)) {
                        oldFkProductoOfCaracteristicaListNewCaracteristica.getCaracteristicaList().remove(caracteristicaListNewCaracteristica);
                        oldFkProductoOfCaracteristicaListNewCaracteristica = em.merge(oldFkProductoOfCaracteristicaListNewCaracteristica);
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
                Long id = producto.getProductoId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getProductoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Categoria fkSubTipoProducto = producto.getFkSubTipoProducto();
            if (fkSubTipoProducto != null) {
                fkSubTipoProducto.getProductoList().remove(producto);
                fkSubTipoProducto = em.merge(fkSubTipoProducto);
            }
            Modelo fkModelo = producto.getFkModelo();
            if (fkModelo != null) {
                fkModelo.getProductoList().remove(producto);
                fkModelo = em.merge(fkModelo);
            }
            Publicacion fkPublicacion = producto.getFkPublicacion();
            if (fkPublicacion != null) {
                fkPublicacion.getProductoList().remove(producto);
                fkPublicacion = em.merge(fkPublicacion);
            }
            List<Caracteristica> caracteristicaList = producto.getCaracteristicaList();
            for (Caracteristica caracteristicaListCaracteristica : caracteristicaList) {
                caracteristicaListCaracteristica.setFkProducto(null);
                caracteristicaListCaracteristica = em.merge(caracteristicaListCaracteristica);
            }
            em.remove(producto);
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

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
