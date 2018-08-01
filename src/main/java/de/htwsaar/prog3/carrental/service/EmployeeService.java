package de.htwsaar.prog3.carrental.service;


import de.htwsaar.prog3.carrental.dao.EmployeeDaoImpl;
import de.htwsaar.prog3.carrental.model.Employee;

import java.util.List;

/**
 * Service layer implementation for the {@link EmployeeDaoImpl Employee DAO}.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class EmployeeService implements GenericService<Employee, Long> {
    private EmployeeDaoImpl employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void persist(Employee entity) {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        employeeDao.persist(entity);
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }

    @Override
    public Employee findById(Long id) {
        employeeDao.createEntityManager();
        Employee employee = employeeDao.findById(id);
        employeeDao.closeEntityManager();

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        employeeDao.createEntityManager();
        List<Employee> employees = employeeDao.findAll();
        employeeDao.closeEntityManager();

        return employees;
    }

    @Override
    public void update(Employee entity) {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        employeeDao.update(entity);
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }

    @Override
    public void deleteById(Long id) {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        Employee employee = employeeDao.findById(id);
        employeeDao.delete(employee);
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }

    @Override
    public void deleteAll() {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        employeeDao.deleteAll();
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }
}
