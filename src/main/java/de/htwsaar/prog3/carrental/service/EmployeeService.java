package de.htwsaar.prog3.carrental.service;


import de.htwsaar.prog3.carrental.dao.EmployeeDaoImpl;
import de.htwsaar.prog3.carrental.model.Employee;

import java.util.List;

/**
 * Employee service layer providing methods for working with the Employee model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class EmployeeService {
    private EmployeeDaoImpl employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    public void persist(Employee entity) {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        employeeDao.persist(entity);
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }

    public Employee findById(Long id) {
        employeeDao.createEntityManager();
        Employee employee = employeeDao.findById(id);
        employeeDao.closeEntityManager();

        return employee;
    }

    public List<Employee> findAll() {
        employeeDao.createEntityManager();
        List<Employee> employees = employeeDao.findAll();
        employeeDao.closeEntityManager();

        return employees;
    }

    public void update(Employee entity) {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        employeeDao.update(entity);
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }

    public void deleteById(Long id) {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        Employee employee = employeeDao.findById(id);
        employeeDao.delete(employee);
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }

    public void deleteAll() {
        employeeDao.createEntityManager();
        employeeDao.beginTransaction();
        employeeDao.deleteAll();
        employeeDao.commitTransaction();
        employeeDao.closeEntityManager();
    }
}
