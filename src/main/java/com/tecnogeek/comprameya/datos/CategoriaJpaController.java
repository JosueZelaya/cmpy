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
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) throws RollbackFailureException, Exception {
        if (categoria.getProductoList() == null) {
            categoria.setProductoList(new ArrayList<Producto>());
        }
        if (categoria.getCategoriaList() == null) {
            categoria.setCategoriaList(new ArrayList<Categoria>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria fkCategoria = categoria.getFkCategoria();
            if (fkCategoria != null) {
                fkCategoria = em.getReference(fkCategoria.getClass(), fkCategoria.getCategoriaId());
                categoria.setFkCategoria(fkCategoria);
            }
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : categoria.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getProductoId());
                attachedProductoList.add(productoListProductoToAttach);
            }
            categoria.setProductoList(attachedProductoList);
            List<Categoria> attachedCategoriaList = new ArrayList<Categoria>();
            for (Categoria categoriaListCategoriaToAttach : categoria.getCategoriaList()) {
                categoriaListCategoriaToAttach = em.getReference(categoriaListCategoriaToAttach.getClass(), categoriaListCategoriaToAttach.getCategoriaId());
                attachedCategoriaList.add(categoriaListCategoriaToAttach);
            }
            categoria.setCategoriaList(attachedCategoriaList);
            em.persist(categoria);
            if (fkCategoria != null) {
                fkCategoria.getCategoriaList().add(categoria);
                fkCategoria = em.merge(fkCategoria);
            }
            for (Producto productoListProducto : categoria.getProductoList()) {
                Categoria oldFkSubTipoProductoOfProductoListProducto = productoListProducto.getFkSubTipoProducto();
                productoListProducto.setFkSubTipoProducto(categoria);
                productoListProducto = em.merge(productoListProducto);
                if (oldFkSubTipoProductoOfProductoListProducto != null) {
                    oldFkSubTipoProductoOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldFkSubTipoProductoOfProductoListProducto = em.merge(oldFkSubTipoProductoOfProductoListProducto);
                }
            }
            for (Categoria categoriaListCategoria : categoria.getCategoriaList()) {
                Categoria oldFkCategoriaOfCategoriaListCategoria = categoriaListCategoria.getFkCategoria();
                categoriaListCategoria.setFkCategoria(categoria);
                categoriaListCategoria = em.merge(categoriaListCategoria);
                if (oldFkCategoriaOfCategoriaListCategoria != null) {
                    oldFkCategoriaOfCategoriaListCategoria.getCategoriaList().remove(categoriaListCategoria);
                    oldFkCategoriaOfCategoriaListCategoria = em.merge(oldFkCategoriaOfCategoriaListCategoria);
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

    public void edit(Categoria categoria) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getCategoriaId());
            Categoria fkCategoriaOld = persistentCategoria.getFkCategoria();
            Categoria fkCategoriaNew = categoria.getFkCategoria();
            List<Producto> productoListOld = persistentCategoria.getProductoList();
            List<Producto> productoListNew = categoria.getProductoList();
            List<Categoria> categoriaListOld = persistentCategoria.getCategoriaList();
            List<Categoria> categoriaListNew = categoria.getCategoriaList();
            if (fkCategoriaNew != null) {
                fkCategoriaNew = em.getReference(fkCategoriaNew.getClass(), fkCategoriaNew.getCategoriaId());
                categoria.setFkCategoria(fkCategoriaNew);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getProductoId());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            categoria.setProductoList(productoListNew);
            List<Categoria> attachedCategoriaListNew = new ArrayList<Categoria>();
            for (Categoria categoriaListNewCategoriaToAttach : categoriaListNew) {
                categoriaListNewCategoriaToAttach = em.getReference(categoriaListNewCategoriaToAttach.getClass(), categoriaListNewCategoriaToAttach.getCategoriaId());
                attachedCategoriaListNew.add(categoriaListNewCategoriaToAttach);
            }
            categoriaListNew = attachedCategoriaListNew;
            categoria.setCategoriaList(categoriaListNew);
            categoria = em.merge(categoria);
            if (fkCategoriaOld != null && !fkCategoriaOld.equals(fkCategoriaNew)) {
                fkCategoriaOld.getCategoriaList().remove(categoria);
                fkCategoriaOld = em.merge(fkCategoriaOld);
            }
            if (fkCategoriaNew != null && !fkCategoriaNew.equals(fkCategoriaOld)) {
                fkCategoriaNew.getCategoriaList().add(categoria);
                fkCategoriaNew = em.merge(fkCategoriaNew);
            }
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    productoListOldProducto.setFkSubTipoProducto(null);
                    productoListOldProducto = em.merge(productoListOldProducto);
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Categoria oldFkSubTipoProductoOfProductoListNewProducto = productoListNewProducto.getFkSubTipoProducto();
                    productoListNewProducto.setFkSubTipoProducto(categoria);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldFkSubTipoProductoOfProductoListNewProducto != null && !oldFkSubTipoProductoOfProductoListNewProducto.equals(categoria)) {
                        oldFkSubTipoProductoOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldFkSubTipoProductoOfProductoListNewProducto = em.merge(oldFkSubTipoProductoOfProductoListNewProducto);
                    }
                }
            }
            for (Categoria categoriaListOldCategoria : categoriaListOld) {
                if (!categoriaListNew.contains(categoriaListOldCategoria)) {
                    categoriaListOldCategoria.setFkCategoria(null);
                    categoriaListOldCategoria = em.merge(categoriaListOldCategoria);
                }
            }
            for (Categoria categoriaListNewCategoria : categoriaListNew) {
                if (!categoriaListOld.contains(categoriaListNewCategoria)) {
                    Categoria oldFkCategoriaOfCategoriaListNewCategoria = categoriaListNewCategoria.getFkCategoria();
                    categoriaListNewCategoria.setFkCategoria(categoria);
                    categoriaListNewCategoria = em.merge(categoriaListNewCategoria);
                    if (oldFkCategoriaOfCategoriaListNewCategoria != null && !oldFkCategoriaOfCategoriaListNewCategoria.equals(categoria)) {
                        oldFkCategoriaOfCategoriaListNewCategoria.getCategoriaList().remove(categoriaListNewCategoria);
                        oldFkCategoriaOfCategoriaListNewCategoria = em.merge(oldFkCategoriaOfCategoriaListNewCategoria);
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
                Long id = categoria.getCategoriaId();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getCategoriaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            Categoria fkCategoria = categoria.getFkCategoria();
            if (fkCategoria != null) {
                fkCategoria.getCategoriaList().remove(categoria);
                fkCategoria = em.merge(fkCategoria);
            }
            List<Producto> productoList = categoria.getProductoList();
            for (Producto productoListProducto : productoList) {
                productoListProducto.setFkSubTipoProducto(null);
                productoListProducto = em.merge(productoListProducto);
            }
            List<Categoria> categoriaList = categoria.getCategoriaList();
            for (Categoria categoriaListCategoria : categoriaList) {
                categoriaListCategoria.setFkCategoria(null);
                categoriaListCategoria = em.merge(categoriaListCategoria);
            }
            em.remove(categoria);
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

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
