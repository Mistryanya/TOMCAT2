package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "LogoutServlet",
        urlPatterns = {"/LogoutServlet"}
)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Cookie ck = new Cookie("name","");
        ck.setMaxAge(0);
        response.addCookie(ck);
        request.getSession().removeAttribute("name");
        request.removeAttribute("name");
        request.getRequestDispatcher("logout.jsp").forward(request, response);


    }
}
