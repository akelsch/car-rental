package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDaoImpl;
import de.htwsaar.prog3.carrental.model.Customer;

/**
 * Service layer implementation for the {@link Customer} model.
 *
 * @author Julian Quint, Arthur Kelsch
 * @see GenericServiceImpl
 */
public class CustomerService extends GenericServiceImpl<Customer> {
    public CustomerService() {
        dao = new GenericDaoImpl<>(Customer.class);
    }
}
