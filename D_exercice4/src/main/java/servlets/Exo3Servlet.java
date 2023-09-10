package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/init", "/play", "/"})
public class Exo3Servlet extends HttpServlet {
    private GestionPendu jeuPendu = new GestionPendu();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (jeuPendu.getDevine() == null || "/".equals(request.getServletPath())) {
            request.getRequestDispatcher("WEB-INF/pendu.jsp").forward(request, response);
        } else {
            request.setAttribute("devine", jeuPendu.getDevine());
            request.setAttribute("nbEssaisRestants", jeuPendu.getNbEssaisRestants());
            request.getRequestDispatcher("WEB-INF/essai.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/init".equals(request.getServletPath())) {
            String mot = request.getParameter("lemot");
            if (mot != null && !mot.isEmpty()) {
                jeuPendu.setaDeviner(mot);
                response.sendRedirect(request.getContextPath() + "/play");
            } else {
                request.setAttribute("error", "Veuillez entrer un mot valide.");
                request.getRequestDispatcher("WEB-INF/pendu.jsp").forward(request, response);
            }
        } else if ("/play".equals(request.getServletPath())) {
            String letterInput = request.getParameter("lecaractere");
            if (letterInput != null && letterInput.length() == 1) {
                char letter = letterInput.charAt(0);
                boolean letterExists = jeuPendu.test(letter);
                request.setAttribute("letterExists", letterExists);
                request.setAttribute("devine", jeuPendu.getDevine());
                request.setAttribute("nbEssaisRestants", jeuPendu.getNbEssaisRestants());
                request.getRequestDispatcher("WEB-INF/essai.jsp").forward(request, response);
            } else {
                request.setAttribute("nbEssaisRestants", jeuPendu.getNbEssaisRestants());
                request.setAttribute("error", "Veuillez entrer un caractère valide.");
                request.getRequestDispatcher("WEB-INF/essai.jsp").forward(request, response);
            }
        }
    }
}
