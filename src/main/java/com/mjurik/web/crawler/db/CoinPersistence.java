package com.mjurik.web.crawler.db;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.entity.Coin;

/**
 * Created by Marian Jurik on 20.9.2015.
 */
public enum CoinPersistence {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinPersistence.class);

    public void persistNewCoin(Coin newCoin) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newCoin);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public List<Coin> listAll() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            return em.createQuery("select c from Coin c", Coin.class).getResultList();
        } finally {
            em.close();
        }

    }
}
