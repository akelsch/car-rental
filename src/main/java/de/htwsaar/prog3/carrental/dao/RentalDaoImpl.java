package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;

/**
 * DAO implementation for the Rental model.
 *
 * @author Julian Quint
 */
public class RentalDaoImpl implements GenericDao<Rental, Long> {
    private static final String PERSISTENCE_UNIT_NAME = "rental";
    private static EntityManager entityManager = PersistenceUtil.getEntityManager(PERSISTENCE_UNIT_NAME);

    public RentalDaoImpl() {
    }

    @Override
    public void persist(Rental entity) {
        entityManager.persist(entity);
    }

    @Override
    public Rental findById(Long id) {
        return entityManager.find(Rental.class, id);
    }

    @Override
    public void update(Rental entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Rental entity) {
        entityManager.remove(entity);
    }
}
