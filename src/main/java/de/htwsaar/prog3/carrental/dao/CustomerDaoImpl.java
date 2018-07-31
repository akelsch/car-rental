package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO implementation for the Customer model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class CustomerDaoImpl implements GenericDao<Customer, Long> {
    private static final String PERSISTENCE_UNIT_NAME = "customer";
    private static EntityManager entityManager = PersistenceUtil.getEntityManager(PERSISTENCE_UNIT_NAME);

    public CustomerDaoImpl() {
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
    public void remove(Customer entity) {
        entityManager.remove(entity);
    }
}
