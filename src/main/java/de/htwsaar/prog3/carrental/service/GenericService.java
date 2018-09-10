package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDao;

import java.util.List;

/**
 * Interface for the Service layer.
 * <p>
 * Besides using methods from the DAO layer, the Service layer also manages entity managers and transactions.
 *
 * @param <T> the type of the entity
 * @author Arthur Kelsch, Julian Quint
 * @see GenericDao
 */
public interface GenericService<T> {
    /**
     * Persists a given entity.
     *
     * @param entity entity instance
     * @see GenericDao#persist(Object)
     */
    void persist(T entity);

    /**
     * Finds a single entity by its ID.
     *
     * @param id primary key
     * @return the found entity instance or null if the entity does not exist
     * @see GenericDao#findById(Long)
     */
    T findById(Long id);

    /**
     * Finds all entities.
     *
     * @return a list of found entity instances or a empty list if no entities exist
     * @see GenericDao#findAll()
     */
    List<T> findAll();

    /**
     * Finds all entities that match a filter.
     *
     * @param field      entity field
     * @param comparator comparator, e.g. {@code >} or {@code <}
     * @param value      value to filter for
     * @return a list of found entity instances or a empty list if no entities match the filter
     * @see GenericDao#filter(String, String, String)
     */
    List<T> filter(String field, String comparator, String value);

    /**
     * Updates a given entity.
     *
     * @param entity entity instance
     * @see GenericDao#update(Object)
     */
    void update(T entity);

    /**
     * Deletes a given entity.
     *
     * @param entity entity instance
     * @see GenericDao#delete(Object)
     */
    void delete(T entity);

    /**
     * Deletes a single entity by its ID.
     *
     * @param id primary key
     * @see GenericService#delete(Object)
     */
    void deleteById(Long id);

    /**
     * Deletes all entities.
     *
     * @see GenericDao#deleteAll()
     */
    void deleteAll();
}
