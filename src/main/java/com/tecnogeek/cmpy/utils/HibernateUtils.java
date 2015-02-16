package com.tecnogeek.cmpy.utils;

//import com.taca.operaciones.cipher.Crypt;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.taca.mdmro.controller.utils.WriteLogs;
//import com.taca.mdmro.persistence.service.RetrieveCatalogInformationService;
//import com.taca.mdmro.utils.ConstantParameters;

public final class HibernateUtils {
	
private static final Logger logger = LoggerFactory.getLogger(HibernateUtils.class);
private static final SessionFactory sessionFactory;
//private static final ServiceRegistry serviceRegistry;

//private HibernateUtils() {
//}


static {
    try {        
        /*Configuration config = new Configuration().configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                config.getProperties()).build();
        sessionFactory = config.buildSessionFactory(serviceRegistry);*/ 
        Configuration config = new Configuration().configure();          
        sessionFactory = config.buildSessionFactory();
    } catch (HibernateException ex) {
  	  logger.error("@HibernateUtils.HibernateUtils(): Initial SessionFactory creation failed" + ex.getMessage());
		  WriteLogs.writeLogsLine("@HibernateUtils.HibernateUtils(): Initial SessionFactory creation failed" + ex.getMessage());
		  throw new ExceptionInInitializerError(ex);
    } catch (Exception e){
  	  logger.error("@HibernateUtils.HibernateUtils(): Initial SessionFactory creation failed" + e.getMessage());
		  WriteLogs.writeLogsLine("@HibernateUtils.HibernateUtils(): Initial SessionFactory creation failed" + e.getMessage());
		  throw new ExceptionInInitializerError(e);
    }
}

public static SessionFactory getSessionFactory() {
    return sessionFactory;
}

public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
}

public static void attachObject(Object entity) {
    HibernateUtils.getSessionFactory().getCurrentSession().update(entity);
}


}