package de.htwsaar.prog3.carrental.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utility class providing functionality for working with JPA.
 *
 * @author Arthur Kelsch
 */
public class PersistenceUtil {
    private static EntityManager entityManager;

    private PersistenceUtil() {
    }

    public static EntityManager getEntityManager(String persistenceUnitName) {
        if (entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
            entityManager = entityManagerFactory.createEntityManager();
        }

        return entityManager;
    }

    public static void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public static void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}
