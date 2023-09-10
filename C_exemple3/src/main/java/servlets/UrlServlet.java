package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/UrlServlet/*"})
public class UrlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWhatever(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWhatever(request,response);
    }

    private void doWhatever(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo=request.getPathInfo();

        request.setAttribute("previoustodo",todo);
        if (todo==null) {
            request.setAttribute("msg","Choisissez une action...");
        } else {
            todo=todo.substring(1);   // le substring retire le / de début

            switch (todo) {
                case "bonjour" :
                    request.setAttribute("msg","Bien le bonjour!");
                    break;
                case "beau" :
                    request.setAttribute("msg","Il fait toujours beau quand c'est le Spring!");
                    break;
                default:
                    request.setAttribute("msg","Qu'avez vous dit?");
            }
        }
        request.getRequestDispatcher("/WEB-INF/url.jsp").forward(request,response);

    }


}
