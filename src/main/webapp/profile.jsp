<%@ page import="daxbnb.model.Apartments"%>
<%@ page import="daxbnb.DAO.ApartmentDAO"%>
<%@ page import="daxbnb.DAO.BookingDAO"%>
<%@ page import="daxbnb.DAO.UserDAO"%>
<%@ page import="daxbnb.model.User"%>
<%@ page import="java.util.*"%>
<%@ page import="daxbnb.model.Images"%>
<%@ page import="daxbnb.model.Booking"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="styles/styles.css">
</head>
<body>

	<jsp:include page="header.jsp" />

	<%
	HttpSession currentSession = request.getSession(false);
	String username = null;
	int idUser = 0;

	if (currentSession != null) {

		username = (String) currentSession.getAttribute("username");
		idUser = Integer.parseInt(currentSession.getAttribute("idu").toString());

		UserDAO userDAO = new UserDAO();
		User user = userDAO.selectById(idUser);
	%>

	<div class="card" style="width: 400px; margin: 1.5rem;">
		<h5 class="card-header">Details</h5>
		<div class="card-body">
			<p class="card-text mb-0">
				<strong>User name:</strong>
				<%=user.getName()%></p>
			<p class="card-text mb-0">
				<Strong>Password:</Strong>
				<%=user.getPassword()%></p>
			<p class="card-text mb-0">
				<Strong>User type:</Strong>
				<%=user.getType()%></p>
			<p class="card-text mb-0">
				<Strong>Address:</Strong>
				<%=user.getAddress()%></p>
		</div>
	</div>

	<%
	BookingDAO bookingDAO = new BookingDAO();
	List<Booking> bookings = bookingDAO.selectBookingByUserId(idUser);
	%>

	<div class="table-responsive mx-4">
		<h3>Your Bookings</h3>
		<table class="table table-striped table-bordered">
			<thead class="table-dark">
				<tr>
					<th>Img</th>
					<th>Booking ID</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Total Price</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Booking b : bookings) {
					Apartments apartmet = b.getApartment();

					List<Images> imagenes = apartmet.getImages();

					String imageUrl = apartmet.getImages().isEmpty() ? "default.jpg" : apartmet.getImages().get(0).getUrl();
				%>
				<tr>
					<td class="text-center">
						<img src="<%=imageUrl%>" alt="<%=b.getApartment().getName()%>" style="width: 100px; height: auto; border-radius: 6px; content-aling:center">
					</td>
					<td class="align-middle"><%=b.getId()%></td>
					<td class="align-middle"><%=b.getStartDate()%></td>
					<td class="align-middle"><%=b.getEndDate()%></td>
					<td class="align-middle"><%=b.getTotalPrice()%></td>
					<td class="align-middle"><%=b.getStatus()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<%
	} else {
	// No hay sesión válida → redirige a login
	response.sendRedirect("login.jsp");
	return;
	}
	%>

	<jsp:include page="footer.jsp" />
</body>
</html>