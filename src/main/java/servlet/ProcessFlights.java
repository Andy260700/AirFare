package servlet;

import entities.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.FlightService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/flights")
public class ProcessFlights extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("entity-manager-factory");
        FlightService flightService = new FlightService();

        String source = req.getParameter("source").toLowerCase();
        String destination = req.getParameter("destination").toLowerCase();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        Date time = new Date();
        try
        {
            Date time = sdf.parse(req.getParameter("search_time"));
            List<Flight> flightList = flightService.getFlights(source, destination, time, emf);

            req.setAttribute("flight-array", flightList);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("entity-manager-factory");
        FlightService flightService = new FlightService();

        String flightNumber = req.getParameter("flight_number").toLowerCase();
        long leg = Long.parseLong(req.getParameter("leg"));
        String owner = req.getParameter("flight_owner").toLowerCase();
        String source = req.getParameter("source").toLowerCase();
        String destination = req.getParameter("destination").toLowerCase();
        double price = Double.parseDouble(req.getParameter("price"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        Date arrivalTime = new Date();
//        Date departTime = new Date();
        try
        {
//            System.out.println(req.getParameter("arrival_time"));
            Date arrivalTime = sdf.parse(req.getParameter("arrival_time"));
            Date departTime = sdf.parse(req.getParameter("depart_time"));
            Flight flight = new Flight(flightNumber, leg, owner, source, destination, departTime, arrivalTime,price);
            flightService.saveFlight(flight, emf);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("flight_form.html").forward(req, resp);
    }
}
