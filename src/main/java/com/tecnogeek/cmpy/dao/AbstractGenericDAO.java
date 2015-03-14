package com.tecnogeek.cmpy.dao;


import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;

import com.tecnogeek.cmpy.utils.WriteLogs;
import com.tecnogeek.cmpy.utils.HibernateUtils;

public class AbstractGenericDAO {
	
	private final SessionFactory sessionFactory = getSessionFactory();
	private static final Logger logger = Logger.getLogger(AbstractGenericDAO.class);    
    
    public SessionFactory getSessionFactory() throws IllegalStateException {
        try {
            return HibernateUtils.getSessionFactory();
        } catch (Exception e) {

        	logger.error("@AbstractGenericDAO.getSessionFactory():Could not locate SessionFactory in JNDI " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.getSessionFactory():Could not locate SessionFactory in JNDI " + e.getMessage());
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");

        }
    }
    
    public boolean save(final Object obj) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(obj);
            sessionFactory.getCurrentSession().getTransaction().commit();            
            return true;
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.save(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.save(): " + e.getMessage());
           

            return false;
        }
    }
    
    public boolean save(final Object obj, final Session session) throws HibernateException {
        try {
            session.save(obj);
            return true;
        } catch (HibernateException e) {
        	//sessionFactory.getCurrentSession().getTransaction().rollback();

        	 logger.error("@AbstractGenericDAO.save(): " + e.getMessage());
             WriteLogs.writeLogsLine("@AbstractGenericDAO.save(): " + e.getMessage());
            

            throw e;
        }
    }
    
    public boolean update(final Object obj) {
        try {
        	 sessionFactory.getCurrentSession().beginTransaction();
             sessionFactory.getCurrentSession().update(obj);
             sessionFactory.getCurrentSession().getTransaction().commit();            
            return true;
        } catch (HibernateException e) {
        	sessionFactory.getCurrentSession().getTransaction().rollback();

        	 logger.error("@AbstractGenericDAO.update(): " + e.getMessage());
             WriteLogs.writeLogsLine("@AbstractGenericDAO.update(): " + e.getMessage());
            

            return false;
        }
    }
    
    public boolean update(final Object obj, final Session session) throws HibernateException {
        try {
            session.update(obj);
            return true;
        } catch (HibernateException e) {

        	 logger.error("@AbstractGenericDAO.update(): " + e.getMessage());
             WriteLogs.writeLogsLine("@AbstractGenericDAO.update(): " + e.getMessage());

            throw e;
        }
    }
    
