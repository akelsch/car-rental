package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO layer implementation for the {@link Employee Employee model}.
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
    public void delete(Employee entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Employee").executeUpdate();
    }

    @Override
    public void createEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void closeEntityManager() {
        entityManager.close();
    }

    @Override
    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}
