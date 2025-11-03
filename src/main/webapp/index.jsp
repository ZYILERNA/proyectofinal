<%@page import="daxbnb.model.ApartmentsNameComparator"%>
<%@page import="daxbnb.model.ApartmentsPriceComparator"%>
<%@page import="java.util.*"%>
<%@page import="daxbnb.DAO.ApartmentDAO"%>
<%@page import="daxbnb.model.Apartments"%>
<%@page import="daxbnb.model.Images"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apartments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>

<jsp:include page="header.jsp" />

<%
    String typeFilter = request.getParameter("type");
    String locationSearch = request.getParameter("location");
    String sortBy = request.getParameter("sort");

    ApartmentDAO apartmentDAO = new ApartmentDAO();
    List<Apartments> apartments = apartmentDAO.selectAll();

    if (apartments != null) {
        // Filtro por tipo
        if (typeFilter != null && !typeFilter.isEmpty()) {
            apartments.removeIf(a -> !a.getApartmentType().name().equalsIgnoreCase(typeFilter));
        }

        // Filtro por ciudad
        if (locationSearch != null && !locationSearch.isEmpty()) {
            apartments.removeIf(a -> !a.getLocation().toLowerCase().contains(locationSearch.toLowerCase()));
        }

        // Ordenar por
        if (sortBy != null) {
            if (sortBy.equals("name")) {
            	ApartmentsNameComparator apartmentsNameComparator = new ApartmentsNameComparator(); 
                apartments.sort(apartmentsNameComparator);
            } else if (sortBy.equals("price")) {
            	ApartmentsPriceComparator apartmentsPriceComparator = new ApartmentsPriceComparator(); 
                apartments.sort(apartmentsPriceComparator);
            }
        }
    }
%>

<div class="container mx-auto my-4 p-4 bg-white rounded" style="max-width: 800px;">

    <!-- Filtro por tipo -->
    <div class="d-flex justify-content-center align-items-center flex-wrap gap-3 mb-3 text-dark">
        <a href="index.jsp" class="text-decoration-none text-dark px-3 py-2 rounded-pill">All</a>
        <a href="index.jsp?type=CABIN" class="text-decoration-none text-dark px-3 py-2 rounded-pill">
            <i class="fas fa-home me-1"></i> Cabin
        </a>
        <a href="index.jsp?type=TINYHOMES" class="text-decoration-none text-dark px-3 py-2 rounded-pill">
            <i class="fas fa-campground me-1"></i> Tiny Homes
        </a>
        <a href="index.jsp?type=APARTMENTS" class="text-decoration-none text-dark px-3 py-2 rounded-pill">
            <i class="fas fa-building me-1"></i> Apartments
        </a>
        <a href="index.jsp?type=COUNTRYSIDE" class="text-decoration-none text-dark px-3 py-2 rounded-pill">
            <i class="fas fa-tree me-1"></i> Countryside
        </a>
    </div>

    <!-- Ordenar -->
    <div class="d-flex justify-content-center flex-wrap gap-4 mb-4 text-muted">
        <span>Sort by:</span>
        <a href="index.jsp?<%= typeFilter != null ? "type=" + typeFilter + "&" : "" %>sort=name" class="text-decoration-none text-secondary">name</a>
        <a href="index.jsp?<%= typeFilter != null ? "type=" + typeFilter + "&" : "" %>sort=price" class="text-decoration-none text-secondary">price</a>
    </div>

    <!-- Buscar por ciudad -->
    <form method="get" action="index.jsp" class="mx-auto" style="max-width: 400px;">
        <div class="input-group">
            <input type="text" name="location" class="form-control" placeholder="Search by city..." value="<%= locationSearch != null ? locationSearch : "" %>">
            <% if (typeFilter != null) { %>
                <input type="hidden" name="type" value="<%= typeFilter %>">
            <% } %>
            <button class="btn" type="submit" style="background-color: #aab9a7; color: white; border-color: #aab9a7;">Buscar</button>
        </div>
    </form>

</div>

<% if (apartments != null && !apartments.isEmpty()) { %>
    <div class="grid">
        <% for (Apartments apt : apartments) { 
            String imageUrl = apt.getImages().isEmpty() ? "default.jpg" : apt.getImages().get(0).getUrl();
        %>
            <div class="card">
  			<a href="apartmentDetails.jsp?id=<%= apt.getId() %>" style="text-decoration: none; color: inherit;">
                <span class="label"><%= apt.getApartmentType().name() %></span>
                <img src="<%= imageUrl %>" alt="<%= apt.getName() %>">
                <div class="content">
                    <p class="title"><%= apt.getName() %></p>
                    <p class="location"><%= apt.getLocation() %></p>
                    <p class="details">
                        <%= apt.getCapacity() %> guests -
                        <%= apt.getBedrooms() %> bedrooms -
                        <%= apt.getBeds() %> beds -
                        <%= apt.getBaths() %> baths
                    </p>

                    <p class="price"><%= String.format("%.1f € night", apt.getPricePerNight()) %></p>
                </div>  
                 </a>
            </div>
        <% } %>
    </div>
<% } else { %>
    <p class="text-center text-danger">No apartments found.</p>
<% } %>

<jsp:include page="footer.jsp" />

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f5f5f5;
    }
    .grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
        gap: 20px;
        margin: 0 100px 100px 100px;
    }
    .card {
        background: #fff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }
    .card img {
        width: 100%;
        height: 180px;
        object-fit: cover;
    }
    .label {
        position: absolute;
        background: #d8f3dc;
        color: #2d6a4f;
        font-size: 12px;
        font-weight: bold;
        padding: 4px 8px;
        border-radius: 4px;
        margin: 10px;
    }
    .content {
        padding: 15px;
    }
    .title {
        font-size: 16px;
        font-weight: bold;
        margin: 0 0 5px 0;
    }
    .location {
        font-size: 14px;
        color: #666;
        margin-bottom: 10px;
    }
    .details {
        font-size: 13px;
        color: #444;
        margin-bottom: 10px;
    }
    .price {
        font-weight: bold;
        font-size: 14px;
    }
</style>

</body>
</html>
