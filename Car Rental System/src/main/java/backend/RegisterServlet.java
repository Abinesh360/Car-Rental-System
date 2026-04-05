package backend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Car_Rental_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB2806";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
    	// Retrieve user input from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Validate input
        if (username == null || password == null || email == null || phone == null ||
            username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            response.sendRedirect("register.html?error=All fields are required");
            
            return;
        }

        // Register the user
        if (registerUser(username, password, email, phone)) {
            response.sendRedirect("login.html?message=Registration successful. Please login.");
        } else {
            response.sendRedirect("register.html?error=Registration failed. Please try again.");
        }
    }

    private boolean registerUser(String username, String password, String email, String phone) {
        boolean isRegistered = false;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Prepare SQL query to insert user data
                String sql = "INSERT INTO users (username, password, email, phone) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password); // In a real application, hash the password before storing
                    statement.setString(3, email);
                    statement.setString(4, phone);
                    int rowsInserted = statement.executeUpdate();
                    isRegistered = rowsInserted > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRegistered;
    }
}
