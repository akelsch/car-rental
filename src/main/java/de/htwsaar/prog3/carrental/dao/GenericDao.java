package de.htwsaar.prog3.carrental.dao;

import java.util.List;

/**
 * Interface for the Data Access Object (DAO) layer.
 * <p>
 * Defines basic CRUD methods as well as methods for handling entity managers and transactions.
 *
 * @param <T> the type of the entity
 * @author Arthur Kelsch
 */
public interface GenericDao<T> {
    /**
     * Persists a given entity (Create).
     *
     * @param entity entity instance
     */
    void persist(T entity);

    /**
     * Finds a single entity by its ID (Read).
     *
     * @param id primary key
     * @return the found entity instance or null if the entity does not exist
     */
    T findById(Long id);

    /**
     * Finds all entities (Read).
     *
     * @return a list of found entity instances or a empty list if no entities exist
     */
    List<T> findAll();

    /**
     * Finds all entities that match a filter (Read).
     *
     * @param field      entity field
     * @param comparator comparator, e.g. {@code >} or {@code <}
     * @param value      value to filter for
     * @return a list of found entity instances or a empty list if no entities match the filter
     */
    List<T> filter(String field, String comparator, String value);

    /**
     * Updates a given entity (Update).
     *
     * @param entity entity instance
     */
    void update(T entity);

    /**
     * Deletes a given entity (Delete).
     *
     * @param entity entity instance
     */
    void delete(T entity);

    /**
     * Deletes all entities (Delete).
     */
    void deleteAll();

    /**
     * Creates an entity manager.
     */
    void createEntityManager();

    /**
     * Closes an entity manager.
     */
    void closeEntityManager();

    /**
     * Begins a transaction.
     */
    void beginTransaction();

    /**
     * Commits a transaction.
     */
    void commitTransaction();
}
