package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.CarDaoImpl;
import de.htwsaar.prog3.carrental.model.Car;

import java.util.List;

/**
 * Car service layer providing methods for working with the Car model.
 *
 * @author Arthur Kelsch
 */
public class CarService {
    private CarDaoImpl carDao;

    public CarService() {
        this.carDao = new CarDaoImpl();
    }

    public void persist(Car entity) {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.persist(entity);
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }

    public Car findById(Long id) {
        carDao.createEntityManager();
        Car car = carDao.findById(id);
        carDao.closeEntityManager();

        return car;
    }

    public List<Car> findAll() {
        carDao.createEntityManager();
        List<Car> cars = carDao.findAll();
        carDao.closeEntityManager();

        return cars;
    }

    public void update(Car entity) {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.update(entity);
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }

    public void deleteById(Long id) {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.deleteById(id);
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }

    public void deleteAll() {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.deleteAll();
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }
}
