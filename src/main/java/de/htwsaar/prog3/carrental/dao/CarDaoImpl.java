package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO implementation for the Car model.
 *
 * @author Arthur Kelsch
 */
public class CarDaoImpl implements GenericDao<Car, Long> {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CarDaoImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("car-rental");
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
    public void delete(Car entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Car").executeUpdate();
    }

    public void createEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager() {
        entityManager.close();
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}
