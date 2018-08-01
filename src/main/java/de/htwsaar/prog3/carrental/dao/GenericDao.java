package de.htwsaar.prog3.carrental.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for the Data Access Object (DAO) layer.
 * <p>
 * Defines basic CRUD methods as well as methods for handling entity managers and transactions.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the primary key
 * @author Arthur Kelsch
 * @see <a href="https://bit.ly/2vlJaS0">EntityManager</a>
 * @see <a href="https://bit.ly/2vnoGs2">CrudRepository</a>
 */
public interface GenericDao<T, ID extends Serializable> {
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
    T findById(ID id);

    /**
     * Finds all entities (Read).
     *
     * @return a list of found entity instances or a empty list if no entities exist
     */
    List<T> findAll();

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
