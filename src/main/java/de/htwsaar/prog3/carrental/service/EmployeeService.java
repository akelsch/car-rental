package de.htwsaar.prog3.carrental.service;


import de.htwsaar.prog3.carrental.dao.EmployeeDaoImpl;
import de.htwsaar.prog3.carrental.dao.GenericDao;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

/**
 * Employee service layer providing methods for working with the Employee model.
 *
 * @author Julian Quint
 */
public class EmployeeService {
    private GenericDao<Employee, Long> employeeDao = new EmployeeDaoImpl();

    public EmployeeService() {
    }

    public void persist(Employee entity) {
        PersistenceUtil.beginTransaction();
        employeeDao.persist(entity);
        PersistenceUtil.commitTransaction();
    }

    public Employee findById(Long id) {
        PersistenceUtil.beginTransaction();
        Employee employee = employeeDao.findById(id);
        PersistenceUtil.commitTransaction();

        return employee;
    }

    public void update(Employee entity) {
        PersistenceUtil.beginTransaction();
        employeeDao.update(entity);
        PersistenceUtil.commitTransaction();
    }

    public void remove(Employee entity) {
        PersistenceUtil.beginTransaction();
        employeeDao.remove(entity);
        PersistenceUtil.commitTransaction();
    }
}
