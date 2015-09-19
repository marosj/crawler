package com.mjurik.web.crawler.db;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.entity.NumEuResult;

/**
 * Created by Marian Jurik on 27.6.2015.
 */
public enum NumEuPersistence {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(NumEuPersistence.class);

    public void persist(NumEuResult entity) {
        LOGGER.debug("Persisting {}", entity);
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public List<NumEuResult> listUnprocessed() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        return em.createQuery("select res from NumEuResult res where res.processed = false", NumEuResult.class).getResultList();
    }
}
