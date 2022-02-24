package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PersistenceUtil {
    private static EntityManagerFactory emf;

    public static void setEmf(EntityManagerFactory emf) {
        PersistenceUtil.emf = emf;
    }

    public static EntityManager beginTransaction() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    public static void commitTransaction(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }
}
