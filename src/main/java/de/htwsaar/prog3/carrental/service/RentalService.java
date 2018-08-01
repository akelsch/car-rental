package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.RentalDaoImpl;
import de.htwsaar.prog3.carrental.model.Rental;

import java.util.List;

/**
 * Rental service layer providing methods for working with the Rental model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class RentalService {
    private RentalDaoImpl rentalDao;

    public RentalService() {
        this.rentalDao = new RentalDaoImpl();
    }

    public void persist(Rental entity) {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.persist(entity);
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }

    public Rental findById(Long id) {
        rentalDao.createEntityManager();
        Rental rental = rentalDao.findById(id);
        rentalDao.closeEntityManager();

        return rental;
    }

    public List<Rental> findAll() {
        rentalDao.createEntityManager();
        List<Rental> employees = rentalDao.findAll();
        rentalDao.closeEntityManager();

        return employees;
    }

    public void update(Rental entity) {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.update(entity);
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }

    public void deleteById(Long id) {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.deleteById(id);
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }

    public void deleteAll() {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.deleteAll();
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }
}
