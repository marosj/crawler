package com.mjurik.web.crawler.db;

import com.mjurik.web.crawler.db.entity.NumEuResult;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marian Jurik on 20.11.2015.
 */
public class NumEuTestSelectSame {

    @Test
    public void testSame() {

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from NumEuResult").executeUpdate();
        em.getTransaction().commit();

        em.close();


        NumEuResult processed = new NumEuResult();
        processed.setName("123 EURO - Anton Bernolák – 250. výročie narodenia - vymyslene");
        processed.setPath("/mince/same/bernolak");
        processed.setPrice("32 €");
        processed.setEan("EAN: MIN1931");
        processed.setVariant("PROOF");
        processed.setStartDate(LocalDate.now().minusDays(1));
        processed.setStartDateTime(LocalDateTime.now());
        processed.setProcessTime(LocalDateTime.now().minusDays(1).plusHours(1));
        processed.setProcessed(true);

        NumEuPersistence.INSTANCE.persist(processed);
        assertNotNull(processed.getId());

        NumEuResult processedAdditional = new NumEuResult();
        processedAdditional.setName("123 EURO - Anton Bernolák – 250. výročie narodenia - vymyslene");
        processedAdditional.setPath("/mince/same/bernolak/mimo");
        processedAdditional.setPrice("32 €");
        processedAdditional.setEan("EAN: iny");
        processedAdditional.setVariant("PROOF");
        processedAdditional.setStartDate(LocalDate.now().minusDays(1));
        processedAdditional.setStartDateTime(LocalDateTime.now());
        processedAdditional.setProcessTime(LocalDateTime.now().minusDays(1).plusHours(1));
        processedAdditional.setProcessed(true);

        NumEuPersistence.INSTANCE.persist(processedAdditional);
        assertNotNull(processedAdditional.getId());

        NumEuResult unprocessed = new NumEuResult();
        unprocessed.setName("123 EURO - Anton Bernolák – 250. výročie narodenia - vymyslene");
        unprocessed.setPath("/mince/same/bernolak");
        unprocessed.setPrice("32 €");
        unprocessed.setEan("EAN: MIN1931");
        unprocessed.setVariant("PROOF");
        unprocessed.setStartDate(LocalDate.now().minusDays(1));
        unprocessed.setStartDateTime(LocalDateTime.now());

        NumEuPersistence.INSTANCE.persist(unprocessed);
        assertNotNull(unprocessed.getId());

        NumEuResult unprocessedDifferent = new NumEuResult();
        unprocessedDifferent.setName("123 EURO - Anton Bernolák – 250. výročie narodenia - vymyslene");
        unprocessedDifferent.setPath("/mince/same/bernolak/ina/cesta");
        unprocessedDifferent.setPrice("32 €");
        unprocessedDifferent.setEan("EAN: MIN1931");
        unprocessedDifferent.setVariant("PROOF");
        unprocessedDifferent.setStartDate(LocalDate.now().minusDays(1));
        unprocessedDifferent.setStartDateTime(LocalDateTime.now());

        NumEuPersistence.INSTANCE.persist(unprocessedDifferent);
        assertNotNull(unprocessedDifferent.getId());

        List<NumEuResult> results = NumEuPersistence.INSTANCE.listUnprocessedWithProcessedMatch();
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(unprocessed, results.get(0));
    }
}
