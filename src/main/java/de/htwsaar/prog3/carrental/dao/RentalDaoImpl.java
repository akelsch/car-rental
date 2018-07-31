package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO implementation for the Rental model.
 *
 * @author Julian Quint, Arthur Kelsch
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
    @SuppressWarnings("unchecked")
    public List<Rental> findAll() {
        return (List<Rental>) entityManager.createQuery("SELECT e FROM Rental e").getResultList();
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
