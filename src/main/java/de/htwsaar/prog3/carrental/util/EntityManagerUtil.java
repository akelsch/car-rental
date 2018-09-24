package de.htwsaar.prog3.carrental.util;

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

    /**
     * Initializes the {@link EntityManagerFactory} field {@link #emf} if it is null or currently closed.
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }

        return emf;
    }

    /**
     * Closes the {@link EntityManagerFactory} if it is not null and currently open.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
