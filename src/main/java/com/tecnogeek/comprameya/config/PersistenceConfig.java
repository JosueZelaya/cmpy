/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;


import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author jzelaya
 */

@Configuration
@EnableJpaRepositories(basePackages="com.tecnogeek.comprameya.repositories")
public class PersistenceConfig {
 
    @Autowired 
    private Environment env;
    
    @Bean
    public PlatformTransactionManager transactionManager()
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory()
    {
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        
        Properties jpaProperties = new Properties();
        //jpaProperties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.max_fetch_depth",env.getProperty("hibernate.max_fetch_depth"));
        jpaProperties.put("hibernate.jdbc.fetch_size",env.getProperty("hibernate.jdbc.fetch_size"));
        jpaProperties.put("hibernate.jdbc.batch_size",env.getProperty("hibernate.jdbc.batch_size"));
        jpaProperties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.current_session_context_class",env.getProperty("hibernate.current_session_context_class"));
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.tecnogeek.comprameya.entidad");
        factory.setJpaProperties(jpaProperties);
        
        factory.afterPropertiesSet();
        
        return factory.getObject();
    }
    
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
    
}
