<%@page import="java.util.*"%>
<%@page import="daxbnb.DAO.ApartmentDAO"%>
<%@page import="daxbnb.model.Apartments"%>
<%@page import="daxbnb.DAO.ApartmentsOffersDAO"%>
<%@page import="daxbnb.model.Images"%>
<%@ page import="daxbnb.model.Offers"%>
<%@ page import="daxbnb.model.ApartmentOffers"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="daxbnb.model.Booking"%>
<%@ page import="daxbnb.model.User"%>
<%@ page import="daxbnb.model.Apartments"%>
<%@ page import="daxbnb.DAO.BookingDAO"%>
<%@ page import="daxbnb.model.BookingStatus"%>
<%@ page import="daxbnb.DAO.UserDAO"%>
<%@ page import="java.time.format.DateTimeParseException"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="styles/styles.css">

<style>
body {
	font-family: Arial, sans-serif;
	background: #f5f5f5;
}

.carousel-item img {
	height: 400px;
	object-fit: cover;
	width: 100%;
	border-radius: 10px;
}

.list-group-item {
	border: none;
	padding-left: 0;
}
</style>
</head>
<body>

	<jsp:include page="header.jsp" />

	<%
	String idParam = request.getParameter("id");
	Apartments apartment = null;

	if (idParam != null) {
		try {
			int apartmentId = Integer.parseInt(idParam);
			ApartmentDAO apartmentDAO = new ApartmentDAO();
			apartment = apartmentDAO.selectById(apartmentId);
		} catch (NumberFormatException e) {
			out.println("ID inválido.");
		}
	}
	%>

	<%
	if (apartment != null) {
	%>
	<div class="container mt-5">
		<div class="row">
			<!-- CARRUSEL -->
			<div class="col-md-6">
				<div id="detailsCarousel" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<%
						List<Images> imgs = apartment.getImages();
						for (int i = 0; i < imgs.size(); i++) {
						%>
						<div class="carousel-item <%=i == 0 ? "active" : ""%>">
							<img src="<%=imgs.get(i).getUrl()%>"
								class="d-block w-100 rounded">
						</div>
						<%
						}
						%>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#detailsCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#detailsCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon"></span>
					</button>
				</div>
			</div>

			<!-- DETALLES -->
			<div class="col-md-6">
				<h2><%=apartment.getName()%></h2>
				<p class="text-muted"><%=apartment.getLocation()%></p>
				<p>
					<i class="fas fa-user"></i>
					<%=apartment.getCapacity()%>
					guest
				</p>
				<p>
					<i class="fas fa-bed"></i>
					<%=apartment.getBedrooms()%>
					bedroom
				</p>
				<p>
					<i class="fas fa-bed"></i>
					<%=apartment.getBeds()%>
					bed
				</p>
				<p>
					<i class="fas fa-bath"></i>
					<%=apartment.getBaths()%>
					bath
				</p>
				<div class="border rounded p-3 my-3">
					<strong>Price: </strong>
					<%=apartment.getPricePerNight()%>
					€ per night
				</div>
			</div>
		</div>

		<!-- DESCRIPCIÓN + OFERTAS + RESERVA -->
		<div class="row mt-4">
			<!-- DESCRIPCIÓN + OFERTAS -->
			<div class="col-md-8">
				<h5>Description:</h5>
				<p>
					<%=apartment.getDescription()%>
				</p>

				<%
				ApartmentsOffersDAO apartofferDAO = new ApartmentsOffersDAO();
				List<ApartmentOffers> offersList = apartofferDAO.getOffersByApartmentId(apartment.getId());
				%>

				<h5>Offers:</h5>
				<ul class="list-group mb-3">
					<%
					for (ApartmentOffers ao : offersList) {
						Offers offer = ao.getOffer();
					%>
					<li class="list-group-item"><%=offer.getName()%></li>
					<%
					}
					%>
				</ul>

			</div>

			<!-- RESERVA -->
			<div class="col-md-4">
				<div class="border rounded p-4 shadow-sm bg-white">
					<h5>Reserve:</h5>
					<form method="post">
						<input type="hidden" name="action" value="reserve">
						<div class="mb-3">
							<label class="form-label">Check-in:</label> <input type="date"
								name="startdate" class="form-control" id="checkin" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Check-out:</label> <input type="date"
								name="enddate" class="form-control" id="checkout" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Guests:</label> <input type="number"
								name="guest" class="form-control" value="1" min="1" required>
						</div>
						<input type="hidden" name="total" id="total" value="0">
						<p>
							<strong>Total Price:</strong> <span id="totalPrice">0.00</span> €
						</p>
						<button type="submit" class="btn btn-success w-100">Reserve</button>
					</form>

					<%
					BookingDAO bookingDAO = new BookingDAO();
					String action = request.getParameter("action");
					if ("reserve".equals(action)) {
						String start = request.getParameter("startdate");
						String end = request.getParameter("enddate");
						String guests = request.getParameter("guest");
						LocalDate start2 = LocalDate.parse(start);
						LocalDate end2 = LocalDate.parse(end);
						double total = bookingDAO.calculateTotalBooking(apartment.getId(), start2, end2);
						out.println("<hr>");
						out.println("<p>Fecha de entrada: " + start + "</p>");
						out.println("<p>Fecha de salida: " + end + "</p>");
						out.println("<p>Número de huéspedes: " + guests + "</p>");
						out.println("<p>Total a pagar: " + total + " €</p>");
					}
					%>
					<%
					action = request.getParameter("action");
					if ("reserve".equals(action)) {
						String startStr = request.getParameter("startdate");
						String endStr = request.getParameter("enddate");
						String guestsStr = request.getParameter("guest");
						String totalStr = request.getParameter("total");

						// Validar sesión y obtener usuario
						if (session == null || session.getAttribute("idu") == null) {
							response.sendRedirect("login.jsp");
							return;
						}

						int userId = (int) session.getAttribute("idu");
						UserDAO userDAO = new UserDAO();
						User user = userDAO.selectById(userId);

						if (user == null || apartment == null) {
							out.println("<p class='text-warning'>Usuario o apartamento no disponible.</p>");
							return;
						}

						try {
							LocalDate start = LocalDate.parse(startStr);
							LocalDate end = LocalDate.parse(endStr);

							if (start.equals(end)) {
						out.println("<p class='text-warning'>Las fechas no pueden ser iguales.</p>");
						return;
							}

							if (end.isBefore(start)) {
						out.println("<p class='text-warning'>La fecha de salida no puede ser anterior a la de entrada.</p>");
						return;
							}
							boolean overlapping = bookingDAO.hasOverlappingBooking(apartment.getId(), start, end);
							if (overlapping) {
						out.println("<p class='text-warning'>Ya existe una reserva para esas fechas. Por favor, elige otras.</p>");
						return;
							}

							// Usar procedimiento almacenado para calcular el total
							double total = bookingDAO.calculateTotalBooking(apartment.getId(), start, end);

							Booking booking = new Booking(0, start, end, user, apartment, total, BookingStatus.CONFIRMED);
							boolean success = bookingDAO.insertBooking(booking);

							if (success) {
						out.println("<p class='text-success'>Reserva guardada correctamente. Total pagado: " + total + " €</p>");
							} else {
						out.println("<p class='text-danger'>No se pudo guardar la reserva.</p>");
							}

						} catch (DateTimeParseException | NumberFormatException e) {
							out.println("<p class='text-danger'>Error de formato en fechas o total: " + e.getMessage() + "</p>");
						} catch (Exception e) {
							out.println("<p class='text-danger'>Error inesperado: " + e.getMessage() + "</p>");
						}
					}
					%>
				</div>
			</div>

		</div>
	</div>
	<%
	} else {
	%>
	<div class="container mt-4">
		<p class="text-danger">Apartment not found.</p>
	</div>
	<%
	}
	%>
	<jsp:include page="footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		const pricePerNight =
	<%=apartment.getPricePerNight()%>
		;
		const checkinInput = document.getElementById("checkin");
		const checkoutInput = document.getElementById("checkout");
		const totalPriceDisplay = document.getElementById("totalPrice");
		const totalInput = document.getElementById("total");

		function updateTotalPrice() {
			const checkinDate = new Date(checkinInput.value);
			const checkoutDate = new Date(checkoutInput.value);

			if (!isNaN(checkinDate) && !isNaN(checkoutDate)
					&& checkoutDate > checkinDate) {
				const days = (checkoutDate - checkinDate)
						/ (1000 * 60 * 60 * 24);
				const total = days * pricePerNight;
				totalPriceDisplay.textContent = total.toFixed(2);
				totalInput.value = total.toFixed(2);
			} else {
				totalPriceDisplay.textContent = "0.00";
				totalInput.value = "0";
			}
		}

		checkinInput.addEventListener("change", updateTotalPrice);
		checkoutInput.addEventListener("change", updateTotalPrice);
	</script>

</body>
</html>
