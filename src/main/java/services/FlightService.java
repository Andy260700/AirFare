package services;

import entities.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class FlightService {
    public void saveFlight(Flight flight, EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(flight);
        em.getTransaction().commit();
        em.close();
    }

    public List<Flight> getFlights(String source, String destination, Date time, EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        List<Flight> list = new ArrayList<>();
        return list;
    }

    public Flight getFlights(String flightId, Long leg, EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        
        Flight flight = new Flight();
        return flight;
    }

}
