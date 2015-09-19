package com.mjurik.web.crawler.db;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.entity.IgnoredPath;

/**
 * Created by Marian Jurik on 19.9.2015.
 */
public enum IgnoredPathPersistence {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(NumEuPersistence.class);

    public void persistIgnoredPath(String source, String path) {
        LOGGER.debug("Persisting ignored {}: {}", source, path);
        IgnoredPath entity = new IgnoredPath();
        entity.setPath(path);
        entity.setSource(source);

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public Collection<String> getIgnoredPaths(String source) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try {
            List<String> result = em.createQuery("select ip.path from IgnoredPath ip where ip.source=:source ", String.class)
                    .setParameter("source", source)
                    .getResultList();
            if (result != null) {
                return result;
            } else {
                return Collections.emptyList();
            }
        } finally {
            em.close();
        }
    }
}
