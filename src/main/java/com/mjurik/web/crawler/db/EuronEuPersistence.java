package com.mjurik.web.crawler.db;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.entity.EuronEuResult;

/**
 * Created by Marian Jurik on 27.6.2015.
 */
public enum EuronEuPersistence {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(EuronEuPersistence.class);

    public void persist(EuronEuResult entity) {
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

    public List<EuronEuResult> listUnprocessed() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            return em.createQuery("select res from EuronEuResult res where res.processed = false", EuronEuResult.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void setAsProcessed(String id) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            EuronEuResult entity = em.find(EuronEuResult.class, id);
            if (entity != null) {
                entity.setProcessed(Boolean.TRUE);
                em.getTransaction().begin();
                em.persist(entity);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

}
