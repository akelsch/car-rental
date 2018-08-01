package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.RentalDaoImpl;
import de.htwsaar.prog3.carrental.model.Rental;

import java.util.List;

/**
 * Service layer implementation for the {@link RentalDaoImpl Rental DAO}.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class RentalService implements GenericService<Rental, Long> {
    private RentalDaoImpl rentalDao;

    public RentalService() {
        this.rentalDao = new RentalDaoImpl();
    }

    @Override
    public void persist(Rental entity) {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.persist(entity);
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }

    @Override
    public Rental findById(Long id) {
        rentalDao.createEntityManager();
        Rental rental = rentalDao.findById(id);
        rentalDao.closeEntityManager();

        return rental;
    }

    @Override
    public List<Rental> findAll() {
        rentalDao.createEntityManager();
        List<Rental> employees = rentalDao.findAll();
        rentalDao.closeEntityManager();

        return employees;
    }

    @Override
    public void update(Rental entity) {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.update(entity);
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }

    @Override
    public void deleteById(Long id) {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        Rental rental = rentalDao.findById(id);
        rentalDao.delete(rental);
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }

    @Override
    public void deleteAll() {
        rentalDao.createEntityManager();
        rentalDao.beginTransaction();
        rentalDao.deleteAll();
        rentalDao.commitTransaction();
        rentalDao.closeEntityManager();
    }
}
