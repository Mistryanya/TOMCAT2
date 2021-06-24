
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
         <%
            Cookie[] cks = request.getCookies();
            String nameS = (String) session.getAttribute("name");
            if (cks != null){
                for (int i = 0; i < cks.length; i++){
                    String nameC = cks[i].getName();
                    String valueC = cks[i].getValue();

                    if (nameC.equals("name") && valueC.equals(nameS)){
                        break;
                    }else if (nameC.equals("name")){
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        return;
                    }

                }
            }else{
                response.sendRedirect("sessionExpired.jsp");
                return;
            }
         %>
            <h3>${name} you have successfully logged in.</h3>
            <form action="LogoutServlet" method="post">
               <input type="submit" value="Logout">
            </form>
    </body>
</html>
