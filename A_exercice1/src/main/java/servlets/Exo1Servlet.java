package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class Exo1Servlet extends HttpServlet {

    private final String expectedLogin = "login", expectedMdp = "mdp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        if (login.equals(expectedLogin) && mdp.equals(expectedMdp)) {
            request.getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
    }
}
