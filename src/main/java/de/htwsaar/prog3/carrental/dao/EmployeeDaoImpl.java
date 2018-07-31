package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;

/**
 * DAO implementation for the Employee model.
 *
 * @author Julian Quint
 */
public class EmployeeDaoImpl implements GenericDao<Employee, Long> {
    private static final String PERSISTENCE_UNIT_NAME = "employee";
    private static EntityManager entityManager = PersistenceUtil.getEntityManager(PERSISTENCE_UNIT_NAME);

    public EmployeeDaoImpl() {
    }

    @Override
    public void persist(Employee entity) {
        entityManager.persist(entity);
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void update(Employee entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Employee entity) {
        entityManager.remove(entity);
    }
}
