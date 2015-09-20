package com.mjurik.web.crawler.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.mjurik.web.crawler.db.entity.Coin;
import com.mjurik.web.crawler.db.entity.CoinVariant;
import com.mjurik.web.crawler.db.entity.CoinVariantHistory;
import com.mjurik.web.crawler.db.entity.NominalValue;
import com.mjurik.web.crawler.db.entity.NumEuResult;
import com.mjurik.web.crawler.db.entity.Variant;

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

        assertNotNull(result.getId());
    }

    @Test
    public void testNewCoin() {
        Coin coin = new Coin();
        coin.setName("New coin name");
        coin.setNominal(NominalValue.E_100);

        CoinVariant variant = new CoinVariant();
        variant.setReleaseDate(LocalDate.now());
        variant.setVariant(Variant.PROOF);
        coin.addVariant(variant);

        CoinVariantHistory history = new CoinVariantHistory();
        history.setCurrency(Currency.getInstance("EUR"));
        history.setDate(LocalDate.now());
        history.setPrice(new BigDecimal("123.45"));
        variant.addHistory(history);

        CoinPersistence.INSTANCE.persistNewCoin(coin);

        List<Coin> coins = CoinPersistence.INSTANCE.listAll();
        assertNotNull(coins);
        assertEquals(1, coins.size());
    }
}
