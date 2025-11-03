<%@ page import="javax.servlet.http.HttpSession" %>
<%
    // Iniciar la sessió i invalidar-la per tancar la sessió
    HttpSession userSession = request.getSession(false); // Obtenir la sessió si existeix
    if (session != null) {
        session.invalidate(); // Invalidar la sessió
    }

    // Redirigir a la pàgina d'inici després de tancar la sessió
    response.sendRedirect("index.jsp");
%>