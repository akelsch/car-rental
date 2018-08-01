package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO implementation for the Customer model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class CustomerDaoImpl implements GenericDao<Customer, Long> {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CustomerDaoImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("car-rental");
    }

    @Override
    public void persist(Customer entity) {
        entityManager.persist(entity);
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {
        return (List<Customer>) entityManager.createQuery("SELECT c FROM Customer c").getResultList();
    }

    @Override
    public void update(Customer entity) {
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findById(id);
        entityManager.remove(customer);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Customer").executeUpdate();
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
