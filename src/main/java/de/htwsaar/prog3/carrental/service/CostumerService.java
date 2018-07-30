package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.dao.CostumerDaoImpl;
import de.htwsaar.prog3.carrental.dao.GenericDao;
import de.htwsaar.prog3.carrental.model.Costumer;
import de.htwsaar.prog3.carrental.util.PersistenceUtil;

/**
 * Costumer service layer providing methods for working with the Costumer model.
 *
 * @author Julian Quint
 */
public class CostumerService {
    private GenericDao<Costumer, Long> costumerDao = new CostumerDaoImpl();

    public CostumerService() {
    }

    public void persist(Costumer entity) {
        PersistenceUtil.beginTransaction();
        costumerDao.persist(entity);
        PersistenceUtil.commitTransaction();
    }

    public Costumer findById(Long id) {
        PersistenceUtil.beginTransaction();
        Costumer costumer = costumerDao.findById(id);
        PersistenceUtil.commitTransaction();

        return costumer;
    }

    public void update(Costumer entity) {
        PersistenceUtil.beginTransaction();
        costumerDao.update(entity);
        PersistenceUtil.commitTransaction();
    }

    public void remove(Costumer entity) {
        PersistenceUtil.beginTransaction();
        costumerDao.remove(entity);
        PersistenceUtil.commitTransaction();
    }
}
