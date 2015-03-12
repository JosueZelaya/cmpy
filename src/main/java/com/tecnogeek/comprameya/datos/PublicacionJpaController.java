/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.datos;

import com.tecnogeek.comprameya.datos.excepcion.IllegalOrphanException;
import com.tecnogeek.comprameya.datos.excepcion.NonexistentEntityException;
import com.tecnogeek.comprameya.datos.excepcion.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.entidad.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import com.tecnogeek.comprameya.entidad.Producto;
import com.tecnogeek.comprameya.entidad.Comentario;
import com.tecnogeek.comprameya.entidad.Publicacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author genaro
 */
public class PublicacionJpaController implements Serializable {

    public PublicacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicacion publicacion) throws RollbackFailureException, Exception {
        if (publicacion.getUbicacionList() == null) {
            publicacion.setUbicacionList(new ArrayList<Ubicacion>());
        }
        if (publicacion.getProductoList() == null) {
            publicacion.setProductoList(new ArrayList<Producto>());
        }
        if (publicacion.getComentarioList() == null) {
            publicacion.setComentarioList(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoPublicacion fkTipoPublicacion = publicacion.getFkTipoPublicacion();
            if (fkTipoPublicacion != null) {
                fkTipoPublicacion = em.getReference(fkTipoPublicacion.getClass(), fkTipoPublicacion.getTipoPublicacionId());
                publicacion.setFkTipoPublicacion(fkTipoPublicacion);
            }
            Usuario fkUsuario = publicacion.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario = em.getReference(fkUsuario.getClass(), fkUsuario.getUsuarioId());
                publicacion.setFkUsuario(fkUsuario);
            }
            List<Ubicacion> attachedUbicacionList = new ArrayList<Ubicacion>();
            for (Ubicacion ubicacionListUbicacionToAttach : publicacion.getUbicacionList()) {
                ubicacionListUbicacionToAttach = em.getReference(ubicacionListUbicacionToAttach.getClass(), ubicacionListUbicacionToAttach.getUbicacionId());
                attachedUbicacionList.add(ubicacionListUbicacionToAttach);
            }
            publicacion.setUbicacionList(attachedUbicacionList);
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : publicacion.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getProductoId());
                attachedProductoList.add(productoListProductoToAttach);
            }
            publicacion.setProductoList(attachedProductoList);
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : publicacion.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getComentarioId());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            publicacion.setComentarioList(attachedComentarioList);
            em.persist(publicacion);
            if (fkTipoPublicacion != null) {
                fkTipoPublicacion.getPublicacionList().add(publicacion);
                fkTipoPublicacion = em.merge(fkTipoPublicacion);
            }
            if (fkUsuario != null) {
                fkUsuario.getPublicacionList().add(publicacion);
                fkUsuario = em.merge(fkUsuario);
            }
            for (Ubicacion ubicacionListUbicacion : publicacion.getUbicacionList()) {
                Publicacion oldFkPublicacionOfUbicacionListUbicacion = ubicacionListUbicacion.getFkPublicacion();
                ubicacionListUbicacion.setFkPublicacion(publicacion);
                ubicacionListUbicacion = em.merge(ubicacionListUbicacion);
                if (oldFkPublicacionOfUbicacionListUbicacion != null) {
                    oldFkPublicacionOfUbicacionListUbicacion.getUbicacionList().remove(ubicacionListUbicacion);
                    oldFkPublicacionOfUbicacionListUbicacion = em.merge(oldFkPublicacionOfUbicacionListUbicacion);
                }
            }
            for (Producto productoListProducto : publicacion.getProductoList()) {
                Publicacion oldFkPublicacionOfProductoListProducto = productoListProducto.getFkPublicacion();
                productoListProducto.setFkPublicacion(publicacion);
                productoListProducto = em.merge(productoListProducto);
                if (oldFkPublicacionOfProductoListProducto != null) {
                    oldFkPublicacionOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldFkPublicacionOfProductoListProducto = em.merge(oldFkPublicacionOfProductoListProducto);
                }
            }
            for (Comentario comentarioListComentario : publicacion.getComentarioList()) {
                Publicacion oldFkPublicacionOfComentarioListComentario = comentarioListComentario.getFkPublicacion();
                comentarioListComentario.setFkPublicacion(publicacion);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldFkPublicacionOfComentarioListComentario != null) {
                    oldFkPublicacionOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldFkPublicacionOfComentarioListComentario = em.merge(oldFkPublicacionOfComentarioListComentario);
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

    public void edit(Publicacion publicacion) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Publicacion persistentPublicacion = em.find(Publicacion.class, publicacion.getPublicacionId());
            TipoPublicacion fkTipoPublicacionOld = persistentPublicacion.getFkTipoPublicacion();
            TipoPublicacion fkTipoPublicacionNew = publicacion.getFkTipoPublicacion();
            Usuario fkUsuarioOld = persistentPublicacion.getFkUsuario();
            Usuario fkUsuarioNew = publicacion.getFkUsuario();
            List<Ubicacion> ubicacionListOld = persistentPublicacion.getUbicacionList();
            List<Ubicacion> ubicacionListNew = publicacion.getUbicacionList();
            List<Producto> productoListOld = persistentPublicacion.getProductoList();
            List<Producto> productoListNew = publicacion.getProductoList();
            List<Comentario> comentarioListOld = persistentPublicacion.getComentarioList();
            List<Comentario> comentarioListNew = publicacion.getComentarioList();
            List<String> illegalOrphanMessages = null;
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentario " + comentarioListOldComentario + " since its fkPublicacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkTipoPublicacionNew != null) {
                fkTipoPublicacionNew = em.getReference(fkTipoPublicacionNew.getClass(), fkTipoPublicacionNew.getTipoPublicacionId());
                publicacion.setFkTipoPublicacion(fkTipoPublicacionNew);
            }
            if (fkUsuarioNew != null) {
                fkUsuarioNew = em.getReference(fkUsuarioNew.getClass(), fkUsuarioNew.getUsuarioId());
                publicacion.setFkUsuario(fkUsuarioNew);
            }
            List<Ubicacion> attachedUbicacionListNew = new ArrayList<Ubicacion>();
            for (Ubicacion ubicacionListNewUbicacionToAttach : ubicacionListNew) {
                ubicacionListNewUbicacionToAttach = em.getReference(ubicacionListNewUbicacionToAttach.getClass(), ubicacionListNewUbicacionToAttach.getUbicacionId());
                attachedUbicacionListNew.add(ubicacionListNewUbicacionToAttach);
            }
            ubicacionListNew = attachedUbicacionListNew;
            publicacion.setUbicacionList(ubicacionListNew);
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getProductoId());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            publicacion.setProductoList(productoListNew);
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getComentarioId());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            publicacion.setComentarioList(comentarioListNew);
            publicacion = em.merge(publicacion);
            if (fkTipoPublicacionOld != null && !fkTipoPublicacionOld.equals(fkTipoPublicacionNew)) {
                fkTipoPublicacionOld.getPublicacionList().remove(publicacion);
                fkTipoPublicacionOld = em.merge(fkTipoPublicacionOld);
            }
            if (fkTipoPublicacionNew != null && !fkTipoPublicacionNew.equals(fkTipoPublicacionOld)) {
                fkTipoPublicacionNew.getPublicacionList().add(publicacion);
                fkTipoPublicacionNew = em.merge(fkTipoPublicacionNew);
            }
            if (fkUsuarioOld != null && !fkUsuarioOld.equals(fkUsuarioNew)) {
                fkUsuarioOld.getPublicacionList().remove(publicacion);
                fkUsuarioOld = em.merge(fkUsuarioOld);
            }
            if (fkUsuarioNew != null && !fkUsuarioNew.equals(fkUsuarioOld)) {
                fkUsuarioNew.getPublicacionList().add(publicacion);
                fkUsuarioNew = em.merge(fkUsuarioNew);
            }
            for (Ubicacion ubicacionListOldUbicacion : ubicacionListOld) {
                if (!ubicacionListNew.contains(ubicacionListOldUbicacion)) {
                    ubicacionListOldUbicacion.setFkPublicacion(null);
                    ubicacionListOldUbicacion = em.merge(ubicacionListOldUbicacion);
                }
            }
            for (Ubicacion ubicacionListNewUbicacion : ubicacionListNew) {
                if (!ubicacionListOld.contains(ubicacionListNewUbicacion)) {
                    Publicacion oldFkPublicacionOfUbicacionListNewUbicacion = ubicacionListNewUbicacion.getFkPublicacion();
                    ubicacionListNewUbicacion.setFkPublicacion(publicacion);
                    ubicacionListNewUbicacion = em.merge(ubicacionListNewUbicacion);
                    if (oldFkPublicacionOfUbicacionListNewUbicacion != null && !oldFkPublicacionOfUbicacionListNewUbicacion.equals(publicacion)) {
                        oldFkPublicacionOfUbicacionListNewUbicacion.getUbicacionList().remove(ubicacionListNewUbicacion);
                        oldFkPublicacionOfUbicacionListNewUbicacion = em.merge(oldFkPublicacionOfUbicacionListNewUbicacion);
                    }
                }
            }
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    productoListOldProducto.setFkPublicacion(null);
                    productoListOldProducto = em.merge(productoListOldProducto);
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Publicacion oldFkPublicacionOfProductoListNewProducto = productoListNewProducto.getFkPublicacion();
                    productoListNewProducto.setFkPublicacion(publicacion);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldFkPublicacionOfProductoListNewProducto != null && !oldFkPublicacionOfProductoListNewProducto.equals(publicacion)) {
                        oldFkPublicacionOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldFkPublicacionOfProductoListNewProducto = em.merge(oldFkPublicacionOfProductoListNewProducto);
                    }
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Publicacion oldFkPublicacionOfComentarioListNewComentario = comentarioListNewComentario.getFkPublicacion();
                    comentarioListNewComentario.setFkPublicacion(publicacion);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldFkPublicacionOfComentarioListNewComentario != null && !oldFkPublicacionOfComentarioListNewComentario.equals(publicacion)) {
                        oldFkPublicacionOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldFkPublicacionOfComentarioListNewComentario = em.merge(oldFkPublicacionOfComentarioListNewComentario);
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
                Long id = publicacion.getPublicacionId();
                if (findPublicacion(id) == null) {
                    throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Publicacion publicacion;
            try {
                publicacion = em.getReference(Publicacion.class, id);
                publicacion.getPublicacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comentario> comentarioListOrphanCheck = publicacion.getComentarioList();
            for (Comentario comentarioListOrphanCheckComentario : comentarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Publicacion (" + publicacion + ") cannot be destroyed since the Comentario " + comentarioListOrphanCheckComentario + " in its comentarioList field has a non-nullable fkPublicacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoPublicacion fkTipoPublicacion = publicacion.getFkTipoPublicacion();
            if (fkTipoPublicacion != null) {
                fkTipoPublicacion.getPublicacionList().remove(publicacion);
                fkTipoPublicacion = em.merge(fkTipoPublicacion);
            }
            Usuario fkUsuario = publicacion.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario.getPublicacionList().remove(publicacion);
                fkUsuario = em.merge(fkUsuario);
            }
            List<Ubicacion> ubicacionList = publicacion.getUbicacionList();
            for (Ubicacion ubicacionListUbicacion : ubicacionList) {
                ubicacionListUbicacion.setFkPublicacion(null);
                ubicacionListUbicacion = em.merge(ubicacionListUbicacion);
            }
            List<Producto> productoList = publicacion.getProductoList();
            for (Producto productoListProducto : productoList) {
                productoListProducto.setFkPublicacion(null);
                productoListProducto = em.merge(productoListProducto);
            }
            em.remove(publicacion);
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

    public List<Publicacion> findPublicacionEntities() {
        return findPublicacionEntities(true, -1, -1);
    }

    public List<Publicacion> findPublicacionEntities(int maxResults, int firstResult) {
        return findPublicacionEntities(false, maxResults, firstResult);
    }

    private List<Publicacion> findPublicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Publicacion.class));
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

    public Publicacion findPublicacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicacion> rt = cq.from(Publicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
