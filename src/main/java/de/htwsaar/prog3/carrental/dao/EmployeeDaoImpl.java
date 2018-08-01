package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO implementation for the Employee model.
 *
 * @author Julian Quint, Arthur Kelsch
 */
public class EmployeeDaoImpl implements GenericDao<Employee, Long> {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EmployeeDaoImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("car-rental");
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
    public void deleteById(Long id) {
        Employee employee = findById(id);
        entityManager.remove(employee);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Employee").executeUpdate();
    }

    public void createEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager() {
        entityManager.close();
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}
