package services;

import entities.Deal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import util.PersistenceUtil;
import java.util.List;

public class DealService {
    public void saveDeal(Deal deal, EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(deal);
        em.getTransaction().commit();
        em.close();
    }

    public List<Deal> getDeals(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Deal> list = em.createQuery("Select d from Deal d", Deal.class).getResultList();
        em.getTransaction().commit();



        em.close();
        return list;
    }
}
