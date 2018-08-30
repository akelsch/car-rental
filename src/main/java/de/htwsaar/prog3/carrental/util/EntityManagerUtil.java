package de.htwsaar.prog3.carrental.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class makes sure that we only use a single EntityManagerFactory
 * instance as these are heavyweight objects.
 *
 * @author Arthur Kelsch
 */
public final class EntityManagerUtil {
    private static final String PERSISTENCE_UNIT_NAME = "car-rental";

    private static EntityManagerFactory emf;

    private EntityManagerUtil() {
    }

    static {
        createEntityManagerFactory();
    }

    /**
     * Initializes the {@link EntityManagerFactory} field {@link #emf} if it is null or currently closed.
     */
    private static void createEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
    }

    /**
     * Closes the {@link EntityManagerFactory} if it is not null and currently open.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Provides {@link EntityManager} objects for the DAO layer.
     *
     * @return an entity manager object
     */
    public static EntityManager getEntityManager() {
        // Make sure the entity manager factory exists!
        // -> avoiding NullPointerException after closing the entity manager factory
        createEntityManagerFactory();

        return emf.createEntityManager();
    }
}
