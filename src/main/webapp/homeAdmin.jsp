<%@ page import="daxbnb.model.BookingStatus"%>
<%@ page import="daxbnb.DAO.ApartmentsOffersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.time.LocalDate"%>


<%@ page import="daxbnb.DAO.BookingDAO"%>
<%@ page import="daxbnb.DAO.ApartmentDAO"%>
<%@ page import="daxbnb.DAO.OffersDAO"%>
<%@ page import="daxbnb.DAO.ImagesDAO"%>
<%@ page import="daxbnb.DAO.UserDAO"%>

<%@ page import="daxbnb.model.Images"%>
<%@ page import="daxbnb.model.Booking"%>
<%@ page import="daxbnb.model.User"%>
<%@ page import="daxbnb.model.Apartments"%>
<%@ page import="daxbnb.model.Offers"%>
<%@ page import="daxbnb.model.ApartmentType"%>
<%@ page import="daxbnb.model.UserType"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>HomeAdmin</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
		<!-- Header -->
		<jsp:include page="header.jsp" /> 

		<%
			
			String title = "";
			String action = "action1";
			List<Apartments> apartments = new ArrayList<>();
			
			List<Booking> bookings = new ArrayList<>();
			List<User> users = new ArrayList<>();
			List<Offers> offers = new ArrayList<>();
		
			ApartmentDAO apartmentDAO = new ApartmentDAO();
			BookingDAO bookingDAO = new BookingDAO();
			UserDAO userDAO = new UserDAO();
			OffersDAO offersDAO = new OffersDAO();
			ImagesDAO imagesDAO = new ImagesDAO();
			ApartmentsOffersDAO apartmentsOffersDAO = new ApartmentsOffersDAO();
		
			apartments = apartmentDAO.selectAll();
			offers = offersDAO.SelectAll();
			bookings = bookingDAO.selectAll();
			users = userDAO.select();
			
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				try {
					action = request.getParameter("action");
					switch (action) {
					
							case "action1": // List Apartments
								title = "Stays";
							break;
							case "action2": // Insert New Apartment
								title = "New Stay";
							break;
							case "action3": // Update Apartment
								title = "Update Stay";
							break;
							case "action4": // Delete Apartment
								title = "Delete Stay";
							break;
							
							case "action5": // List Bookings
								title = "Bookings";
							break;
							case "action6": // Insert New Booking
								title = "New Booking";
							break;
							case "action7": // Update Booking
								title = "Update Booking";
							break;
							case "action8": // Delete Booking
								title = "Delete Booking";
							break;
					
							case "action9": // List Users
								title = "Users";
							break;
							case "action10": // Insert New User
								title = "New User";
							break;
							case "action11": // Update User
								title = "Update User";
							break;
							case "action12": // Delete User
								title = "Delete User";
							break;
					
							case "action13": // List Offers
								title = "Offers";
							break;
							case "action14": // Insert Offer
								title = "New Offer";
							break;
							case "action15": // Update Offer
								title = "Update Offer";
							break;
							case "action16": // Delete Offer
								title = "Delete Offer";
							break;
							case "logout": // Logout
							    response.sendRedirect("logout.jsp");
							break;
							
							default:
								
			
					}
				} catch (Exception e) {
					//Get all excepcions to print message error.
					out.println("<div class='alert alert-danger' role='alert'>ERROR: " + e.getMessage() + "</div>");
				}
		
			} else if ("GET".equalsIgnoreCase(request.getMethod())) {
		
			}
		%>
		
		<div class="container">
			<div class="row">
				<!-- Left content: menu -->
				<div class="col-md-3 mb-5 mt-4">
				
					<!-- Stay -->
					<form method="POST">
						<input type="hidden" name="action" value="action1" />
						<button type="submit" class="btn btn-primary btn-menu mt-1 mb-1 w-100">List Stays</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action2" />
						<button type="submit" class="btn btn-primary btn-menu mt-1 mb-1 w-100">Insert new Stay</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action3" />
						<button type="submit" class="btn btn-primary btn-menu mt-1 mb-1 w-100">Update Stay</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action4" />
						<button type="submit" class="btn btn-primary btn-menu mt-1 mb-1 w-100">Delete Stay</button>
					</form>
	
					<!-- Booking -->
					<form method="POST">
						<input type="hidden" name="action" value="action5" />
						<button type="submit" class="btn btn-success btn-menu mt-1 mb-1 w-100">List Bookings</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action6" />
						<button type="submit" class="btn btn-success btn-menu mt-1 mb-1 w-100">Insert new Booking</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action7" />
						<button type="submit" class="btn btn-success btn-menu mt-1 mb-1 w-100">Update Booking</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action8" />
						<button type="submit" class="btn btn-success btn-menu mt-1 mb-1 w-100">Delete Booking</button>
					</form>
	
					<!-- User -->
					<form method="POST">
						<input type="hidden" name="action" value="action9" />
						<button type="submit" class="btn btn-warning btn-menu mt-1 mb-1 w-100">List Users</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action10" />
						<button type="submit" class="btn btn-warning btn-menu mt-1 mb-1 w-100">Insert new User</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action11" />
						<button type="submit" class="btn btn-warning btn-menu mt-1 mb-1 w-100">Update User</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action12" />
						<button type="submit" class="btn btn-warning btn-menu mt-1 mb-1 w-100">Delete User</button>
					</form>
	
					<!-- Offer -->
					<form method="POST">
						<input type="hidden" name="action" value="action13" />
						<button type="submit" class="btn btn-info btn-menu mt-1 mb-1 w-100">List Offers</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action14" />
						<button type="submit" class="btn btn-info btn-menu mt-1 mb-1 w-100">Insert new Offer</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action15" />
						<input type="hidden" name="action" value="action15" />
						<button type="submit" class="btn btn-info btn-menu mt-1 mb-1 w-100">Update Offer</button>
					</form>
					<form method="POST">
						<input type="hidden" name="action" value="action16" />
						<button type="submit" class="btn btn-info btn-menu mt-1 mb-1 w-100">Delete Offer</button>
					</form>
	
					<!-- Logout -->
					<form method="POST">
						<input type="hidden" name="action" value="logout" />
						<button type="submit" class="btn btn-danger btn-menu mt-1 mb-1 w-100">LOGOUT</button>
					</form>
	
				</div>
	
				<!-- Right content -->
				<div class="col-md-9 mt-4 mb-5">
					<div class="card">
	
						<div class="card-header">
							<h2 class="text-center"><%=title%></h2>
						</div>
	
						<!------------------------- Apartments ------------------------->
	
						<!-- List Stays -->
						<%
						if ("action1".equals(action)) {
						%>
						<div class="table-responsive mx-4">
							<table class="table table-striped table-bordered">
								<thead class="table-dark text-center">
									<tr>
										<th>Image</th>
										<th>Name</th>
										<th>City</th>
										<th>Price/Night</th>
										<th>Type</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (Apartments apt : apartments) {
										String imageUrl = (apt.getImages() != null && !apt.getImages().isEmpty()) ? apt.getImages().get(0).getUrl()
										: "default.jpg";
									%>
									<tr>
										<td class="text-center"><img src="<%=imageUrl%>"
											alt="<%=apt.getName()%>"
											style="width: 100px; height: auto; border-radius: 6px;">
										</td>
										<td class="align-middle"><%=apt.getName()%></td>
										<td class="align-middle"><%=apt.getLocation()%></td>
										<td class="align-middle"><%=apt.getPricePerNight()%></td>
										<td class="align-middle"><%=apt.getApartmentType()%></td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</div>
						<%
						}
						%>
	
						<!-- Insert new Stay -->
						<%
						if ("POST".equalsIgnoreCase(request.getMethod()) && "action2".equals(request.getParameter("action"))) {
						%>
							<div class="texto">
								<form method="post" class="mx-4 my-3">
									<input type="hidden" name="action" value="action2" />
		
									<div class="mb-3">
										<label for="name" class="form-label">Name:</label> 
										<input type="text" class="form-control" id="name" name="name" required>
									</div>
		
									<div class="mb-3">
										<label for="description" class="form-label">Description:</label>
										<input type="text" class="form-control" id="description" name="description" required>
									</div>
		
									<div class="mb-3">
										<label for="location" class="form-label">Location:</label> 
										<input type="text" class="form-control" id="location" name="location" required >
									</div>
		
									<div class="mb-3">
										<label for="capacity" class="form-label">Capacity:</label> 
										<input type="number" class="form-control" id="capacity" name="capacity" required>
									</div>
		
									<div class="mb-3">
										<label for="bedrooms" class="form-label">BedRooms:</label> 
										<input type="number" class="form-control" id="bedrooms" name="bedrooms" required>
									</div>
		
									<div class="mb-3">
										<label for="beds" class="form-label">Beds:</label> 
										<input type="number" class="form-control" id="beds" name="beds" required>
									</div>
		
									<div class="mb-3">
										<label for="baths" class="form-label">Baths:</label> 
										<input type="number" class="form-control" id="baths" name="baths" required>
									</div>
		
									<div class="mb-3">
										<label for="priceNight" class="form-label">Price Night:</label>
										<input type="number" step="0.01" class="form-control" id="priceNight" name="priceNight" required>
									</div>
		
									<div class="mb-3">
										<label for="url1" class="form-label">Url Image 1:</label> 
										<input type="text" class="form-control" id="url1" name="url1" required>
									</div>
		
									<div class="mb-3">
										<label for="url2" class="form-label">Url Image 2:</label> 
										<input type="text" class="form-control" id="url2" name="url2">
									</div>
		
									<div class="mb-3">
										<label for="url3" class="form-label">Url Image 2:</label> 
										<input type="text" class="form-control" id="url3" name="url3">
									</div>
		
									<div class="mb-3">
										<label for="availability" class="form-label">Availability:</label>
										<select class="form-control" name="availability" id="availability" required>
											<option value="true">TRUE</option>
											<option value="false">FALSE</option>
										</select>
									</div>
		
									<div class="mb-3">
										<label for="type" class="form-label">Type:</label>
										<select class="form-control" name="type" required>
										 <option value="" disabled selected>Select Type</option>
    									<% for (ApartmentType type : ApartmentType.values()) { %>
        									<option value="<%=type.name()%>"><%=type.name().toUpperCase()%></option>
									    <% } %>
										</select>
									</div>
									
									<div class="mb-3">
										<label for="offer1" class="form-label">Offer 1:</label>
										<select class="form-control" name="offerId1">
										<option value="" disabled selected>Select Offer</option>
    									<% for (Offers o : offers) { %>
        									<option value="<%=o.getId()%>"><%=o.getName().toUpperCase()%></option>
									    <% } %>
										</select>
									</div>
									
									<div class="mb-3">
										<label for="offer2" class="form-label">Offer 2:</label>
										<select class="form-control" name="offerId2">
										<option value="" disabled selected>Select Offer</option>
    									<% for (Offers o : offers) { %>
        									<option value="<%=o.getId()%>"><%=o.getName().toUpperCase()%></option>
									    <% } %>
										</select>
									</div>
									
									<div class="mb-3">
										<label for="offer3" class="form-label">Offer 3:</label>
										<select class="form-control" name="offerId3">
										<option value="" disabled selected>Select Offer</option>
    									<% for (Offers o : offers) { %>
        									<option value="<%=o.getId()%>"><%=o.getName().toUpperCase()%></option>
									    <% } %>
										</select>
									</div>
									
									<button type="submit" class="btn btn-primary">Save</button>
									
								</form>
							</div>
	
						<%
							try {
					            // Validar que los parámetros requeridos no sean nulos
					            if (request.getParameter("name") == null || request.getParameter("description") == null || 
					                request.getParameter("location") == null || request.getParameter("capacity") == null ||
					                request.getParameter("bedrooms") == null || request.getParameter("beds") == null ||
					                request.getParameter("baths") == null || request.getParameter("priceNight") == null ||
					                request.getParameter("url1") == null || request.getParameter("availability") == null ||
					                request.getParameter("type") == null) {
					                throw new Exception("Todos los campos requeridos deben estar completos");
					            }
	
					            String name = request.getParameter("name");
					            String description = request.getParameter("description");
					            String location = request.getParameter("location");
					            
					            // Convertir parámetros numéricos con manejo de errores
					            int capacity, bedrooms, beds, baths;
					            double pricePerNight;
					            
					            try {
					                capacity = Integer.parseInt(request.getParameter("capacity"));
					                bedrooms = Integer.parseInt(request.getParameter("bedrooms"));
					                beds = Integer.parseInt(request.getParameter("beds"));
					                baths = Integer.parseInt(request.getParameter("baths"));
					                pricePerNight = Double.parseDouble(request.getParameter("priceNight"));
					            } catch (NumberFormatException e) {
					                throw new Exception("Los campos numéricos deben contener valores válidos");
					            }
					            
					            boolean availability = Boolean.parseBoolean(request.getParameter("availability"));
					            String apartmentTypeParam = request.getParameter("type");
					            ApartmentType apartmentType;
					            
					            try {
					                apartmentType = ApartmentType.valueOf(apartmentTypeParam.toUpperCase());
					            } catch (IllegalArgumentException e) {
					                throw new Exception("Tipo de apartamento no válido");
					            }
					            
					            String url1 = request.getParameter("url1");
					            String url2 = request.getParameter("url2");
					            String url3 = request.getParameter("url3");
					            
					            // Insertar en la base de datos
					            int idApartment = apartmentDAO.insert(name, description, location, capacity, bedrooms, 
					                beds, baths, pricePerNight, availability, apartmentType);
					            
					            // Insertar imágenes
					            imagesDAO.insert(url1, idApartment);
					            if (url2 != null && !url2.trim().isEmpty()) {
					                imagesDAO.insert(url2, idApartment);
					            }
					            if (url3 != null && !url3.trim().isEmpty()) {
					                imagesDAO.insert(url3, idApartment);
					            }
					            
					            // Estos son los id de las ofertas
					            int offerId1 = Integer.parseInt(request.getParameter("offerId1"));
					            int offerId2 = Integer.parseInt(request.getParameter("offerId2"));
					            int offerId3 = Integer.parseInt(request.getParameter("offerId3"));
					            
					            // insertar offertas
					            apartmentsOffersDAO.insert(idApartment, offerId1);
					            
					            if (request.getParameter("offerId2") != null && !request.getParameter("offerId2").isEmpty()){
					            	apartmentsOffersDAO.insert(idApartment, offerId2);	
					            }
					            if (request.getParameter("offerId3") != null && !request.getParameter("offerId3").isEmpty()){
					            	apartmentsOffersDAO.insert(idApartment, offerId3);	
					            }
					            
					            out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
					            out.println("<strong>Éxito!</strong> La propiedad se ha registrado correctamente.");
					            out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
					            out.println("</div>");
					            
					            // Limpiar el formulario (opcional)
					            out.println("<script>document.getElementById('tuFormId').reset();</script>");
					            
					        } catch (Exception e) {
					            out.println("<div class='alert alert-danger' role='alert'>ERROR: " + e.getMessage() + "</div>");
					        }
				    	}
				    	%>
				    	
						<!-- Update Stay -->
						
						<%
						if ("action3".equals(action)) {
						
						    // Procesar actualización si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {

						        String updateId = request.getParameter("updateId");
						
						        String name = request.getParameter("name");
						        String description = request.getParameter("description");
						        String location = request.getParameter("location");
						        String capacityStr = request.getParameter("capacity");
						        String bedroomsStr = request.getParameter("bedrooms");
						        String bedsStr = request.getParameter("beds");
						        String bathsStr = request.getParameter("baths");
						        String priceNightStr = request.getParameter("priceNight");
						        String url1 = request.getParameter("url1");
						        String url2 = request.getParameter("url2");
						        String url3 = request.getParameter("url3");
						        String availabilityStr = request.getParameter("availability");
						        String typeIdStr = request.getParameter("type");
						        ApartmentType apartmentType;
						
						        if (name != null && description != null && location != null && capacityStr != null &&
						            bedroomsStr != null && bedsStr != null && bathsStr != null && priceNightStr != null &&
						            url1 != null && availabilityStr != null && typeIdStr != null && updateId != null) {
						
						            try {
						            	
						            	int id = Integer.parseInt(updateId);
						                int capacity = Integer.parseInt(capacityStr);
						                int bedrooms = Integer.parseInt(bedroomsStr);
						                int beds = Integer.parseInt(bedsStr);
						                int baths = Integer.parseInt(bathsStr);
						                double pricePerNight = Double.parseDouble(priceNightStr);
						                boolean availability = Boolean.parseBoolean(availabilityStr);
						                apartmentType = ApartmentType.valueOf(typeIdStr);
							            
						                int rowsUpdated = apartmentDAO.update(id, name, description, location, capacity, bedrooms, beds, baths, pricePerNight, availability, apartmentType);
						                
						                // Estos son los id de las ofertas
							            int offerId1 = Integer.parseInt(request.getParameter("offerId1"));
							            int offerId2 = Integer.parseInt(request.getParameter("offerId2"));
							            int offerId3 = Integer.parseInt(request.getParameter("offerId3"));
							            
							            // insertar offertas
							            apartmentsOffersDAO.insert(id, offerId1);
							            
							            if (request.getParameter("offerId2") != null && !request.getParameter("offerId2").isEmpty()){
							            	apartmentsOffersDAO.insert(id, offerId2);	
							            }
							            if (request.getParameter("offerId3") != null && !request.getParameter("offerId3").isEmpty()){
							            	apartmentsOffersDAO.insert(id, offerId3);	
							            }
						
						                if (rowsUpdated > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Apartamento con ID " + id + " actualizado.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró el apartamento con ID: " + id + "</div>");
						                }
						
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>Faltan datos para actualizar el apartamento</div>");
						        }
						    }
						
						    // Mostrar formulario de edición si updateId existe
						    String updateId = request.getParameter("updateId");
						    if (updateId != null && !updateId.isEmpty()) {
						        try {
						        	int id = Integer.parseInt(updateId);
						            Apartments apartmentsEdit = apartmentDAO.selectAll()
						                .stream()
						                .filter(u -> u.getId() == id)
						                .findFirst()
						                .orElse(null);
						
						            if (apartmentsEdit != null) {
							%>
						                <div class="texto mx-4 my-3">
						                    <form method="post">
						                        <input type="hidden" name="action" value="action3" />
						                        <input type="hidden" name="updateId" value="<%= apartmentsEdit.getId() %>" />
												
						                        <div class="mb-3">
													<label for="name" class="form-label">Name:</label> 
													<input type="text" class="form-control" id="name" name="name" value="<%=apartmentsEdit.getName()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="description" class="form-label">Description:</label>
													<input type="text" class="form-control" id="description" name="description" value="<%=apartmentsEdit.getDescription()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="location" class="form-label">Location:</label> 
													<input type="text" class="form-control" id="location" name="location" value="<%=apartmentsEdit.getLocation()%>" required >
												</div>
					
												<div class="mb-3">
													<label for="capacity" class="form-label">Capacity:</label> 
													<input type="number" class="form-control" id="capacity" name="capacity" value="<%=apartmentsEdit.getCapacity()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="bedrooms" class="form-label">BedRooms:</label> 
													<input type="number" class="form-control" id="bedrooms" name="bedrooms" value="<%=apartmentsEdit.getBedrooms()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="beds" class="form-label">Beds:</label> 
													<input type="number" class="form-control" id="beds" name="beds" value="<%=apartmentsEdit.getBeds()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="baths" class="form-label">Baths:</label> 
													<input type="number" class="form-control" id="baths" name="baths" value="<%=apartmentsEdit.getBaths()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="priceNight" class="form-label">Price Night:</label>
													<input type="number" step="0.01" class="form-control" id="priceNight" name="priceNight" value="<%=apartmentsEdit.getPricePerNight()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="url1" class="form-label">Url Image 1:</label> 
													<input type="text" class="form-control" id="url1" name="url1" required>
												</div>
					
												<div class="mb-3">
													<label for="url2" class="form-label">Url Image 2:</label> 
													<input type="text" class="form-control" id="url2" name="url2">
												</div>
					
												<div class="mb-3">
													<label for="url3" class="form-label">Url Image 2:</label> 
													<input type="text" class="form-control" id="url3" name="url3">
												</div>
					
												<div class="mb-3">
												    <label for="availability" class="form-label">Availability:</label>
												    <select class="form-control" name="availability" id="availability" required>
												        <option value="true" <%= apartmentsEdit.isListedAvailable() ? "selected" : "" %>>TRUE</option>
												        <option value="false" <%= !apartmentsEdit.isListedAvailable() ? "selected" : "" %>>FALSE</option>
												    </select>
												</div>
												
												<div class="mb-3">
													<label for="type" class="form-label">Type:</label>
													<select class="form-control" name="type" required>
			    									<% for (ApartmentType type : ApartmentType.values()) { %>
												        <option value="<%= type.name() %>" <%= type == apartmentsEdit.getApartmentType() ? "selected" : "" %>>
												            <%= type.name() %>
												        </option>
												    <% } %>
													</select>
												</div>

												<div class="mb-3">
													<label for="offer1" class="form-label">Offer 1:</label>
													<select class="form-control" name="offerId1">
													<option value="" disabled selected>Select Offer</option>
			    									<% for (Offers o : offers) { %>
			        									<option value="<%=o.getId()%>"><%=o.getName().toUpperCase()%></option>
												    <% } %>
													</select>
												</div>
												
												<div class="mb-3">
													<label for="offer2" class="form-label">Offer 2:</label>
													<select class="form-control" name="offerId2">
													<option value="" disabled selected>Select Offer</option>
			    									<% for (Offers o : offers) { %>
			        									<option value="<%=o.getId()%>"><%=o.getName().toUpperCase()%></option>
												    <% } %>
													</select>
												</div>
												
												<div class="mb-3">
													<label for="offer3" class="form-label">Offer 3:</label>
													<select class="form-control" name="offerId3">
													<option value="" disabled selected>Select Offer</option>
			    									<% for (Offers o : offers) { %>
			        									<option value="<%=o.getId()%>"><%=o.getName().toUpperCase()%></option>
												    <% } %>
													</select>
												</div>
												
												<button type="submit" class="btn btn-primary">Update</button>
						                        
						                    </form>
						                </div>
							<%
						            } else {
						                out.println("<div class='alert alert-warning mx-4 mt-3'>Apartamento no encontrado</div>");
						            }
						        } catch (Exception e) {
						            out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						        }
						    }
						
						    // Mostrar tabla solo si NO estamos en modo edición
						    if (updateId == null || updateId.isEmpty()) {
						        apartments = apartmentDAO.selectAll();
							%>
							
							<div class="table-responsive mx-4">
						    <table class="table table-striped table-bordered">
						        <thead class="table-dark text-center">
						            <tr>
						                <th>ID</th>
						                <th>Nombre</th>
						                <th>Ubicación</th>
						                <th>Precio/Noche</th>
						                <th>Tipo</th>
						                <th>-</th>
						            </tr>
						        </thead>
						        <tbody>
						            <% for (Apartments apt : apartments) { 
						                String imageUrl = (apt.getImages() != null && !apt.getImages().isEmpty()) ? 
						                    apt.getImages().get(0).getUrl() : "images/default.jpg";
						            %>
						            <tr>
						                <td class="align-middle text-center"><%=apt.getId()%></td>
						                <td class="align-middle"><%=apt.getName()%></td>
						                <td class="align-middle"><%=apt.getLocation()%></td>
						                <td class="align-middle text-end">$<%=String.format("%.2f", apt.getPricePerNight())%></td>
						                <td class="align-middle"><%=apt.getApartmentType().toString().toUpperCase()%></td>
						                <td class="text-center">
						                    <form method="POST">
						                        <input type="hidden" name="action" value="action3" />
						                        <input type="hidden" name="updateId" value="<%=apt.getId()%>" />
						                        <button type="submit" class="btn btn-success btn-sm" title="Actualizar propiedad">
						                            <i class="fas fa-sync-alt"></i> Update
						                        </button>
						                    </form>
						                </td>
						            </tr>
						            <% } %>
						        </tbody>
						    </table>
						</div>
						<%
						    }
						}
						%>
						
						<!-- Delete Stay -->
						<%
						if ("action4".equals(action)) {
						    // Procesar eliminación si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						        String deleteId = request.getParameter("deleteId");
						        if (deleteId != null && !deleteId.isEmpty()) {
						            try {
						                int id = Integer.parseInt(deleteId);
						                
						                // 1. Primero eliminar las relaciones (ofertas e imágenes)
						                apartmentsOffersDAO.deleteByApartmentId(id);
						                imagesDAO.delete(id);
						                
						                // 2. Luego eliminar la propiedad
						                int deletedId = apartmentDAO.delete(id);
						                
						                if (deletedId > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Propiedad ID " + deletedId + " eliminada.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró la propiedad con ID: " + id + "</div>");
						                }
						            } catch (NumberFormatException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: ID inválido</div>");
						            } catch (SQLException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error en base de datos: " + e.getMessage() + "</div>");
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>No se proporcionó ID para eliminar</div>");
						        }
						    }
						    
						    // Mostrar tabla de propiedades actualizada
						    apartments = apartmentDAO.selectAll();
						%>
						<div class="table-responsive mx-4">
						    <table class="table table-striped table-bordered">
						        <thead class="table-dark text-center">
						            <tr>
						                <th>ID</th>
						                <th>Nombre</th>
						                <th>Ubicación</th>
						                <th>Precio/Noche</th>
						                <th>Tipo</th>
						                <th>-</th>
						            </tr>
						        </thead>
						        <tbody>
						            <% for (Apartments apt : apartments) { 
						                String imageUrl = (apt.getImages() != null && !apt.getImages().isEmpty()) ? 
						                    apt.getImages().get(0).getUrl() : "images/default.jpg";
						            %>
						            <tr>
						                <td class="align-middle text-center"><%=apt.getId()%></td>
						                <td class="align-middle"><%=apt.getName()%></td>
						                <td class="align-middle"><%=apt.getLocation()%></td>
						                <td class="align-middle text-end">$<%=String.format("%.2f", apt.getPricePerNight())%></td>
						                <td class="align-middle"><%=apt.getApartmentType().toString().toUpperCase()%></td>
						                <td class="text-center">
						                    <form method="POST" onsubmit="return confirm('¿Confirmas eliminar <%=apt.getName()%>?');">
						                        <input type="hidden" name="action" value="action4" />
						                        <input type="hidden" name="deleteId" value="<%=apt.getId()%>" />
						                        <button type="submit" class="btn btn-danger btn-sm" title="Eliminar propiedad">
						                            <i class="fas fa-trash-alt"></i> Delete
						                        </button>
						                    </form>
						                </td>
						            </tr>
						            <% } %>
						        </tbody>
						    </table>
						</div>
						<%
						}
						%>
							
						<!------------------------- Bookings --------------------------->
						
						<!-- List Bookings -->
						<%
						if ("action5".equals(action)) {
						%>
						<div class="table-responsive mx-4">
							<table class="table table-striped table-bordered">
								<thead class="table-dark text-center">
									<tr>
										<th>Apartment ID</th>
										<th>User ID</th>
										<th>Start/Date</th>
										<th>End/Date</th>
										<th>TotalPrice</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (Booking b : bookings) {
									%>
									<tr>
										<td class="align-middle text-center">
										    <%= b.getApartment() != null ? b.getApartment().getId() : "N/A" %>
										</td>
										<td class="align-middle text-center"><%=(b.getUser() != null) ? b.getUser().getId() : "N/A"%></td>
										<td class="align-middle text-center"><%=b.getStartDate()%></td>
										<td class="align-middle text-center"><%=b.getEndDate()%></td>
										<td class="align-middle text-center"><%=b.getTotalPrice()%></td>
										<td class="align-middle text-center"><%=b.getStatus()%></td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</div>
						<%
						}
						%>
						
						<!-- Create Booking -->
						
						<%
						if ("POST".equalsIgnoreCase(request.getMethod()) && "action6".equals(request.getParameter("action"))) {
						%>
							<div class="texto">
								<form method="post" class="mx-4 my-3">
									<input type="hidden" name="action" value="action6" />
		
									<div class="mb-3">
										<label for="startDate" class="form-label">Start Date:</label> 
										<input type="date" class="form-control" id="startDate" name="startDate" required>
									</div>
		
									<div class="mb-3">
										<label for="endDate" class="form-label">End Date:</label>
										<input type="date" class="form-control" id="endDate" name="endDate" required>
									</div>
		
									<div class="mb-3">
										<label for="userId" class="form-label">User Id:</label> 
										<input type="number" class="form-control" id="userId" name="userId" required >
									</div>
		
									<div class="mb-3">
										<label for="apartmentId" class="form-label">Apartment Id:</label> 
										<input type="number" class="form-control" id="apartmentId" name="apartmentId" required>
									</div>
		
									<div class="mb-3">
										<label for="totalPrice" class="form-label">Total Price:</label> 
										<input type="number" step="0.01" class="form-control" id="totalPrice" name="totalPrice" required>
									</div>
									
									<div class="mb-3">
										<label for="status" class="form-label">Status:</label>
										<select class="form-control" name="status" required>
										<option value="" disabled selected>Select Status</option>
    									<% for (BookingStatus type : BookingStatus.values()) { %>
        									<option value="<%=type.name()%>"><%=type.name().toUpperCase()%></option>
									    <% } %>
										</select>
									</div>
									
									<button type="submit" class="btn btn-primary">Save</button>
									
								</form>
							</div>
						<%
							try {
					            // Validar que los parámetros requeridos no sean nulos
					            if (request.getParameter("startDate") == null || request.getParameter("endDate") == null || 
					                request.getParameter("userId") == null || request.getParameter("apartmentId") == null ||
					                request.getParameter("totalPrice") == null || request.getParameter("status") == null){
					                throw new Exception("Todos los campos requeridos deben estar completos");
					            }
					            
					            
					            LocalDate startDate, endDate;
					           	int userId, apartmentId;
					           	double totalPrice;
					           	// Verificacion casteo fechas
					           	try {
					                startDate = LocalDate.parse(request.getParameter("startDate")); // Fecha
					                endDate = LocalDate.parse(request.getParameter("endDate")); // Fecha
					                
					                // Aqui verifico si las fechas son correctas
					                if(startDate.isAfter(endDate)){ 
					                	throw new Exception("La fecha de inicio debe ser anterior a la fecha de fin.");	
					                }
					                
					            } catch (NumberFormatException e) {
					                throw new Exception("Los campos para las fechas deben contener valores válidos.");
					            }
					           	
					           	// Verificacion casto enteros/double
					            try {
					            	totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
					                userId = Integer.parseInt(request.getParameter("userId"));
					                apartmentId = Integer.parseInt(request.getParameter("apartmentId"));
					            } catch (NumberFormatException e) {
					                throw new Exception("Los campos numéricos deben contener valores válidos");
					            }
					           	
					           	String bookingStatusParam = request.getParameter("status");
					            BookingStatus status;
					            
					            try {
					            	status = BookingStatus.valueOf(bookingStatusParam.toUpperCase());
					            } catch (IllegalArgumentException e) {
					                throw new Exception("Tipo de apartamento no válido");
					            }
					            
					            // Insertar en la base de datos
					            int idBooking = bookingDAO.insert(startDate, endDate, userId, apartmentId, totalPrice, bookingStatusParam);
					            
					            out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
					            out.println("<strong>Éxito!</strong> El booking se ha registrado correctamente.");
					            out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
					            out.println("</div>");
					            
					            // Limpiar el formulario (opcional)
					            out.println("<script>document.getElementById('tuFormId').reset();</script>");
					            
					        } catch (Exception e) {
					            out.println("<div class='alert alert-danger' role='alert'>ERROR: " + e.getMessage() + "</div>");
					        }
						
						}
						%>
						
						<!-- Update Booking -->
						
						<%
						if ("action7".equals(action)) {
						
						    // Procesar actualización si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						    	
						        String updateId = request.getParameter("updateId");
						        String startStr = request.getParameter("startDate");
						        String endStr = request.getParameter("endDate");
						        String userIdStr = request.getParameter("userId");
						        String apartmentIdStr = request.getParameter("apartmentId");
						        String totalPriceStr = request.getParameter("totalPrice");
						        String bookingStatus = request.getParameter("status");

						        if (userIdStr != null && apartmentIdStr != null && totalPriceStr != null && bookingStatus != null) {
						            try {
						            	
						                LocalDate startDate = LocalDate.parse(startStr);
						                LocalDate endDate = LocalDate.parse(endStr);
						                int userId = Integer.parseInt(userIdStr);
						                int apartmentId = Integer.parseInt(apartmentIdStr);
						                double totalPrice = Double.parseDouble(totalPriceStr);
						                
						                int id = Integer.parseInt(updateId);
						
						                int rowsUpdated = bookingDAO.update(id, startDate, endDate, userId, apartmentId, totalPrice, bookingStatus);
						
						                if (rowsUpdated > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Booking con ID " + id + " actualizado.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró el booking con ID: " + id + "</div>");
						                }
						
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>Faltan datos para actualizar el usuario</div>");
						        }
						    }
						
						    // Mostrar formulario de edición si updateId existe
						    String updateId = request.getParameter("updateId");
						    if (updateId != null && !updateId.isEmpty()) {
						        try {
						        	int id = Integer.parseInt(updateId);
						            Booking bookingEdit = bookingDAO.selectAll()
						                .stream()
						                .filter(u -> u.getId() == id)
						                .findFirst()
						                .orElse(null);
						
						            if (bookingEdit != null) {
							%>
						                <div class="texto mx-4 my-3">
						                    <form method="post">
						                        <input type="hidden" name="action" value="action7" />
						                        <input type="hidden" name="updateId" value="<%= bookingEdit.getId() %>" />
						
						                        <div class="mb-3">
													<label for="startDate" class="form-label">Start Date:</label> 
													<input type="date" class="form-control" id="startDate" name="startDate" value="<%=bookingEdit.getStartDate()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="endDate" class="form-label">End Date:</label>
													<input type="date" class="form-control" id="endDate" name="endDate" value="<%=bookingEdit.getEndDate()%>" required>
												</div>
					
												<div class="mb-3">
													<label for="userId" class="form-label">User Id:</label> 
													<input type="number" class="form-control" id="userId" name="userId" value="<%=bookingEdit.getUser().getId()%>" required >
												</div>
					
												<div class="mb-3">
													<label for="apartmentId" class="form-label">Apartment Id:</label> 
													<input type="number" class="form-control" id="apartmentId" name="apartmentId" value="<%=bookingEdit.getApartment().getId()%>"required>
												</div>
					
												<div class="mb-3">
													<label for="totalPrice" class="form-label">Total Price:</label> 
													<input type="number" step="0.01" class="form-control" id="totalPrice" name="totalPrice" value="<%=bookingEdit.getTotalPrice()%>" required>
												</div>
												
												<div class="mb-3">
						                            <label for="userType" class="form-label">Type:</label>
						                            <select class="form-control" name="status" required>
						                                <% for (BookingStatus bt : BookingStatus.values()) { %>
						                                    <option value="<%= bt.name() %>" <%= bt == bookingEdit.getStatus() ? "selected" : "" %>>
						                                        <%= bt.name().toUpperCase() %>
						                                    </option>
						                                <% } %>
						                            </select>
						                        </div>
												
												<button type="submit" class="btn btn-primary">Update</button>
						                        
						                    </form>
						                </div>
							<%
						            } else {
						                out.println("<div class='alert alert-warning mx-4 mt-3'>Booking no encontrado</div>");
						            }
						        } catch (Exception e) {
						            out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						        }
						    }
						
						    // Mostrar tabla solo si NO estamos en modo edición
						    if (updateId == null || updateId.isEmpty()) {
						        bookings = bookingDAO.selectAll();
							%>
							
							<div class="table-responsive mx-4">
								<table class="table table-striped table-bordered">
									<thead class="table-dark text-center">
										<tr>
											<th>Apartment ID</th>
											<th>User ID</th>
											<th>Start/Date</th>
											<th>End/Date</th>
											<th>TotalPrice</th>
											<th>Status</th>
											<th>-</th>
										</tr>
									</thead>
									<tbody>
										<%
										for (Booking b : bookings) {
										%>
										<tr>
											<td class="align-middle text-center">
											    <% 
											    // Verifica si es el primer registro y muestra un valor por defecto
											    if (bookings.indexOf(b) == 0 && b.getApartment() == null) {
											        out.print("1"); // O el valor que corresponda
											    } else {
											        out.print(b.getApartment() != null ? b.getApartment().getId() : "N/A");
											    }
											    %>
											</td>
											<td class="align-middle text-center"><%=(b.getUser() != null) ? b.getUser().getId() : "N/A"%></td>
											<td class="align-middle text-center"><%=b.getStartDate()%></td>
											<td class="align-middle text-center"><%=b.getEndDate()%></td>
											<td class="align-middle text-center"><%=b.getTotalPrice()%></td>
											<td class="align-middle text-center"><%=b.getStatus()%></td>
											<td class="text-center">
							                    <form method="POST">
							                        <input type="hidden" name="action" value="action7" />
							                        <input type="hidden" name="updateId" value="<%=b.getId()%>" />
							                        <button type="submit" class="btn btn-success btn-sm" title="Actualizar booking">
							                            <i class="fas fa-sync-alt"></i> Update
							                        </button>
							                    </form>
							                </td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
						<%
						    }
						}
						%>
						
						<!-- Delete  Booking -->
						
						<%
						if ("action8".equals(action)) {
						    // Procesar eliminación si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						        String deleteId = request.getParameter("deleteId");
						        if (deleteId != null && !deleteId.isEmpty()) {
						            try {
						                int id = Integer.parseInt(deleteId);
						                
						                int deletedId = bookingDAO.delete(id);
						                
						                if (deletedId > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Booking ID " + deletedId + " eliminado.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró el booking con ID: " + id + "</div>");
						                }
						            } catch (NumberFormatException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: ID inválido</div>");
						            } catch (SQLException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error en base de datos: " + e.getMessage() + "</div>");
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>No se proporcionó ID para eliminar</div>");
						        }
						    }
						    
						    // Mostrar tabla de propiedades actualizada
						    users = userDAO.select();
						%>
						<div class="table-responsive mx-4">
							<table class="table table-striped table-bordered">
								<thead class="table-dark text-center">
									<tr>
										<th>Apartment ID</th>
										<th>User ID</th>
										<th>Start/Date</th>
										<th>End/Date</th>
										<th>TotalPrice</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (Booking b : bookings) {
									%>
									<tr>
										<td class="align-middle text-center">
										    <% 
										    // Verifica si es el primer registro y muestra un valor por defecto
										    if (bookings.indexOf(b) == 0 && b.getApartment() == null) {
										        out.print("1"); // O el valor que corresponda
										    } else {
										        out.print(b.getApartment() != null ? b.getApartment().getId() : "N/A");
										    }
										    %>
										</td>
										<td class="align-middle text-center"><%=(b.getUser() != null) ? b.getUser().getId() : "N/A"%></td>
										<td class="align-middle text-center"><%=b.getStartDate()%></td>
										<td class="align-middle text-center"><%=b.getEndDate()%></td>
										<td class="align-middle text-center"><%=b.getTotalPrice()%></td>
										<td class="align-middle text-center"><%=b.getStatus()%></td>
										<td class="text-center">
						                    <form method="POST" onsubmit="return confirm('¿Confirmas eliminar booking con id <%=b.getId()%>?');">
						                        <input type="hidden" name="action" value="action8" />
						                        <input type="hidden" name="deleteId" value="<%=b.getId()%>" />
						                        <button type="submit" class="btn btn-danger btn-sm" title="Eliminar booking">
						                            <i class="fas fa-trash-alt"></i> Delete
						                        </button>
						                    </form>
						                </td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</div>
						<%
						}
						%>
		
						<!------------------------ Users --------------------------->
						
						<!-- List Users -->
						<%
						if ("action9".equals(action)) {
						%>
						<div class="table-responsive mx-4">
							<table class="table table-striped table-bordered">
								<thead class="table-dark text-center">
									<tr>
										<th>Name</th>
										<th>Email</th>
										<th>Password</th>
										<th>Type</th>
										<th>Address</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (User u : users) {
									%>
									<tr>
										<td class="align-middle text-center"><%=u.getName()%></td>
										<td class="align-middle text-center"><%=u.getEmail()%></td>
										<td class="align-middle text-center"><%=u.getPassword()%></td>
										<td class="align-middle text-center"><%=u.getType()%></td>
										<td class="align-middle text-center"><%=u.getAddress()%></td>
									</tr>
									<%
									}
									%>
	
								</tbody>
							</table>
						</div>
						<%
						}
						%>
						
						<!-- Create User -->
						
						<%
						if ("POST".equalsIgnoreCase(request.getMethod()) && "action10".equals(request.getParameter("action"))) {
						%>
							<div class="texto">
								<form method="post" class="mx-4 my-3">
									<input type="hidden" name="action" value="action10"/>
		
									<div class="mb-3">
										<label for="name" class="form-label">Name:</label> 
										<input type="text" class="form-control" id="name" name="name" required>
									</div>
		
									<div class="mb-3">
										<label for="email" class="form-label">Email:</label>
										<input type="email" class="form-control" id="email" name="email" required>
									</div>
		
									<div class="mb-3">
										<label for="password" class="form-label">Password:</label> 
										<input type="password" class="form-control" id="password" name="password" required >
									</div>
									
									<div class="mb-3">
										<label for="userType" class="form-label">Type:</label>
										<select class="form-control" name="userType" required>
										<option value="" disabled selected>Select UserType</option>
    									<% for (UserType u : UserType.values()) { %>
        									<option value="<%=u.name()%>"><%=u.name().toUpperCase()%></option>
									    <% } %>
										</select>
									</div>
									
									<div class="mb-3">
										<label for="address" class="form-label">Addres:</label> 
										<input type="text" class="form-control" id="address" name="address" required >
									</div>
									
									<button type="submit" class="btn btn-primary">Save</button>
									
								</form>
							</div>
						<%
							try {
					            // Validar que los parámetros requeridos no sean nulos
					            if (request.getParameter("name") == null || request.getParameter("email") == null || 
					                request.getParameter("password") == null || request.getParameter("userType") == null ||
					                request.getParameter("address") == null){
					                throw new Exception("Todos los campos requeridos deben estar completos");
					            }
					            
					            String nameUser = request.getParameter("name");
					            String email = request.getParameter("email");
					            String password = request.getParameter("password");
					            String address = request.getParameter("address");
					           	
					            UserType userType;
					            
					            try {
					            	userType = UserType.valueOf(request.getParameter("userType"));
					            } catch (IllegalArgumentException e) {
					                throw new Exception("Tipo de usuario no válido");
					            }
					            
					            // Insertar en la base de datos
					            int idUser = userDAO.insert(nameUser, email, password, userType, address);
					            
					            out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
					            out.println("<strong>Éxito!</strong> El usuario se ha registrado correctamente.");
					            out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
					            out.println("</div>");
					            
					            // Limpiar el formulario (opcional)
					            out.println("<script>document.getElementById('tuFormId').reset();</script>");
					            
					        } catch (Exception e) {
					            out.println("<div class='alert alert-danger' role='alert'>ERROR: " + e.getMessage() + "</div>");
					        }
						
						}
						%>
						
						<!-- Update User -->
						
						<%
						if ("action11".equals(action)) {
						
						    // Procesar actualización si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						        String updateId = request.getParameter("updateId");
						
						        String name = request.getParameter("name");
						        String email = request.getParameter("email");
						        String password = request.getParameter("password");
						        String address = request.getParameter("address");
						        String typeStr = request.getParameter("userType");
						
						        if (updateId != null && name != null && email != null && password != null && typeStr != null && address != null) {
						            try {
						                int id = Integer.parseInt(updateId);
						                UserType userType = UserType.valueOf(typeStr);
						
						                int rowsUpdated = userDAO.update(id, name, email, password, userType, address);
						
						                if (rowsUpdated > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Usuario con ID " + id + " actualizado.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró el usuario con ID: " + id + "</div>");
						                }
						
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>Faltan datos para actualizar el usuario</div>");
						        }
						    }
						
						    // Mostrar formulario de edición si updateId existe
						    String updateId = request.getParameter("updateId");
						    if (updateId != null && !updateId.isEmpty()) {
						        try {
						            int id = Integer.parseInt(updateId);
						            User userEdit = userDAO.select()
						                .stream()
						                .filter(u -> u.getId() == id)
						                .findFirst()
						                .orElse(null);
						
						            if (userEdit != null) {
						%>
						                <div class="texto mx-4 my-3">
						                    <form method="post">
						                        <input type="hidden" name="action" value="action11" />
						                        <input type="hidden" name="updateId" value="<%= userEdit.getId() %>" />
						
						                        <div class="mb-3">
						                            <label for="name" class="form-label">Name:</label>
						                            <input type="text" class="form-control" id="name" name="name" value="<%= userEdit.getName() %>" required />
						                        </div>
						
						                        <div class="mb-3">
						                            <label for="email" class="form-label">Email:</label>
						                            <input type="email" class="form-control" id="email" name="email" value="<%= userEdit.getEmail() %>" required />
						                        </div>
						
						                        <div class="mb-3">
						                            <label for="password" class="form-label">Password:</label>
						                            <input type="text" class="form-control" id="password" name="password" value="<%= userEdit.getPassword() %>" required />
						                        </div>
						
						                        <div class="mb-3">
						                            <label for="userType" class="form-label">Type:</label>
						                            <select class="form-control" name="userType" required>
						                                <% for (UserType u : UserType.values()) { %>
						                                    <option value="<%= u.name() %>" <%= u == userEdit.getType() ? "selected" : "" %>>
						                                        <%= u.name().toUpperCase() %>
						                                    </option>
						                                <% } %>
						                            </select>
						                        </div>
						
						                        <div class="mb-3">
						                            <label for="address" class="form-label">Address:</label>
						                            <input type="text" class="form-control" id="address" name="address" value="<%= userEdit.getAddress() %>" required />
						                        </div>
						
						                        <button type="submit" class="btn btn-primary">Actualizar</button>
						                    </form>
						                </div>
						<%
						            } else {
						                out.println("<div class='alert alert-warning mx-4 mt-3'>Usuario no encontrado</div>");
						            }
						        } catch (Exception e) {
						            out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						        }
						    }
						
						    // Mostrar tabla solo si NO estamos en modo edición
						    if (updateId == null || updateId.isEmpty()) {
						        users = userDAO.select();
						%>
						        <div class="table-responsive mx-4">
						            <table class="table table-striped table-bordered">
						                <thead class="table-dark text-center">
						                    <tr>
						                        <th>ID</th>
						                        <th>Nombre</th>
						                        <th>Email</th>
						                        <th>Type</th>
						                        <th>-</th>
						                    </tr>
						                </thead>
						                <tbody>
						                    <% for (User u : users) { %>
						                    <tr>
						                        <td class="align-middle text-center"><%= u.getId() %></td>
						                        <td class="align-middle"><%= u.getName() %></td>
						                        <td class="align-middle"><%= u.getEmail() %></td>
						                        <td class="align-middle"><%= u.getType() %></td>
						                        <td class="text-center">
						                            <form method="post">
						                                <input type="hidden" name="action" value="action11" />
						                                <input type="hidden" name="updateId" value="<%= u.getId() %>" />
						                                <button type="submit" class="btn btn-success btn-sm" title="Editar usuario">
						                                    <i class="fas fa-sync-alt"></i> Update
						                                </button>
						                            </form>
						                        </td>
						                    </tr>
						                    <% } %>
						                </tbody>
						            </table>
						        </div>
						<%
						    }
						}
						%>

						<!-- Delete User -->
						
						<%
						if ("action12".equals(action)) {
						    // Procesar eliminación si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						        String deleteId = request.getParameter("deleteId");
						        if (deleteId != null && !deleteId.isEmpty()) {
						            try {
						                int id = Integer.parseInt(deleteId);
						                
						                int deletedId = userDAO.delete(id);
						                
						                if (deletedId > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Usuario ID " + deletedId + " eliminado.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró el usuario con ID: " + id + "</div>");
						                }
						            } catch (NumberFormatException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: ID inválido</div>");
						            } catch (SQLException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error en base de datos: " + e.getMessage() + "</div>");
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>No se proporcionó ID para eliminar</div>");
						        }
						    }
						    
						    // Mostrar tabla de propiedades actualizada
						    users = userDAO.select();
						%>
						<div class="table-responsive mx-4">
						    <table class="table table-striped table-bordered">
						        <thead class="table-dark text-center">
						            <tr>
						                <th>ID</th>
						                <th>Nombre</th>
						                <th>Email</th>
						                <th>Type</th>
						                <th>-</th>
						            </tr>
						        </thead>
						        <tbody>
						            <% for (User u : users) {
						            %>
						            <tr>
						                <td class="align-middle text-center"><%=u.getId()%></td>
						                <td class="align-middle"><%=u.getName()%></td>
						                <td class="align-middle"><%=u.getEmail()%></td>
						                <td class="align-middle"><%=u.getType()%></td>
						                <td class="text-center">
						                    <form method="POST" onsubmit="return confirm('¿Confirmas eliminar usuario con id <%=u.getId()%> y nombre <%=u.getName()%>?');">
						                        <input type="hidden" name="action" value="action12" />
						                        <input type="hidden" name="deleteId" value="<%=u.getId()%>" />
						                        <button type="submit" class="btn btn-danger btn-sm" title="Eliminar usuario">
						                            <i class="fas fa-trash-alt"></i> Delete
						                        </button>
						                    </form>
						                </td>
						            </tr>
						            <% } %>
						        </tbody>
						    </table>
						</div>
						<%
						}
						%>
						
						<!------------------------ Offers --------------------------->
						
						<!-- List Offers -->
						<%
						if ("action13".equals(action)) {
						%>
						<div class="table-responsive mx-4">
							<table class="table table-striped table-bordered">
								<thead class="table-dark text-center">
									<tr>
										<th>Oferta ID</th>
										<th>Name</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (Offers u : offers) {
									%>
									<tr>
										<td class="align-middle text-center"><%=u.getId()%></td>
										<td class="align-middle text-center"><%=u.getName()%></td>
									</tr>
									<%
									}
									%>
	
								</tbody>
							</table>
						</div>
						<%
						}
						%>
						
						<!-- Create Offers -->
							
						<%
						if ("POST".equalsIgnoreCase(request.getMethod()) && "action14".equals(request.getParameter("action"))) {
						%>
							<div class="texto">
								<form method="post" class="mx-4 my-3">
									<input type="hidden" name="action" value="action14"/>
		
									<div class="mb-3">
										<label for="offer" class="form-label">Offer Description:</label> 
										<input type="text" class="form-control" id="offer" name="offer" required>
									</div>
									
									<button type="submit" class="btn btn-primary">Save</button>
									
								</form>
							</div>
						<%
							try {
					            // Validar que los parámetros requeridos no sean nulos
					            if (request.getParameter("offer") == null){
					                throw new Exception("Todos los campos requeridos deben estar completos");
					            }
					            
					            String offerDescription = request.getParameter("offer");
					            
					            // Insertar en la base de datos
					            int idOffer = offersDAO.insert(offerDescription);
					            
					            out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
					            out.println("<strong>Éxito!</strong> La oferta se ha registrado correctamente.");
					            out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
					            out.println("</div>");
					            
					            // Limpiar el formulario (opcional)
					            out.println("<script>document.getElementById('tuFormId').reset();</script>");
					            
					        } catch (Exception e) {
					            out.println("<div class='alert alert-danger' role='alert'>ERROR: " + e.getMessage() + "</div>");
					        }
						
						}
						%>
						
						<!-- Update Offers -->
						
						<%
						if ("action15".equals(action)) {
						
						    // Procesar actualización si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						        String updateId = request.getParameter("updateId");
						        String offerName = request.getParameter("offer");
						
						        if (updateId != null && !updateId.isEmpty() && offerName != null && !offerName.isEmpty()) {
						            try {
						                int id = Integer.parseInt(updateId);
						
						                int rowsUpdated = offersDAO.update(id, offerName);
						
						                if (rowsUpdated > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Oferta con ID " + id + " actualizada.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>No se encontró la oferta con ID: " + id + "</div>");
						                }
						            } catch (NumberFormatException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: ID inválido</div>");
						            } catch (SQLException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error en base de datos: " + e.getMessage() + "</div>");
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>Faltan datos para actualizar la oferta</div>");
						        }
						    }
						
						    // Mostrar formulario de edición
						    String updateId = request.getParameter("updateId");
						    if (updateId != null && !updateId.isEmpty()) {
						        try {
						            int id = Integer.parseInt(updateId);
						            Offers offerToEdit = offersDAO.SelectAll()
						                .stream()
						                .filter(o -> o.getId() == id)
						                .findFirst()
						                .orElse(null);
						
						            if (offerToEdit != null) {
						%>
						                <div class="texto mx-4 my-3">
						                    <form method="post">
						                        <input type="hidden" name="action" value="action15" />
						                        <input type="hidden" name="updateId" value="<%= offerToEdit.getId() %>" />
						                        <div class="mb-3">
						                            <label for="offer" class="form-label">Descripción de la oferta:</label>
						                            <input type="text" class="form-control" id="offer" name="offer" value="<%= offerToEdit.getName() %>" required />
						                        </div>
						                        <button type="submit" class="btn btn-primary">Actualizar</button>
						                    </form>
						                </div>
						<%
						            } else {
						                out.println("<div class='alert alert-warning mx-4 mt-3'>Oferta no encontrada</div>");
						            }
						        } catch (NumberFormatException e) {
						            out.println("<div class='alert alert-danger mx-4 mt-3'>ID inválido</div>");
						        } catch (Exception e) {
						            out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						        }
						    }
						
						    // Mostrar tabla de ofertas actualizada solo si NO estamos en modo edición
						    if (updateId == null || updateId.isEmpty()) {
						        offers = offersDAO.SelectAll();
						%>
						        <div class="table-responsive mx-4">
						            <table class="table table-striped table-bordered">
						                <thead class="table-dark text-center">
						                    <tr>
						                        <th>Id</th>
						                        <th>Description</th>
						                        <th>-</th>
						                    </tr>
						                </thead>
						                <tbody>
						                    <% for (Offers o : offers) { %>
						                    <tr>
						                        <td class="align-middle text-center"><%= o.getId() %></td>
						                        <td class="align-middle"><%= o.getName() %></td>
						                        <td class="text-center">
						                            <form method="post">
						                                <input type="hidden" name="action" value="action15" />
						                                <input type="hidden" name="updateId" value="<%= o.getId() %>" />
						                                <button type="submit" class="btn btn-success btn-sm" title="Actualizar oferta">
						                                    <i class="fas fa-sync-alt"></i> Update
						                                </button>
						                            </form>
						                        </td>
						                    </tr>
						                    <% } %>
						                </tbody>
						            </table>
						        </div>
						<%
						    }
						}
						%>

						<!-- Delete Offers -->
						
						<%
						if ("action16".equals(action)) {
						    // Procesar eliminación si se envió el formulario
						    if ("POST".equalsIgnoreCase(request.getMethod())) {
						        String deleteId = request.getParameter("deleteId");
						        if (deleteId != null && !deleteId.isEmpty()) {
						            try {
						                int id = Integer.parseInt(deleteId);
						                
						                int deletedId = offersDAO.delete(id);
						                
						                if (deletedId > 0) {
						                    out.println("<div class='alert alert-success alert-dismissible fade show mx-4 mt-3'>");
						                    out.println("  <strong>¡Éxito!</strong> Oferta con ID " + deletedId + " eliminado.");
						                    out.println("  <button type='button' class='btn-close' data-bs-dismiss='alert'></button>");
						                    out.println("</div>");
						                } else {
						                    out.println("<div class='alert alert-warning mx-4 mt-3'>La oferta con el usuario con ID: " + id + "</div>");
						                }
						            } catch (NumberFormatException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error: ID inválido</div>");
						            } catch (SQLException e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error en base de datos: " + e.getMessage() + "</div>");
						            } catch (Exception e) {
						                out.println("<div class='alert alert-danger mx-4 mt-3'>Error inesperado: " + e.getMessage() + "</div>");
						            }
						        } else {
						            out.println("<div class='alert alert-warning mx-4 mt-3'>No se proporcionó ID para eliminar</div>");
						        }
						    }
						    
						    // Mostrar tabla de propiedades actualizada
						    offers = offersDAO.SelectAll();
						%>
						<div class="table-responsive mx-4">
						    <table class="table table-striped table-bordered">
						        <thead class="table-dark text-center">
						            <tr>
						                <th>Id</th>
						                <th>Description</th>
						                <th>-</th>
						            </tr>
						        </thead>
						        <tbody>
						            <% for (Offers o : offers) {
						            %>
						            <tr>
						                <td class="align-middle text-center"><%=o.getId()%></td>
						                <td class="align-middle"><%=o.getName()%></td>
						                <td class="text-center">
						                    <form method="POST" onsubmit="return confirm('¿Confirmas eliminar oferta con id <%=o.getId()%> y description <%=o.getName()%>?');">
						                        <input type="hidden" name="action" value="action16" />
						                        <input type="hidden" name="deleteId" value="<%=o.getId()%>" />
						                        <button type="submit" class="btn btn-danger btn-sm" title="Eliminar oferta">
						                            <i class="fas fa-trash-alt"></i> Delete
						                        </button>
						                    </form>
						                </td>
						            </tr>
						            <% } %>
						        </tbody>
						    </table>
						</div>
						<%
						}
						%>
									
					</div>
				</div>
			</div>
		</div>
	
		<jsp:include page="footer.jsp" />
	
	</body>
</html>