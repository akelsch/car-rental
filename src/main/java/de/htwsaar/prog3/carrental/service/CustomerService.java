package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.CustomerDaoImpl;
import de.htwsaar.prog3.carrental.model.Customer;

import java.util.List;

/**
 * Customer service layer providing methods for working with the Customer model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class CustomerService {
    private CustomerDaoImpl customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDaoImpl();
    }

    public void persist(Customer entity) {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        customerDao.persist(entity);
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }

    public Customer findById(Long id) {
        customerDao.createEntityManager();
        Customer customer = customerDao.findById(id);
        customerDao.closeEntityManager();

        return customer;
    }

    public List<Customer> findAll() {
        customerDao.createEntityManager();
        List<Customer> customers = customerDao.findAll();
        customerDao.closeEntityManager();

        return customers;
    }

    public void update(Customer entity) {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        customerDao.update(entity);
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }

    public void deleteById(Long id) {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        Customer customer = customerDao.findById(id);
        customerDao.delete(customer);
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }

    public void deleteAll() {
        customerDao.createEntityManager();
        customerDao.beginTransaction();
        customerDao.deleteAll();
        customerDao.commitTransaction();
        customerDao.closeEntityManager();
    }
}
