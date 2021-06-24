package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "ProfileServlet",
        urlPatterns = {"/ProfileServlet"}
)
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] ck =request.getCookies();
        String name = "";
        if(ck!=null){
            name = ck[0].getValue();

            System.out.println(name);
        }
        if(!(name.equals(""))){
                out.print("<b>Welcome to Profile</b>");
                out.print("<br>Welcome, "+name);
        }else{
            out.print("Please login first");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        out.close();
    }
}

