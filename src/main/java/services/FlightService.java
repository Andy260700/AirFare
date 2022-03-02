package services;

import entities.Flight;

import java.security.interfaces.DSAPublicKey;
import java.time.Duration;
import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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
//        em.getTransaction().begin();
        Date time_lim = Date.from(time.toInstant().plus(Duration.ofHours(3)));

        TypedQuery<Flight> typedQuery = em.createQuery("select f from Flight f where f.source = :source and f.departureTime >= :s_time and f.departureTime <= :e_time", Flight.class);
        typedQuery.setParameter("source", source);
        typedQuery.setParameter("s_time", time);
        typedQuery.setParameter("e_time", time_lim);
        List<Flight> s_list = typedQuery.getResultList();

        typedQuery = em.createQuery("select f from Flight f where f.destination = :destination", Flight.class);
        typedQuery.setParameter("destination", destination);
        List<Flight> d_list = typedQuery.getResultList();

        HashMap<String, Flight> map = new HashMap<>();

        for(Flight flight : s_list){
            map.put(flight.getFlightNumber(), flight);
        }

        List<Flight> res_list = new ArrayList<>();

        for(Flight flight : d_list){
            if(map.containsKey(flight.getFlightNumber())){

                Flight f = map.get(flight.getFlightNumber());
                Long leg_gap = flight.getLeg() - f.getLeg() + 1;
                if(leg_gap<=0)
                    break;
                else{
                    f.setDestination(flight.getDestination());
                    f.setArrivalTime(flight.getArrivalTime());
                    TypedQuery<Double> query = em.createQuery("select sum(x.price) from Flight x where x.flightNumber=:flightNumber and x.leg >= :s_leg and x.leg <= :d_leg", Double.class);
                    query.setParameter("flightNumber", f.getFlightNumber());
                    query.setParameter("s_leg", f.getLeg());
                    query.setParameter("d_leg", flight.getLeg());
                    Double fare = query.getSingleResult();
                    System.out.println("total: "+fare + " arrival: " + f.getLeg()+" dest: "+flight.getLeg());
                    f.setPrice(fare);
                    f.setLeg(leg_gap);

                    res_list.add(f);
                }
            }
        }

//        em.getTransaction().commit();
        em.close();

        return res_list;
    }

    public Flight getFlight(String flightNumber, Long leg, EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Flight> typedQuery =  em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber=:flightNumber AND f.leg=:leg", Flight.class);
        typedQuery.setParameter("flightNumber", flightNumber);
        typedQuery.setParameter("leg", leg);
        Flight flight = typedQuery.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return flight;
    }

}
