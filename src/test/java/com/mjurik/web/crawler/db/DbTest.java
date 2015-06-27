package com.mjurik.web.crawler.db;

import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Created by Marian Jurik on 27.6.2015.
 */
public class DbTest {

    @Test
    public void testInsert() {
        Contact con = new Contact(1, "Maros", "mjurik@nieco.com");

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.persist(con);
        em.getTransaction().commit();

        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
