<%@ page import="java.util.ArrayList" %>
<%@ page import="backend.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Bookings</title>
    <style>
    * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    padding: 20px;
    color: #333;
}

h2 {
    text-align: center;
    color: #4CAF50;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    background-color: #fff;
}

th {
    background-color: #4CAF50;
    color: white;
    padding: 12px;
    text-align: left;
    font-weight: bold;
}

td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

tr:hover {
    background-color: #f1f1f1;
}

@media screen and (max-width: 768px) {
    table {
        font-size: 14px;
    }

    th, td {
        padding: 8px;
    }

    h2 {
        font-size: 18px;
    }
}
    </style>
</head>
<body>
    <h2>Your Bookings</h2>
    <table border="1">
        <tr>
            <th>Booking ID</th>
            <th>Car id</th>
            <th>Rental Date</th>
            <th>Return Date</th>
            <th>Total Days</th>
            <th>Total Price</th>
            <th>Status</th>
        </tr>
        <%
            ArrayList<Booking> bookingList = (ArrayList<Booking>) request.getAttribute("bookingList1");
      
        if(bookingList !=null){
                for (Booking booking : bookingList) {
        %>
        <tr>
            <td><%= booking.getBookingId() %></td>
            <td><%= booking.getCarId() %></td>
            <td><%= booking.getRentalDate() %></td>
            <td><%= booking.getReturnDate() %></td>
            <td><%= booking.getTotalDays() %></td>
            <td>$<%= booking.getTotalPrice() %></td>
            <td><%= booking.getStatus() %></td>
        </tr>
        <%
                }
            }else {
        %>     
      <tr><td colspan="7">No bookings found.</td></tr>
        <%
    }
    
%>
    </table>
</body>
</html>
