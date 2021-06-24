package servlet;

import Database.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("Email").trim();
        String pass = request.getParameter("Password").trim();

        Connect testObject = new Connect("Accounts.db");

        String[] columns = {"id","name","email", "password"};
        String[] values = {email, pass};

        try {
            ArrayList<String[]> result;

            result = testObject.query(columns, "UserInfo","email =\""+ values[0]+"\" AND password =\""+values[1]+"\"");
            if(result.size() != 0) {

                //Make a current User
                int id = Integer.parseInt(result.get(0)[0]);
                String name = result.get(0)[1];

                Cookie ck = new Cookie("name",name);
                response.addCookie(ck);
                request.getSession().setAttribute("name", name);
                request.getRequestDispatcher("User.jsp").forward(request, response);

                return;
            }else{
                respond(response, "Log In Failed<div></div><a href=index.jsp>Try Again</a>");
            }

        }catch (SQLException | ServletException e){
            //alert of error **
            respond(response, "Log In Failed<div></div><a href=index.jsp>Try Again</a>");

        }
    }

    private void respond(HttpServletResponse resp, String msg)
            throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<t1>" + msg + "</t1>");
        out.println("</body>");
        out.println("</html>");
    }



}
