package dao;

import entities.Deal;
import jakarta.persistence.EntityManager;
import util.PersistenceUtil;
import java.util.List;

public class DealDao {
    public void saveDeal(Deal deal) {
        EntityManager em = PersistenceUtil.beginTransaction();
        em.persist(deal);
        PersistenceUtil.commitTransaction(em);
    }

    public List<Deal> getDeals() {
        EntityManager em = PersistenceUtil.beginTransaction();
        List<Deal> list = em.createQuery("Select d from Deal d", Deal.class).getResultList();
        PersistenceUtil.commitTransaction(em);
        return list;
    }
}
