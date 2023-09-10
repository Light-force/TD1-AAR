package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class Exo2Servlet extends HttpServlet {
    private final Option[] options={new Option("Beau",1),new Option("Couvert",2),new Option("Pluie",3), new Option("Neige",4)};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        toJsp(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        toJsp(request,response);
    }

    private void toJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // on récupère le paramètre "memo" de la requête
        String meteo = request.getParameter("meteo");
        if(meteo != null) {
            int key = Integer.valueOf(meteo);
            String value = findValueByKey(key);

            // on stocke sa valeur en session, sous le nom "memosession"
            Map<String, Integer> meteoSelectionnes = (HashMap) request.getSession().getAttribute("meteoSelectionnes");

            if(meteoSelectionnes == null)
                meteoSelectionnes = new HashMap<>();

            int nbSelected = 1;
            if(meteoSelectionnes.get(value) != null) {
                nbSelected = meteoSelectionnes.get(value) + 1;
            }
            meteoSelectionnes.put(value, nbSelected);
            request.getSession().setAttribute("meteoSelectionnes", meteoSelectionnes);

            request.getSession().setAttribute("lastUsed", key);
        }

        request.setAttribute("options",options);
        request.getRequestDispatcher("WEB-INF/meteo.jsp").forward(request,response);
    }

    private String findValueByKey(int key) throws ServletException {
        for(int i = 0; i<options.length; i++) {
            if(options[i].getKey() == key)
                return options[i].getValue();
        }
        throw new ServletException("Aucune valeur trouvé");
    }

    public class Option{
        private String value;
        private int key;

        public Option(String value, int key) {
            this.value = value;
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }
    }
}
