package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.GenericDao;

import java.util.List;

public class GenericServiceImpl<T> implements GenericService<T> {
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
    public void update(T entity) {
        dao.createEntityManager();
        dao.beginTransaction();
        dao.update(entity);
        dao.commitTransaction();
        dao.closeEntityManager();
    }

    @Override
    public void delete(T entity) {
        // TODO
        // deleteById(entity.getId());
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
