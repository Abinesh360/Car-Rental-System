<%@ page import="java.util.ArrayList, java.time.LocalDate" %>
<%@ page import="backend.Car" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book a Car</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        img {
            width: 100px;
            height: auto;
            border-radius: 5px;
        }
        button {
            background: #28a745;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background: #218838;
        }
    </style>
</head>
<body>


<div class="container">
    <h2>Available Cars for Booking</h2>

    <%
        ArrayList<Car> carList = (ArrayList<Car>) session.getAttribute("carList");
        if (carList == null || carList.isEmpty()) {
    %>
        <p>No cars available!</p>
    <%
        } else {
        	LocalDate currentDate = LocalDate.now();
    %>
        <table>
            <tr>
                <th>Car Image</th>
                <th>Car Name</th>
                <th>Seat Capacity</th>
                <th>Color</th>
                <th>Daily Rent</th>
                <th>Rental Date</th>
                <th>Return Date</th>
                <th>Action</th>
            </tr>
            <% for (Car car : carList) { %>
            <tr>
                <td><img src="<%= car.getImageUrl() %>" alt="Car Image"></td>
                <td><%= car.getName() %></td>
                <td><%= car.getSeatCapacity() %></td>
                <td><%= car.getColor() %></td>
                <td>₹<%= car.getDailyRent() %></td>
                
                    <form action="BookingServlet" method="post">
                <td >
                        <input type="hidden" name="carId" value="<%= car.getCarId() %>">
                        <input type="hidden" name="dailyRent" value="<%= car.getDailyRent() %>">
                        
                        <input type="date" name="rentalDate" min="<%=currentDate%>" required></td>
                     <td>   <input type="date" name="returnDate" min="<%=currentDate%>" required></td>                       
                	  <td>    <button type="submit">Book Now</button> </td>
                    </form>        
            <tr>
            <% } %>
        </table>
    <%
        }
    %>
</div>
<div>
<a href="UserViewBookingsServlet">Booking Dashborad </a>
</div>

</body>
</html>
