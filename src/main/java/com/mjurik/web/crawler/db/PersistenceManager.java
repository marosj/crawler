package com.mjurik.web.crawler.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;

    PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("jpa-mince-pu");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}