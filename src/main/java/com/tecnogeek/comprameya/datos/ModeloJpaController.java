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
import com.tecnogeek.comprameya.entidad.Marca;
import com.tecnogeek.comprameya.entidad.Modelo;
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
public class ModeloJpaController implements Serializable {

    public ModeloJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modelo modelo) throws RollbackFailureException, Exception {
        if (modelo.getProductoList() == null) {
            modelo.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Marca fkMarca = modelo.getFkMarca();
            if (fkMarca != null) {
                fkMarca = em.getReference(fkMarca.getClass(), fkMarca.getMarcaId());
                modelo.setFkMarca(fkMarca);
            }
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : modelo.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getProductoId());
                attachedProductoList.add(productoListProductoToAttach);
            }
            modelo.setProductoList(attachedProductoList);
            em.persist(modelo);
            if (fkMarca != null) {
                fkMarca.getModeloList().add(modelo);
                fkMarca = em.merge(fkMarca);
            }
            for (Producto productoListProducto : modelo.getProductoList()) {
                Modelo oldFkModeloOfProductoListProducto = productoListProducto.getFkModelo();
                productoListProducto.setFkModelo(modelo);
                productoListProducto = em.merge(productoListProducto);
                if (oldFkModeloOfProductoListProducto != null) {
                    oldFkModeloOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldFkModeloOfProductoListProducto = em.merge(oldFkModeloOfProductoListProducto);
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

    public void edit(Modelo modelo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Modelo persistentModelo = em.find(Modelo.class, modelo.getModeloId());
            Marca fkMarcaOld = persistentModelo.getFkMarca();
            Marca fkMarcaNew = modelo.getFkMarca();
            List<Producto> productoListOld = persistentModelo.getProductoList();
            List<Producto> productoListNew = modelo.getProductoList();
            if (fkMarcaNew != null) {
                fkMarcaNew = em.getReference(fkMarcaNew.getClass(), fkMarcaNew.getMarcaId());
                modelo.setFkMarca(fkMarcaNew);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getProductoId());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            modelo.setProductoList(productoListNew);
            modelo = em.merge(modelo);
            if (fkMarcaOld != null && !fkMarcaOld.equals(fkMarcaNew)) {
                fkMarcaOld.getModeloList().remove(modelo);
                fkMarcaOld = em.merge(fkMarcaOld);
            }
            if (fkMarcaNew != null && !fkMarcaNew.equals(fkMarcaOld)) {
                fkMarcaNew.getModeloList().add(modelo);
                fkMarcaNew = em.merge(fkMarcaNew);
            }
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    productoListOldProducto.setFkModelo(null);
                    productoListOldProducto = em.merge(productoListOldProducto);
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Modelo oldFkModeloOfProductoListNewProducto = productoListNewProducto.getFkModelo();
                    productoListNewProducto.setFkModelo(modelo);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldFkModeloOfProductoListNewProducto != null && !oldFkModeloOfProductoListNewProducto.equals(modelo)) {
                        oldFkModeloOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldFkModeloOfProductoListNewProducto = em.merge(oldFkModeloOfProductoListNewProducto);
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
                Long id = modelo.getModeloId();
                if (findModelo(id) == null) {
                    throw new NonexistentEntityException("The modelo with id " + id + " no longer exists.");
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
            Modelo modelo;
            try {
                modelo = em.getReference(Modelo.class, id);
                modelo.getModeloId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modelo with id " + id + " no longer exists.", enfe);
            }
            Marca fkMarca = modelo.getFkMarca();
            if (fkMarca != null) {
                fkMarca.getModeloList().remove(modelo);
                fkMarca = em.merge(fkMarca);
            }
            List<Producto> productoList = modelo.getProductoList();
            for (Producto productoListProducto : productoList) {
                productoListProducto.setFkModelo(null);
                productoListProducto = em.merge(productoListProducto);
            }
            em.remove(modelo);
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

    public List<Modelo> findModeloEntities() {
        return findModeloEntities(true, -1, -1);
    }

    public List<Modelo> findModeloEntities(int maxResults, int firstResult) {
        return findModeloEntities(false, maxResults, firstResult);
    }

    private List<Modelo> findModeloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modelo.class));
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

    public Modelo findModelo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getModeloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modelo> rt = cq.from(Modelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
