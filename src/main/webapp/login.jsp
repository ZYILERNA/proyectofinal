<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="daxbnb.DAO.UserDAO" %>
<%@ page import="daxbnb.model.User" %>
<%@ page import="daxbnb.model.UserType" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <title>Login</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles/styles.css">

    <style>
        html, body {
            height: 100%;
            margin: 0;
        }

        .page-wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .main-content {
            flex: 1;
        }
    </style>
</head>
<body>

<div class="page-wrapper">

    <main class="main-content">
        <%
        String errorMsg = null;
        String email = null;
        int idUser = 0;

        String action = request.getParameter("action");

        if ("login".equalsIgnoreCase(action)) {

            email = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.login(email, password);

                if (user != null) {
                    idUser = user.getId();
                    session = request.getSession();
                    session.setAttribute("username", email);
                    session.setAttribute("idu", idUser);
                    session.setAttribute("userType", user.getType().name());

                    if (user.getType() == UserType.ADMIN) {
                        response.sendRedirect("homeAdmin.jsp");
                    } else {
                        response.sendRedirect("index.jsp");
                    }

                    return;

                } else {
                    errorMsg = "ERROR: username or password invalid";
                }

            } catch (Exception e) {
                errorMsg = "ERROR: " + e.getMessage();
            }
        }
        %>

        <main class="main-content d-flex justify-content-center align-items-center vh-100">
		    <div class="container">
		        <div class="row justify-content-center">
		            <div class="col-md-6">
		                <div class="card">
		                    <div class="card-header text-center">
		                        <h2>Login</h2>
		                    </div>
		                    <div class="card-body">
		                        <% if (errorMsg != null) { %>
		                        <div class="alert alert-danger text-center">
		                            <%= errorMsg %>
		                        </div>
		                        <% } %>
		
		                        <form method="post">
		                            <input type="hidden" name="action" value="login" />
		                            <div class="mb-3">
		                                <label for="username" class="form-label">User:</label>
		                                <input type="text" class="form-control" id="username" name="username"
		                                       value="<%= email != null ? email : "" %>" required>
		                            </div>
		                            <div class="mb-3">
		                                <label for="password" class="form-label">Password:</label>
		                                <input type="password" class="form-control" id="password" name="password" required>
		                            </div>
		                            <div class="d-grid">
		                                <button type="submit" class="btn btn-primary">Login</button>
		                            </div>
		                        </form>
		
		                    </div>
		                </div>
		            </div>
		        </div>
		        
		    </div>
		    
		</main>
		<jsp:include page="footer.jsp" />
    </main>

    

</div>

</body>
</html>
