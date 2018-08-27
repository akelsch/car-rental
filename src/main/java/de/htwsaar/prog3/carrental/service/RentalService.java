package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDaoImpl;
import de.htwsaar.prog3.carrental.model.Rental;

/**
 * Service layer implementation for the for the {@link Rental} model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class RentalService extends GenericServiceImpl<Rental> {
    public RentalService() {
        dao = new GenericDaoImpl<>(Rental.class);
    }
}
