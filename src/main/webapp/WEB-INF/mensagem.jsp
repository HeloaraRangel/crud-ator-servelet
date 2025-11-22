<%-- 
    Document   : mensagem
    Created on : 19 de ago. de 2025, 20:26:54
    Author     : Heloara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
	
		String m = request.getParameter("nomeAtor");
	
		out.println("<b>"+m+"</b>");
	%>
    </body>
</html>
