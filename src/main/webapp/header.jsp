<!-- includes/header.jsp -->
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<% 
    session = request.getSession(false); // Obtenir la sessió sense crear una nova
    String username = null;
    String userType = null;

    if (session != null) {
        username = (String) session.getAttribute("username"); // Obtener el nombre del usuario
        userType = (String) session.getAttribute("userType"); // Obtener el tipo de usuario
    }
%>

<header class="header">
    <div class="logo-container">
	    <a href="index.jsp">
	        <img src="img/Logos/airbnb.png" alt="DAMbnb Logo" width="50">
	    </a>
	    <span class="brand-name">DAWbnb</span>
	</div>

    <% if (username != null) { %> 
    <div class="user-info">
     	<span class="text-dark fw-medium">Welcome, <%= username %></span>
        <a href="<%= "ADMIN".equalsIgnoreCase(userType) ? "homeAdmin.jsp" : "profile.jsp" %>">
		    <img src="img/Logos/Profile.png" alt="User Profile" class="icon">
		</a>
        <a href="logout.jsp">
            <img src="img/Logos/logout_icon.webp" alt="Logout" class="icon">
        </a>
    </div>
    <% } else { %>
       <a href="login.jsp" class="text-dark fw-medium text-decoration-none">Login</a>
	<% } %>
</header>

</body>
</html>