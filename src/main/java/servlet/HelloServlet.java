package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(req.getPathInfo());
        PrintWriter out = resp.getWriter();
        Optional<String> name = Optional.ofNullable(req.getParameter("name"));
        resp.setContentType("application/json");
        out.print("{message: \"Hello, "+ name.orElse("User") + "\"}");
        out.flush();
    }

}