package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/* ce qui importe c'est le urlPatterns. Le name c'est pour du repérage de compsant au sein du serveur */
/* ici le servlet est invoqué à la racine du projet */
/* ATTENTION NE JAMAIS METTRE DE COMMENTAIRE ENTRE UNE ANNOTATION ET SA CIBLE!!!! */
@WebServlet(urlPatterns = {"/"})
public class Servlet1 extends HttpServlet {

    /** on considère que le doGet n'est appelé que pa invocation directe de l'url */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp1.jsp").forward(request,response);
    }

    /** invoqué via le formulaire de la JSP */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom=request.getParameter("nom");
        if (nom.length()>0) {
            request.setAttribute("who_is_there", nom);
        }
        request.getRequestDispatcher("/WEB-INF/jsp1.jsp").forward(request,response);
    }
}
