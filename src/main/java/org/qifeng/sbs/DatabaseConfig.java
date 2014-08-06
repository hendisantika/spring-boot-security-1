/**
 * 
 */
package org.qifeng.sbs;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jackho
 *
 */
@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScan("org.qifeng.sbs")
public class DatabaseConfig {

	private static final String DATABASE_DRIVER = "db.driver";
	private static final String DATABASE_PASSWORD  = "db.password";
	private static final String DATABASE_URL = "db.url";
	private static final String DATABASE_USERNAME = "db.username";
	
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    
    @Resource
    private Environment env;
    
    @Bean 
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	
    	dataSource.setDriverClassName(env.getRequiredProperty(DATABASE_DRIVER));
    	dataSource.setUrl(env.getRequiredProperty(DATABASE_URL));
    	dataSource.setUsername(env.getRequiredProperty(DATABASE_USERNAME));
    	dataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));
    	
    	System.out.println(env.getRequiredProperty(DATABASE_DRIVER));
    	System.out.println(env.getRequiredProperty(DATABASE_URL));
    	System.out.println(env.getRequiredProperty(DATABASE_PASSWORD));
    	System.out.println(env.getRequiredProperty(HIBERNATE_DIALECT));
    	return dataSource;
    }
    
    private Properties hibProperties() {
    	Properties properties = new Properties();
    	properties.put(HIBERNATE_DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
    	properties.put(HIBERNATE_SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
    	return properties;
    }
    
    
    
    @Bean 
    public HibernateTransactionManager  transactionManager() {
    	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    	transactionManager.setSessionFactory(sessionFactory().getObject());
    	return transactionManager;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    	sessionFactoryBean.setDataSource(dataSource());
    	sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(ENTITYMANAGER_PACKAGES_TO_SCAN));
    	sessionFactoryBean.setHibernateProperties(hibProperties());
    	return sessionFactoryBean;
    }
    
}
