<%@page import="jakarta.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="backend.Car" %>
<!DOCTYPE html>
<html>
<head>
    <title>Remove Car</title>
    <link rel="stylesheet" type="text/css" href="remove_car.css">
    <style>
    /* General styles */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
    text-align: center;
}

/* Form container */
form {
    background: white;
    width: 50%;
    margin: 0 auto;
    padding: 20px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
}

/* Form heading */
h2 {
    color: #333;
    margin-bottom: 20px;
}

/* Label styling */
label {
    display: block;
    font-weight: bold;
    text-align: left;
    margin: 10px 0 5px;
}

/* Select dropdown */
select {
    width: 100%;
    padding: 8px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

/* Button styling */
button {
    background: #dc3545;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: 0.3s;
}

button:hover {
    background: #b52b3a;
}

/* Back link styling */
a {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 15px;
    background: #007BFF;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    transition: 0.3s;
}

a:hover {
    background: #0056b3;
}

/* Responsive Design */
@media (max-width: 768px) {
    form {
        width: 80%;
    }
}
    </style>
</head>
<body>

    <h2>Remove Car</h2>

    <%
        // Change #1: Instead of forwarding to FetchCarsServlet using request.getRequestDispatcher,
        // we now check if carList is null, and if so, redirect to FetchCarsServlet.
        ArrayList<Car> carList = (ArrayList<Car>) request.getAttribute("carList");
        if (carList == null) {
            response.sendRedirect("FetchCarsServlet"); // Redirects to fetch cars if no data is available
            return; // Stops further execution
        }
    %>

    <form action="RemoveCarServlet" method="post">
        <label for="car_id">Select Car:</label>
        <select id="car_id" name="car_id" required>
            <option value="">-- Select a Car --</option>
            <%
                // Change #2: Instead of attempting to iterate over carList directly,
                // we first check if it contains any data to avoid null pointer errors.
                if (!carList.isEmpty()) {
                    for (Car car : carList) {
            %>
                <option value="<%= car.getCarId() %>">
                    <%= car.getName() %> - <%= car.getColor() %> - Seats: <%= car.getSeatCapacity() %> - ₹<%= car.getDailyRent() %>/day
                </option>
            <%
                    }
                } else {
            %>
                <option value="">No cars available</option>
            <%
                }
            %>
        </select>
        <br><br>
        <button type="submit">Remove Car</button>
    </form>

    <br>
    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>


