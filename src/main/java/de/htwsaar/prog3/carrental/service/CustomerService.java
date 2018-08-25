package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.CustomerDaoImpl;
import de.htwsaar.prog3.carrental.model.Customer;

import java.util.List;

/**
 * Service layer implementation for the {@link CustomerDaoImpl Customer DAO}.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class CustomerService implements GenericService<Customer, Long> {
    private CustomerDaoImpl customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDaoImpl();
    }

    @Override
    public void persist(Customer entity) {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        customerDao.persist(entity);
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }

    @Override
    public Customer findById(Long id) {
        customerDao.createEntityManager();
        Customer customer = customerDao.findById(id);
        customerDao.closeEntityManager();

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        customerDao.createEntityManager();
        List<Customer> customers = customerDao.findAll();
        customerDao.closeEntityManager();

        return customers;
    }

    @Override
    public void update(Customer entity) {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        customerDao.update(entity);
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }

    @Override
    public void delete(Customer entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        Customer customer = customerDao.findById(id);
        customerDao.delete(customer);
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }

    @Override
    public void deleteAll() {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        customerDao.deleteAll();
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }
}
