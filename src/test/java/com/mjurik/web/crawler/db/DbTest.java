package com.mjurik.web.crawler.db;

import com.mjurik.web.crawler.db.entity.NumEuResult;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Marian Jurik on 27.6.2015.
 */
public class DbTest {

    @Test
    public void testInsert() {
        NumEuResult result = new NumEuResult();
        result.setName("10 EURO/2012 - Anton Bernolák – 250. výročie narodeniaa");
        result.setPath("/mince/nieco/prva");
        result.setPrice("32 €");
        result.setEan("EAN: MIN1931");
        result.setVariant("PROOF");
        result.setStartDate(LocalDate.now());
        result.setStartDateTime(LocalDateTime.now());
        result.setProcessTime(LocalDateTime.now().plusHours(1));

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.persist(result);
        em.getTransaction().commit();

        em.close();
        PersistenceManager.INSTANCE.close();

        assertNotNull(result.getId());
    }
}
