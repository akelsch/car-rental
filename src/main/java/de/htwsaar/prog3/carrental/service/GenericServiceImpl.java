package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDao;
import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.util.FilterUtil;

import java.util.List;

/**
 * {@link GenericService} implementation.
 * <p>
 * This class serves as a super class for all other implementations.
 * The only difference between the services is their DAO implementation.
 *
 * @param <T> the type of the entity
 * @author Arthur Kelsch
 */
public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T> {
    GenericDao<T> dao;

    @Override
    public void persist(T entity) {
        dao.createEntityManager();
        dao.beginTransaction();
        dao.persist(entity);
        dao.commitTransaction();
        dao.closeEntityManager();
    }

    @Override
    public T findById(Long id) {
        dao.createEntityManager();
        T entity = dao.findById(id);
        dao.closeEntityManager();

        return entity;
    }

    @Override
    public List<T> findAll() {
        dao.createEntityManager();
        List<T> entities = dao.findAll();
        dao.closeEntityManager();

        return entities;
    }

    @Override
    public List<T> filter(String field, String comparator, String value){
        dao.createEntityManager();
        dao.beginTransaction();
        List<T> entities = dao.filter(FilterUtil.convertField(field), comparator,
                FilterUtil.convertValue(value, comparator));
        dao.commitTransaction();
        dao.closeEntityManager();
        return entities;
    }

    @Override
    public void update(T entity) {
        dao.createEntityManager();
        dao.beginTransaction();
        dao.update(entity);
        dao.commitTransaction();
        dao.closeEntityManager();
    }

    @Override
    public void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        dao.createEntityManager();
        dao.beginTransaction();
        T entity = dao.findById(id);
        dao.delete(entity);
        dao.commitTransaction();
        dao.closeEntityManager();
    }

    @Override
    public void deleteAll() {
        dao.createEntityManager();
        dao.beginTransaction();
        dao.deleteAll();
        dao.commitTransaction();
        dao.closeEntityManager();
    }
}
