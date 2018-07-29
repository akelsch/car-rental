package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;

/**
 * DAO implementation for the Car model.
 *
 * @author Arthur Kelsch
 */
public class CarDaoImpl implements CarDao<Car, Long> {
    private static final String PERSISTENCE_UNIT_NAME = "car";
    private static EntityManager entityManager = PersistenceUtil.getEntityManager(PERSISTENCE_UNIT_NAME);

    public CarDaoImpl() {
    }

    @Override
    public void persist(Car entity) {
        entityManager.persist(entity);
    }

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public void update(Car entity) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Car entity) {
        entityManager.remove(entity);
    }
}
