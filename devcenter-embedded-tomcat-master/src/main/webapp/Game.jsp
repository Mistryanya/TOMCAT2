<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cluedo Game</title>
    </head>

    <body>
         <%
            Cookie[] cks = request.getCookies();
            String nameS = (String) session.getAttribute("name");
            if (cks != null) {
               for (int i = 0; i < cks.length; i++) {
                  String nameC = cks[i].getName();
                  String valueC = cks[i].getValue();
                  if (nameC.equals("name") && valueC.equals(nameS)){
                     break;
                  }
                  if (i == (cks.length - 1)){
                     response.sendRedirect("sessionExpired.jsp");
                     return;
                  }
               }
            }else{
               response.sendRedirect("sessionExpired.jsp");
               return;
            }
         %>
         <h1>Cluedo</h1>


    </body>
</html>