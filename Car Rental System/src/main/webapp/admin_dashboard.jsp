<%@ page import="java.util.ArrayList" %>
<%@ page import="backend.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            overflow: hidden;
        }

        th {
            background: #007BFF;
            color: white;
            padding: 12px;
            text-transform: uppercase;
        }

        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: 0.3s;
        }

        a:hover {
            background: #218838;
        }

        input[type="submit"] {
            padding: 5px 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        select {
            padding: 5px;
        }

        form {
            margin: 0;
        }
    </style>
</head>
<body>
    <h2>View Bookings</h2>

    <table border="1">
        <tr>
            <th>Booking ID</th>
            <th>Car ID</th>
            <th>User Name</th>
            <th>Rental Date</th>
            <th>Return Date</th>
            <th>Total Price</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <%
            ArrayList<Booking> bookingList = (ArrayList<Booking>) request.getAttribute("bookingList");
            if (bookingList != null && !bookingList.isEmpty()) {
                for (Booking booking : bookingList) {
        %>
        <tr>
            <form action="UpdateBookingStatusServlet" method="post">
                <td><%= booking.getBookingId() %></td>
                <td><%= booking.getCarId() %></td>
                <td><%= booking.getUser_name() %></td>
                <td><%= booking.getRentalDate() %></td>
                <td><%= booking.getReturnDate() %></td>
                <td>$<%= booking.getTotalPrice() %></td>
                <td>
                    <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                    <select name="status">
                        <option value="Pending" <%= "Pending".equals(booking.getStatus()) ? "selected" : "" %>>Pending</option>
                        <option value="Approved" <%= "Approved".equals(booking.getStatus()) ? "selected" : "" %>>Approved</option>
                        <option value="Rejected" <%= "Rejected".equals(booking.getStatus()) ? "selected" : "" %>>Rejected</option>
                    </select>
                </td>
                <td><input type="submit" value="Update"></td>
            </form>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="8">No bookings found.</td>
        </tr>
        <%
            }
        %>
    </table>

    <br>
    <a href="add_car.jsp">Go to Add Car Page</a>
</body>
</html>
