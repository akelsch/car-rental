package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDao;
import de.htwsaar.prog3.carrental.dao.RentalDaoImpl;
import de.htwsaar.prog3.carrental.model.Rental;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

/**
 * Rental service layer providing methods for working with the Rental model.
 *
 * @author Julian Quint
 */
public class RentalService {
    private GenericDao<Rental, Long> rentalDao = new RentalDaoImpl();

    public RentalService() {
    }

    public void persist(Rental entity) {
        PersistenceUtil.beginTransaction();
        rentalDao.persist(entity);
        PersistenceUtil.commitTransaction();
    }

    public Rental findById(Long id) {
        PersistenceUtil.beginTransaction();
        Rental rental = rentalDao.findById(id);
        PersistenceUtil.commitTransaction();

        return rental;
    }

    public void update(Rental entity) {
        PersistenceUtil.beginTransaction();
        rentalDao.update(entity);
        PersistenceUtil.commitTransaction();
    }

    public void remove(Rental entity) {
        PersistenceUtil.beginTransaction();
        rentalDao.remove(entity);
        PersistenceUtil.commitTransaction();
    }
}
