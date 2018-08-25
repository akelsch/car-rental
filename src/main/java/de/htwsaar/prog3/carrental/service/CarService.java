package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.CarDaoImpl;
import de.htwsaar.prog3.carrental.model.Car;

import java.util.List;

/**
 * Service layer implementation for the {@link CarDaoImpl Car DAO}.
 *
 * @author Arthur Kelsch
 */
public class CarService implements GenericService<Car, Long> {
    private CarDaoImpl carDao;

    public CarService() {
        this.carDao = new CarDaoImpl();
    }

    @Override
    public void persist(Car entity) {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.persist(entity);
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }

    @Override
    public Car findById(Long id) {
        carDao.createEntityManager();
        Car car = carDao.findById(id);
        carDao.closeEntityManager();

        return car;
    }

    @Override
    public List<Car> findAll() {
        carDao.createEntityManager();
        List<Car> cars = carDao.findAll();
        carDao.closeEntityManager();

        return cars;
    }

    @Override
    public void update(Car entity) {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.update(entity);
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }

    @Override
    public void delete(Car entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        carDao.createEntityManager();
        carDao.beginTransaction();
        Car car = carDao.findById(id);
        carDao.delete(car);
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }

    @Override
    public void deleteAll() {
        carDao.createEntityManager();
        carDao.beginTransaction();
        carDao.deleteAll();
        carDao.commitTransaction();
        carDao.closeEntityManager();
    }
}
