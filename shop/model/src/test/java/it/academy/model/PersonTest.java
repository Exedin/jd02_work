package it.academy.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.Serializable;
import java.sql.Date;

import static org.junit.Assert.*;

@FixMethodOrder
public class PersonTest {
    private SessionFactory factory;

    @org.junit.Before
    public void setUp() throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.test.xml")
                .build();
        factory=new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    @Test
    public void create(){
        //Given
        Person person=new Person();
//        person.setId(1L);
        person.setName("Natalia");
        person.setSecondName("Ivanova");
        person.setDateOfBirth(Date.valueOf("1980-01-01"));
        person.setStatus(Status.NEW);
        person.setComment(new String[]{"Comment1", "Comment2"});
        ShopUser shopUser=new ShopUser();
        shopUser.setUsername("n_ivanova");
        shopUser.setPassword("secret");

        person.setShopUser(shopUser);

        //When
        Session session = factory.openSession();
        Transaction tx=null;
        Serializable id=null;
        try {
            tx = session.beginTransaction();
            session.save(shopUser);
            id = session.save(person);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        //Then
        assertNotNull(id);
        //Clean


    }
    @Test
    public void delete (){
        //Given
        String uuid="4028e74a773a5bbc01773a5bbe0b0000";
        Session session = factory.openSession();
        Person person = session.get(Person.class, uuid);
        //When
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            session.delete(person);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }

        //Then
        assertNull(session.get(Person.class, uuid));
        session.close();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        factory.close();
    }
}