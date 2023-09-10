package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/init", "/play", "/"})
public class Exo3Servlet extends HttpServlet {
    private String aDeviner = null;
    private StringBuilder devine = null;
    private int nbEssaisRestants;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (aDeviner == null || "/".equals(request.getServletPath())) {
            request.getRequestDispatcher("WEB-INF/pendu.jsp").forward(request, response);
        } else {
            request.setAttribute("devine", devine);
            request.getRequestDispatcher("WEB-INF/essai.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/init".equals(request.getServletPath())) {
            String mot = request.getParameter("lemot");
            if (mot != null && !mot.isEmpty()) {
                setaDeviner(mot);
                response.sendRedirect(request.getContextPath() + "/play");
            } else {
                request.setAttribute("error", "Veuillez entrer un mot valide.");
                request.getRequestDispatcher("WEB-INF/pendu.jsp").forward(request, response);
            }
        } else if ("/play".equals(request.getServletPath())) {
            String letterInput = request.getParameter("lecaractere");
            if (letterInput != null && letterInput.length() == 1) {
                char letter = letterInput.charAt(0);
                boolean letterExists = test(letter);
                request.setAttribute("letterExists", letterExists);
                request.setAttribute("devine", devine);
                request.getRequestDispatcher("WEB-INF/essai.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Veuillez entrer un caractère valide.");
                request.getRequestDispatcher("WEB-INF/essai.jsp").forward(request, response);
            }
        }
    }

    private void setaDeviner(String aDeviner) {
        this.aDeviner = aDeviner;
        this.devine = new StringBuilder("_".repeat(aDeviner.length()));
        this.nbEssaisRestants = 10;
    }

    private boolean test(char carac) {
        boolean res = false;
        int last = 0;
        while ((last = aDeviner.indexOf(carac, last)) != -1) {
            res = true;
            devine.setCharAt(last, carac);
            last++;
        }
        if (!res) {
            nbEssaisRestants--;
        }
        return res;
    }
}
