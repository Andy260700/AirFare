package servlet;

import entities.Deal;
import entities.Flight;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.DealService;
import services.FlightService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@WebServlet("/deals")
public class ProcessDeals extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("entity-manager-factory");
        DealService dealService = new DealService();

        dealService.deleteExpiredDeals(emf);
        List<Deal> dealList = dealService.getDeals(emf);

        req.setAttribute("deal-array", dealList);
        req.getRequestDispatcher("deals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("entity-manager-factory");
        DealService dealService = new DealService();
        FlightService flightService = new FlightService();

        String flightNumber = req.getParameter("flight_number").toLowerCase();
        long leg = Long.parseLong(req.getParameter("leg"));
        double price = Double.parseDouble(req.getParameter("price"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Flight flight = flightService.getFlight(flightNumber, leg, emf);
        Date dealStarts = new Date();
//        Date dealEnds = new Date();
        try
        {
//            System.out.println(req.getParameter("deal_end"));
            Date dealEnds = sdf.parse(req.getParameter("deal_end"));
            Deal deal = new Deal(price, dealStarts, dealEnds, flight);
            dealService.saveDeal(deal, emf);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("deal_form.html").forward(req,resp);
    }
}
