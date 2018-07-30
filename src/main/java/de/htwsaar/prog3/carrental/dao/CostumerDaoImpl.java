package de.htwsaar.prog3.carrental.dao;

import de.htwsaar.prog3.carrental.model.Costumer;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

import javax.persistence.EntityManager;

/**
 * DAO implementation for the Costumer model.
 *
 * @author Julian Quint
 */
public class CostumerDaoImpl implements GenericDao<Costumer, Long> {
    private static final String PERSISTENCE_UNIT_NAME = "costumer";
    private static EntityManager entityManager = PersistenceUtil.getEntityManager(PERSISTENCE_UNIT_NAME);

    public CostumerDaoImpl() {
    }

    @Override
    public void persist(Costumer entity) {
        entityManager.persist(entity);
    }

    @Override
    public Costumer findById(Long id) {
        return entityManager.find(Costumer.class, id);
    }

    @Override
    public void update(Costumer entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Costumer entity) {
        entityManager.remove(entity);
    }
}
