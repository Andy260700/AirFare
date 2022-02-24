package listener;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import util.PersistenceUtil;

@WebListener
public class PersistenceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        sce.getServletContext().setAttribute("entity-manager-factory", entityManagerFactory);
        PersistenceUtil.setEmf(entityManagerFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        PersistenceUtil.setEmf(null);
        EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("entity-manager-factory");
        emf.close();
    }
}
