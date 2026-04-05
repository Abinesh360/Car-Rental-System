<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login - Car Rental System</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="login-container">
        <h2>Admin Login</h2>
        <form action="AdminLoginServlet" method="post">
            <label for="adminUsername">Username:</label>
            <input type="text" id="adminUsername" name="adminUsername" required>
            
            <label for="adminPassword">Password:</label>
            <input type="password" id="adminPassword" name="adminPassword" required>
            
            <button type="submit">Login</button>`-	
        </form>
        <%-- Display error message if login fails --%>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;">Invalid username or password. Please try again.</p>
        <% } %>
    </div>
</body>
</html>
