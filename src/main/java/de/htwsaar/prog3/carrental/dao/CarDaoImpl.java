package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO implementation for the Car model.
 *
 * @author Arthur Kelsch
 */
public class CarDaoImpl implements GenericDao<Car, Long> {
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
    @SuppressWarnings("unchecked")
    public List<Car> findAll() {
        return (List<Car>) entityManager.createQuery("SELECT c FROM Car c").getResultList();
    }

    @Override
    public void update(Car entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Car entity) {
        entityManager.remove(entity);
    }
}
