package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO implementation for the Employee model.
 *
 * @author Julian Quint, Arthur Kelsch
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
    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        return (List<Employee>) entityManager.createQuery("SELECT e FROM Employee e").getResultList();
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
