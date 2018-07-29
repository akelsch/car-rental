package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDao;
import de.htwsaar.prog3.carrental.dao.CarDaoImpl;
import de.htwsaar.prog3.carrental.model.Car;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

/**
 * Car service layer providing methods for working with the Car model.
 *
 * @author Arthur Kelsch
 */
public class CarService {
    private GenericDao<Car, Long> carDao = new CarDaoImpl();

    public CarService() {
    }

    public void persist(Car entity) {
        PersistenceUtil.beginTransaction();
        carDao.persist(entity);
        PersistenceUtil.commitTransaction();
    }

    public Car findById(Long id) {
        PersistenceUtil.beginTransaction();
        Car car = carDao.findById(id);
        PersistenceUtil.commitTransaction();

        return car;
    }

    public void update(Car entity) {
        PersistenceUtil.beginTransaction();
        carDao.update(entity);
        PersistenceUtil.commitTransaction();
    }

    public void remove(Car entity) {
        PersistenceUtil.beginTransaction();
        carDao.remove(entity);
        PersistenceUtil.commitTransaction();
    }
}
