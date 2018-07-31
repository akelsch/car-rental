package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.CustomerDaoImpl;
import de.htwsaar.prog3.carrental.dao.GenericDao;
import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

/**
 * Customer service layer providing methods for working with the Customer model.
 *
 * @author Julian Quint
 */
public class CustomerService {
    private GenericDao<Customer, Long> customerDao = new CustomerDaoImpl();

    public CustomerService() {
    }

    public void persist(Customer entity) {
        PersistenceUtil.beginTransaction();
        customerDao.persist(entity);
        PersistenceUtil.commitTransaction();
    }

    public Customer findById(Long id) {
        PersistenceUtil.beginTransaction();
        Customer customer = customerDao.findById(id);
        PersistenceUtil.commitTransaction();

        return customer;
    }

    public void update(Customer entity) {
        PersistenceUtil.beginTransaction();
        customerDao.update(entity);
        PersistenceUtil.commitTransaction();
    }

    public void remove(Customer entity) {
        PersistenceUtil.beginTransaction();
        customerDao.remove(entity);
        PersistenceUtil.commitTransaction();
    }
}
