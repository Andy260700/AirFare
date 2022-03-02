package services;

import entities.Deal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;
import util.PersistenceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public void deleteExpiredDeals(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date dealEnds = new Date();
        String temp = sdf.format(dealEnds);
        try
        {
            dealEnds = sdf.parse(temp);
            Query query = em.createQuery("delete from Deal d where d.endingTime < :dealEnds");
            query.setParameter("dealEnds", dealEnds, TemporalType.TIMESTAMP);
            query.executeUpdate();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        em.getTransaction().commit();
        em.close();
    }
}