    public void delete(final Object obj) throws HibernateException {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(obj);
            sessionFactory.getCurrentSession().getTransaction().commit();            
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("@AbstractGenericDAO.delete(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.delete(): " + e.getMessage());
           
            throw e;
        }
    }
    
    public void delete(final Object obj, final Session session) throws HibernateException {
        try {
            session.delete(obj);
        } catch (HibernateException e) {

        	 logger.error("@AbstractGenericDAO.delete(): " + e.getMessage());
             WriteLogs.writeLogsLine("@AbstractGenericDAO.delete(): " + e.getMessage());
            

            throw e;
        }
    }
    
    @SuppressWarnings("rawtypes")
	public List findAll(final Class clazz) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName());
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.findAll(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAll(): " + e.getMessage());
           

        }
        return objects;
    }    
    
    @SuppressWarnings("rawtypes")
    public List findAll(final Class clazz, final Session session) throws HibernateException {
        List objects = null;
        try {
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
        } catch (HibernateException e) {

        	logger.error("@AbstractGenericDAO.findAll(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAll(): " + e.getMessage());
           

            throw e;
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List findAllDistinct( final Class clazz,final Session session) throws HibernateException {
        List objects = null;
        try {
            //Query query = session.createQuery("from " +clazz.getName());
        	Query query = sessionFactory.getCurrentSession().createQuery("select distinct catalogName from " + clazz.getName());
            objects = query.list();
        } catch (HibernateException e) {

        	logger.error("@AbstractGenericDAO.findAllDistinct(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAllDistinct(): " + e.getMessage());
           

            throw e;
        }
        return objects;
    }
        
    @SuppressWarnings("rawtypes")
	public List findAll(final Class clazz, final int page, final int pageSize) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName());
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.findAll(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAll(): " + e.getMessage());
           

        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List findAll(final Class clazz, final int page, final int pageSize, final Session session) {
        List objects = null;
        try {
            Query query = session.createQuery("from " + clazz.getName());
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {

        	logger.error("@AbstractGenericDAO.findAll(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAll(): " + e.getMessage());
           

        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
	public List findAll(final Class clazz, final int page, final int pageSize, final String orderProperty, final String orderType) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {            
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
            criteria.setFirstResult((page - 1) * pageSize);            
            criteria.setMaxResults(pageSize);
            if(orderType.equals("desc"))
            	criteria.addOrder(Order.desc(orderProperty));
            else
            	criteria.addOrder(Order.asc(orderProperty));
            objects = criteria.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("@AbstractGenericDAO.findAll(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAll(): " + e.getMessage());
           
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
	public List findAll(final Class clazz, final int page, final int pageSize, final String orderProperty, final String orderType, final Session session) {
        List objects = null;
        
        try {            
            Criteria criteria = session.createCriteria(clazz);
            criteria.setFirstResult((page - 1) * pageSize);            
            criteria.setMaxResults(pageSize);
            if(orderType.equals("desc"))
            	criteria.addOrder(Order.desc(orderProperty));
            else
            	criteria.addOrder(Order.asc(orderProperty));
            objects = criteria.list();            
        } catch (HibernateException e) {            
        	logger.error("@AbstractGenericDAO.findAll(): " + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findAll(): " + e.getMessage());
           
            throw e;
        }
        return objects;
    }

    /*
     * Metodo para ejecutar una sentencia HQL en un Objeto de dominio especifico a partir de una clausula where
     */
    @SuppressWarnings("rawtypes")
	public List findByWhereStatement(final Class clazz, final String whereStatement) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + whereStatement);
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.findByWhereStatement():RuntimeException " + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement():RuntimeException " + re.getMessage());
                     

            throw re;
        }
        return objects;
    }
        
    /*
     * Metodo alternativo para recicler sesiones
     */
    @SuppressWarnings("rawtypes")
    public List findByWhereStatement(final Class clazz, final String whereStatement, final Session session) {
        List objects = null;
        try {
        	
            Query query = session.createQuery("from " + clazz.getName() + " where " + whereStatement);
            objects = query.list();

        } catch (RuntimeException re) {         
            logger.error("@AbstractGenericDAO.findByWhereStatement():find by where statement failed " + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement():find by where statement failed " + re.getMessage());
               

        }
        return objects;
    }
        
    @SuppressWarnings("rawtypes")
    public List findByWhereStatement(final Class clazz, final String whereStatement, final int page, final int pageSize) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + whereStatement);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.findByWhereStatement(): " + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement(): " + re.getMessage());
             

            throw re;
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List findByWhereStatement(final Class clazz, final String whereStatement, final int page, final int pageSize, final Session session) {
        List objects = null;
        try {
            Query query = session.createQuery("from " + clazz.getName() + " where " + whereStatement);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();

        } catch (RuntimeException re) {    
            logger.error("@AbstractGenericDAO.findByWhereStatement():find by where statement failed " + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement():find by where statement failed " + re.getMessage());

        }
        return objects;
    }

    @SuppressWarnings("rawtypes")
	public List findByWhereStatement(final Class clazz, final String whereStatement, final int page, final int pageSize, final String orderProperty, final String orderType) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + whereStatement + " order by " + orderProperty + " " + orderType);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("@AbstractGenericDAO.findByWhereStatement():" + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement(): " + re.getMessage());
            throw re;
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
	public List findByWhereStatement(final Class clazz, final String whereStatement, final int page, final int pageSize, final String orderProperty, final String orderType, Session session) {
        List objects = null;
        int firstResult = (page - 1) * pageSize;        
        try {
            
            Query query = session.createQuery("from " + clazz.getName() + " where " + whereStatement + " order by " + orderProperty + " " + orderType);
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
            objects = query.list();            

        } catch (RuntimeException re) {       
            logger.error("@AbstractGenericDAO.findByWhereStatement():find by where statement failed" + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement():find by where statement failed " + re.getMessage());
            throw re;
        }
        return objects;
    }    
       
    @SuppressWarnings("rawtypes")
	public List findValuesByWhereStatement(final Class clazz, final String values, final String whereStatement) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("select " + values + " from " + clazz.getName() + " where " + whereStatement);
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.findValuesByWhereStatement():find by where statement failed" + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findValuesByWhereStatement():find by where statement failed " + re.getMessage());
            throw re;
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List executeHQLStatement(final String HQLstatement) {
        List objects = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(HQLstatement);
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.executeHQLStatement():executeHQLStatement failed" + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeHQLStatement():executeHQLStatement failed " + re.getMessage());
           

            throw re;
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List executeNamedQuery(final String name) {
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory
                    .getCurrentSession()
                    .getNamedQuery(name);
            List result = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return result;
        } catch (RuntimeException rex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed" + rex.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed " + rex.getMessage());

            throw rex;
            //return new ArrayList();
        }
    }
    
    @SuppressWarnings("rawtypes")
    public List executeNamedQuery(final String name, final Object[] parameters) {
        List objects;
        objects = null;
        int index = 0;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory
                    .getCurrentSession()
                    .getNamedQuery(name);
            for (Object param : parameters) {
                query.setParameter(index, param);
                index++;
            }
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException rex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed" + rex.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed " + rex.getMessage());

            throw rex;
        }
        return objects;
    }
    @SuppressWarnings("rawtypes")
    public int getNumberOfRows(final Class clazz,final Class clazz1, final String whereStatement,final String whereStatement1, Integer page, Integer pageSize, final Session session){ 
    	int count;
        try {
        	count = ((Long)session.createQuery("select count(*) from " + clazz.getName() + " where " + whereStatement + clazz1.getName() + whereStatement1).uniqueResult()).intValue();
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRows():find by where statement failed " + re.getMessage());

            throw re;
        }    
    	
    	return count;
    }
    @SuppressWarnings("rawtypes")
	public List findByWhereStatement_1(final Class clazz,final Class clazz1, final String whereStatement,final String whereStatement1, final int page, final int pageSize, Session session) {
        List objects = null;
        int firstResult = (page - 1) * pageSize;        
        try {
            
            Query query = session.createQuery("from " + clazz.getName() + " where " + whereStatement + clazz1.getName() + whereStatement1);
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
            objects = query.list();            
        } catch (RuntimeException re) {            

        	logger.error("@AbstractGenericDAO.findByWhereStatement():find by where statement failed" + re.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatement():find by where statement failed " + re.getMessage());

            throw re;
        }
        return objects;
    } 
    @SuppressWarnings("rawtypes")
    public List executeNamedQuery(final String name, final String[] paramNames, final Object[] parameters) throws HibernateException {
        List objects;
        objects = null;
        int index = 0;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            Query query = sessionFactory
                    .getCurrentSession()
                    .getNamedQuery(name);
            for (Object param : parameters) {
                query.setParameter(paramNames[index], param);
                index++;
            }
            objects = query.list();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException rex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

            logger.error("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed" + rex.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed " + rex.getMessage());
        	throw rex;

        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public Object findUniqueResult(final Class clazz, final String whereStatement) {
        Object object = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + whereStatement);
            object = query.uniqueResult();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {

            sessionFactory.getCurrentSession().getTransaction().rollback();  
            logger.error("@AbstractGenericDAO.findUniqueResult():find UniqueResult failed" + re.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.findUniqueResult():find UniqueResult failed " + re.getMessage());
        	
            throw re;
        }
        return object;
    }
    
    @SuppressWarnings("rawtypes")
    public Object findFirstRow(final Class clazz, final String whereStatement) {
        Object object = null;
        sessionFactory.getCurrentSession().beginTransaction();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + whereStatement);
            List results = query.list();
            if (results.size() > 0) {
                object = results.get(0);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {

            sessionFactory.getCurrentSession().getTransaction().rollback();  
            logger.error("@AbstractGenericDAO.findFirstRow():find UniqueResult failed" + re.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.findFirstRow():find UniqueResult failed " + re.getMessage());

            throw re;
        }
        return object;
    }
    
    @SuppressWarnings("rawtypes")
    public void refreshObjects(final List objects) {
        for (Object object : objects) {
            sessionFactory.getCurrentSession().refresh(object);
        }
    }

    /**
     * Alternativa para reciclar sesiones
     *
     * @param objects
     */
    @SuppressWarnings("rawtypes")
    public void refreshObjects(final List objects, final Session session) {
        for (Object object : objects) {
            session.refresh(object);
        }
    }
    
    public void refreshObject(final Object object) {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().refresh(object);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    /**
     * ** ALTERNATIVOS ****
     */
    public void refreshObject(final Object object, final Session session) {
        session.refresh(object);
    }

    /**
     * Se debe cerrar la session afuera
     *
     * @param name
     * @param paramNames
     * @param parameters
     * @param session
     * @return
     * @throws HibernateException
     */
    @SuppressWarnings("rawtypes")
    public List executeNamedQuery(final String name, final String[] paramNames, final Object[] parameters, final Session session) throws HibernateException {
        List objects;
        objects = null;
        int index = 0;
        try {
            Query query = session
                    .getNamedQuery(name);
            for (Object param : parameters) {
                query.setParameter(paramNames[index], param);
                index++;
            }
            objects = query.list();
        } catch (HibernateException rex) {

        	logger.error("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed" + rex.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed " + rex.getMessage());

            throw rex;
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List executeNamedQuery(final String name, final Object[] parameters, final Session session) {
        List objects;
        objects = null;
        int index = 0;
        try {
            Query query = session
                    .getNamedQuery(name);
            for (Object param : parameters) {
                query.setParameter(index, param);
                index++;
            }
            objects = query.list();
        } catch (RuntimeException rex) {

        	logger.error("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed" + rex.getMessage());
        	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNamedQuery():executeNamedQuery failed " + rex.getMessage());

            throw rex;
        }
        return objects;
    }
    
    

    
    
    @SuppressWarnings("rawtypes")
    public Object findFirstRow(final Class clazz, final String whereStatement, final Session session) {
        Object object = null;
        try {
            Query query = session.createQuery("from " + clazz.getName() + " where " + whereStatement);
            List results = query.list();
            if (results.size() > 0) {
                object = results.get(0);
            }
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.findFirstRow():find UniqueResult failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.findFirstRow():find UniqueResult failed " + re.getMessage());

            throw re;
        }
        return object;
    }    
    
    @SuppressWarnings({ })
    public void executeInsertUpdateNativeSqlQuery(final String sqlSentence, final Session session) {        
        try {
            Query query = session.createSQLQuery(sqlSentence);            
            query.executeUpdate();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeInsertUpdateNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeInsertUpdateNativeSqlQuery():HibernateException " + e.getMessage());
         	
        }        
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQuery(final String tableName, final String whereStatement, final int page, final int pageSize, final Session session) {
        List<Object> objects = null;
        try {
            Query query = session.createSQLQuery(
                    "select * from " + tableName + " where " + whereStatement);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
        }
        return objects;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQuery(Class clazz, final String queryString, final Session session) {
        List<Object> objects = null;
        try {
            Query query = session.createSQLQuery(queryString);            
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
        }
        return objects;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQuery(final String queryString, final Session session) {
        List<Object> objects = null;
        try {
            Query query = session.createSQLQuery(queryString);            
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
        }
        return objects;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQuery(final String sqlSentence, final int page, final int pageSize, final Session session) {
        List<Object> objects = null;
        int firstResult = (page - 1) * pageSize;
        try {
            Query query = session.createSQLQuery(sqlSentence);
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
            e.printStackTrace();
        }
        return objects;
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQueryList(final String listColumn, final String tableName, final String whereStatement, final int page, final int pageSize, final Session session) {
        List<Object> objects = null;
        try {
        	
            Query query = session.createSQLQuery(
                    "select " + listColumn + " from " + tableName + " where " + whereStatement);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
        }
        return objects;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQuery(final String tableName, final String whereStatement, final Session session) {
        List<Object> objects = null;
        try {
            Query query = session.createSQLQuery(
                    "select * from " + tableName + " where " + whereStatement);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public List executeNativeSqlQuery(final Class clazz, final String tableName, final String whereStatement, final Session session) {
        List objects = null;
        try {
            Query query = session.createSQLQuery(
                    "select * from " + tableName + " where " + whereStatement)
                    .addEntity(clazz);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuery():HibernateException " + e.getMessage());
         	
            e.printStackTrace();
        }
        return objects;
    }
    
    @SuppressWarnings("rawtypes")
    public Object findUniqueResult(final Class clazz, final String whereStatement, final Session session) {
        Object object = null;
        try {
            Query query = session.createQuery("from " + clazz.getName() + " where " + whereStatement);
            object = query.uniqueResult();
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.findUniqueResult():find UniqueResult failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.findUniqueResult():find UniqueResult failed " + re.getMessage());

            throw re;
        }
        return object;
    }    
    
    @SuppressWarnings("rawtypes")
    public List executeNativeScalarSqlQuery(final String query, final String[] scalarNames, final Type scalarTypes[], final Session session) {
        List listOfRowValues = null;
        SQLQuery scalarVariables = session.createSQLQuery(
                query);
        for (int i = 0; i < scalarNames.length; i++) {
            scalarVariables.addScalar(scalarNames[i], scalarTypes[i]);
        }
        try {
            Query executableQuery = scalarVariables;
            listOfRowValues =
                    executableQuery.list();            
        } catch (HibernateException e) {

        	logger.error("@AbstractGenericDAO.executeNativeScalarSqlQuery():HibernateException" + e.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeScalarSqlQuery():HibernateException" + e.getMessage());

            throw e;
        }
        return listOfRowValues;
    }
    
    @SuppressWarnings("rawtypes")
    public int getNumberOfRows(final Class clazz, final Session session){ 
    	Long count;
        try {
        	count = (Long) session.createCriteria(clazz.getName()).setProjection(Projections.rowCount()).uniqueResult();
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());

            throw re;
        }    
    	
    	return count.intValue();
    }
    
    public int getNumberOfRows(final String nameTable, final Session session){ 
    	BigDecimal count;
        try {
        
        	count = (BigDecimal) (session.createSQLQuery("select count(*) from DMRO." + nameTable).uniqueResult());
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());

            throw re;
        }    
    	
    	return count.intValue();
    }
    
    @SuppressWarnings("rawtypes")
    public int getNumberOfRows(final Class clazz, final String whereStatement, final Session session){ 
    	int count;
        try {
        	count = ((Long)session.createQuery("select count(*) from " + clazz.getName() + " where " + whereStatement).uniqueResult()).intValue();
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());

            throw re;
        }    
    	
    	return count;
    }
    
    @SuppressWarnings({"unchecked" })
    public int getNumberOfRows(final String sqlStatement, final Session session, final int cebo){ 
    	List<Object> objects = null;
    	int count;
        try {
        	Query query = session.createSQLQuery(sqlStatement);
        	objects = query.list();
        	count = objects.size();
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());

            throw re;
        }    
    	
    	return count;
    }
    
    
    public int getNumberOfRows(final String nameTable, final String whereStatement, final Session session){ 
    	BigDecimal count;
        try {
        	
        	count = (BigDecimal) (session.createSQLQuery("select count(*) from " + nameTable + " where " + whereStatement).uniqueResult());
        
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRows():find by where statement failed" + re.getMessage());
           

            throw re;
        }    
    	
    	return count.intValue();
    }
    
    public void executeNativeSqlStoreProcedure(final String procedure) {
    	try {
    		sessionFactory.getCurrentSession().beginTransaction();
    		Query query = sessionFactory.getCurrentSession().createSQLQuery(" call " + procedure);
    		query.list();
    		sessionFactory.getCurrentSession().getTransaction().commit();
    	} catch (RuntimeException re) {
    		sessionFactory.getCurrentSession().getTransaction().rollback();

    		logger.error("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
           

    		throw re;
    	}
    }
    
    public void executeNativeSqlStoreProcedure(final String procedure, Session session) {    	
    	try {    		
    		Query query = session.createSQLQuery(" call " + procedure);
    		query.list();    		
    	} catch (RuntimeException re) {
    		sessionFactory.getCurrentSession().getTransaction().rollback();

    		logger.error("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
           

    		throw re;
    	}
    }
    
    public void executeNativeSqlStoreProcedure(final String procedureName, final String parameters) {
    	try {
    		sessionFactory.getCurrentSession().beginTransaction();
    		Query query = sessionFactory.getCurrentSession().createSQLQuery(" call " + procedureName + " " + parameters);
    		query.list();
    		sessionFactory.getCurrentSession().getTransaction().commit();
    	} catch (RuntimeException re) {
    		sessionFactory.getCurrentSession().getTransaction().rollback();

    		logger.error("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
 
    		throw re;
    	}
    }
    
    public void executeNativeSqlStoreProcedure(final String procedureName, final String parameters, final Session session) {    	
    	try {    		
    		Query query = session.createSQLQuery(" call " + procedureName + " " + parameters);
    		query.list();
    		sessionFactory.getCurrentSession().getTransaction().commit();
    	} catch (RuntimeException re) {    		

    		logger.error("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
         	WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlStoreProcedure():find by where statement failed" + re.getMessage());
           

    		throw re;
    	}
    }
    
    @SuppressWarnings("rawtypes")
  	public List findByWhereStatementXfileName(final Class clazz, final String whereStatement) {
          List objects = null;
          sessionFactory.getCurrentSession().beginTransaction();
          try {
              
              Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + whereStatement);
              objects = query.list();
              sessionFactory.getCurrentSession().getTransaction().commit();
          } catch (RuntimeException re) {
              sessionFactory.getCurrentSession().getTransaction().rollback();

              logger.error("@AbstractGenericDAO.findByWhereStatementXfileName():find by where statement failed" + re.getMessage());
              WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatementXfileName():find by where statement failed" + re.getMessage());
             

              throw re;
          }
          return objects;
      }
    
	@SuppressWarnings("rawtypes")
	public List findByWhereStatementXfileName(final Class clazz,
			final String whereStatement, Session session) {
		List objects = null;
		try {
			Query query = session.createQuery("from " + clazz.getName()
					+ " where " + whereStatement);
			objects = query.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException re) {

			logger.error("@AbstractGenericDAO.findByWhereStatementXfileName():find by where statement failed" + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.findByWhereStatementXfileName():find by where statement failed" + re.getMessage());
           

			throw re;
		}
		return objects;
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQueryRangos(final String tableName, final String whereStatement, final int page, final int pageSize, final Session session,int rankinf,int ranksup ,String COLUMNAS) {
        List<Object> objects = null;
     
        try {
            Query query = session.createSQLQuery(
            		"SELECT * FROM (SELECT t.*, ROWNUM AS rn FROM (SELECT " +COLUMNAS +" FROM " +  tableName + " where " + whereStatement + ") t) WHERE rn BETWEEN " + rankinf + " AND " + ranksup + " order by 1");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQueryRangos():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQueryRangos():HibernateException" + e.getMessage());
           
        }
        return objects;
    }
    
  
    @SuppressWarnings("rawtypes")
    public int getNumberOfRowsRango(final Class clazz, Session session){ 
    	Long count;
        try {
        	count = (Long) session.createCriteria(clazz.getName()).setProjection(Projections.rowCount()).uniqueResult();
        } catch (RuntimeException re) {

        	logger.error("@AbstractGenericDAO.getNumberOfRowsRango():find by where statement failed" + re.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRowsRango():find by where statement failed" + re.getMessage());
           

            throw re;
        }    
    	
    	return count.intValue();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQueryRangos(final String tableName, final String whereStatement, final int page, final int pageSize, final Session session ,String COLUMNAS) {
        List<Object> objects = null;
        try {
            Query query = session.createSQLQuery(
            		"SELECT " +COLUMNAS +" FROM " +  tableName + " where " + whereStatement );
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQueryRangos():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQueryRangos():HibernateException" +e.getMessage());
           
        }
        return objects;
    }
    
    public int getNumberOfRowsRango(final String nameTable, String whereStatement, Session session,int rankinf,int ranksup,String COLUMNAS){ 
     	BigDecimal count;
         try {
         	
         	count = (BigDecimal) (session.createSQLQuery("SELECT count(*) FROM (SELECT t.*, ROWNUM AS rn FROM (SELECT " +COLUMNAS +" FROM " + nameTable + " where " + whereStatement + ") t) WHERE rn BETWEEN " + rankinf + " AND " + ranksup + " order by 1").uniqueResult());
         } catch (RuntimeException re) {

        	 logger.error("@AbstractGenericDAO.getNumberOfRowsRango():find by where statement failed" + re.getMessage());
             WriteLogs.writeLogsLine("@AbstractGenericDAO.getNumberOfRowsRango():find by where statement failed" + re.getMessage());

             throw re;
         }    
     	return count.intValue();
     }
    
    @SuppressWarnings("unchecked")
	public  List<Object> executeNativeSqlQuerySentenceA(final String sqlSentence, final Session session) {    
    	List<Object> objects = null;
        try {
        	
            Query query = session.createSQLQuery(sqlSentence); 
            objects =   query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuerySentence():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuerySentence():HibernateException" +e.getMessage());
           
            throw e;
        }
		return objects;        
    }
    

    
    
    
    public void executeNativeSqlQuerySentence(final String sqlSentence, final Session session) {        
        try {
            Query query = session.createSQLQuery(sqlSentence);            
            query.executeUpdate();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuerySentence():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuerySentence():HibernateException" +e.getMessage());
           
            throw e;
        }        
    }
    
    public String executeNativeSqlQuerySentenceMax(final String sqlSentence, final Session session) {        
    	String valor="";
        try {
            Query query = session.createSQLQuery(sqlSentence);        
            valor = query.getQueryString();
            query.executeUpdate();
          
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQuerySentence():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQuerySentence():HibernateException" +e.getMessage());
           
            throw e;
        } 
        
        return valor;
    }
    
   
    
    
    public boolean executeNativeSqlQuerySentence(final String sqlSentence, final Session session, int placebo) {        
        boolean val;
        int res;
    	try {
            Query query = session.createSQLQuery(sqlSentence);            
            res = query.executeUpdate();            
        } catch (HibernateException e) {
        	e.printStackTrace();
            throw e;
        }
    	if(res > 0){
    		val = true;
    	}else{
    		val = false;
    	}
        return val;
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQueryRangosKey(final String tableName, final String whereStatement, final int page, final int pageSize, final Session session ,String COLUMNAS) {
        List<Object> objects = null;
        
        try {
            Query query = session.createSQLQuery(
                        "SELECT " +COLUMNAS +" FROM " +  tableName + " where " + whereStatement + " order by KEY_");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQueryRangosKey():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQueryRangosKey():HibernateException" +e.getMessage());
           
        }
       return objects;
    }

    

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List executeNativeSqlQueryRangosKey(final String tableName, final String whereStatement, final int page, final int pageSize, final Session session,int rankinf,int ranksup ,String COLUMNAS) {
        List<Object> objects = null;
       
        try {
            Query query = session.createSQLQuery(
                        "SELECT * FROM (SELECT t.*, ROWNUM AS rn FROM (SELECT " +COLUMNAS +" FROM " +  tableName + " where " + whereStatement + ") t) WHERE rn BETWEEN " + rankinf + " AND " + ranksup + " order by 1");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objects = query.list();
        } catch (HibernateException e) {
        	logger.error("@AbstractGenericDAO.executeNativeSqlQueryRangosKey():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeNativeSqlQueryRangosKey():HibernateException" +e.getMessage());
          
        }
        return objects;
    }
    
    @SuppressWarnings("unchecked")
	public List<Object> executeQuery(String query_,String app, final Session session) throws HibernateException {
    	try {
    		Query query = session.createSQLQuery(
        			query_).setString("app", app);
        			List<Object> result = query.list();
        			return result;
        } catch (HibernateException e) {

        	logger.error("@AbstractGenericDAO.executeQuery():HibernateException" + e.getMessage());
            WriteLogs.writeLogsLine("@AbstractGenericDAO.executeQuery():HibernateException" +e.getMessage());
          

            throw e;
        }
    }


}
