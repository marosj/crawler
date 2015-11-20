package com.mjurik.web.crawler.db;

import com.mjurik.web.crawler.db.entity.NumEuResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

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
        try {
            return em.createQuery("select res from NumEuResult res where res.processed = false", NumEuResult.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<NumEuResult> listUnprocessedWithProcessedMatch() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            return em.createQuery(
                    "select res from NumEuResult res where (res.processed = false or res.processed is null) " +
                    " and exists (select processed from NumEuResult proc where " +
                            " proc.path=res.path and proc.ean=res.ean and " +
                            " proc.name=res.name and proc.variant=res.variant and" +
                            " proc.processed = true )",
                    NumEuResult.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void setAsProcessed(String id) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            NumEuResult entity = em.find(NumEuResult.class, id);
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
