package it.academy.dao;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.Driver;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.model.EmployeeFullName;
import it.academy.model.VisitorCount;
import org.apache.commons.dbcp2.BasicDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@PropertySource(value = "classpath:datasource.properties")
@Configuration
@ComponentScan(basePackages = "it.academy")
@EnableTransactionManagement
public class DaoConfiguration {

    @Autowired
    Environment env;

//    @Bean
//    public DataSource dataSource(){
//        BasicDataSource dataSource= new BasicDataSource();
//        dataSource.setUrl(env.getProperty("datasource.url"));
//        dataSource.setDriverClassName(Driver.class.getName());
//        dataSource.setUsername(env.getProperty("datasource.username"));
//        dataSource.setPassword(env.getProperty("datasource.password"));
//        dataSource.setInitialSize(20);
//        dataSource.setMaxTotal(30);
//        return dataSource;
//    }
        @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource= new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setUser(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setJdbcUrl(env.getProperty("datasource.url"));
        dataSource.setMinPoolSize(10);
        dataSource.setMaxPoolSize(250);
        dataSource.setAcquireRetryAttempts(0);
        dataSource.setMaxConnectionAge(100);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean
                = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(
                Department.class, Employee.class, EmployeeFullName.class, VisitorCount.class
        );
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }
    @Bean
    public PlatformTransactionManager transactionManager(
            SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
