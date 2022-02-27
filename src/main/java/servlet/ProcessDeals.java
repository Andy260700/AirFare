package servlet;

import entities.Deal;
import entities.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.DealService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/deals")
public class ProcessDeals extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory em = (EntityManagerFactory)getServletContext().getAttribute("entity-manager-factory");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("entity-manager-factory");
        DealService dealService = new DealService();

        String flightNumber = req.getParameter("flight_number").toLowerCase();
        long leg = Long.parseLong(req.getParameter("leg"));
        double price = Double.parseDouble(req.getParameter("price"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Flight flight = new Flight();
        Date dealStarts = new Date();
        Date dealEnds = new Date();
        try
        {
            dealEnds = sdf.parse(req.getParameter("deal_end"));
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        Deal deal = new Deal(price,dealStarts, dealEnds, flight);
        dealService.saveDeal(deal, emf);

        req.getRequestDispatcher("deal_form.html").forward(req,resp);

    }
}
