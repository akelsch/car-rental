package de.htwsaar.prog3.carrental.dao;

import java.io.Serializable;

/**
 * Interface for Data Access Objects. Defines basic CRUD methods.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the primary key
 * @author Arthur Kelsch
 */
public interface CarDao<T, ID extends Serializable> {
    // Create
    void persist(T entity);

    // Read
    T findById(ID id);

    // Update
    void update(T entity);

    // Delete
    void remove(T entity);
}
