package servlet;

import Database.Connect;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet(
        name = "RegisterServlet",
        urlPatterns = {"/RegisterServlet"}
)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String user = request.getParameter("UserName").trim();
        String email = request.getParameter("Email").trim();
        String pass = request.getParameter("Password").trim();
        String regexv = ".*[\",].*";
        String regexv2 = "[a-zA-Z0-9_.+-]+[@][a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

        boolean matches = Pattern.matches(regexv, user);
        boolean matches2 = Pattern.matches(regexv, email);
        boolean matches3 = Pattern.matches(regexv, pass);

        if(matches || matches2 || matches3) {
            //Don't allow these chars
            responsd(response, "Registration Failed<div></div>None of these characters are Allowed: \",<div></div><a href=Register.jsp>Back to Registration</a>");
        }else{
            Connect testObject = new Connect("Accounts.db");
            String[] columns = {"name", "email", "password"};
            String[] values = {user, email, pass};

            try {
                testObject.insert("UserInfo", columns, values);
                responsd(response, "Registration Complete<div></div><a href=index.jsp>Log In</a>");
            }catch (SQLException e){
                //alert of error **
                if(e.getErrorCode() == 19){
                    responsd(response, "Registration Failed<div></div>This Email has already been used<div></div><a href=Register.jsp>Try Again</a>");
                }else{
                    responsd(response, "Registration Failed<div></div>"+e.getMessage()+"<div></div><a href=Register.jsp>Try Again</a>");
                }

            }
        }
    }

    private void responsd(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<t1>" + msg + "</t1>");
        out.println("</body>");
        out.println("</html>");
    }

}
