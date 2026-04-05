package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoveCarServlet")
public class RemoveCarServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Car_Rental_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB2806";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter("car_id");

        if (carId == null || carId.isEmpty()) {
            response.getWriter().println("Invalid car ID.");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Delete car from the database
            String query = "DELETE FROM cars WHERE car_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(carId));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("remove_car.jsp?success=Car removed successfully");
            } else {
                response.getWriter().println("Failed to remove car. Car not found.");
            }

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }
}
