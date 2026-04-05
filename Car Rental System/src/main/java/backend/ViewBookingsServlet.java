package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewBookingsServlet")
public class ViewBookingsServlet extends HttpServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Forward GET requests to doPost logic
	        doPost(request, response);
	        System.out.println("GET method is working!");
	    }
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Car_Rental_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB2806";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Booking> bookingList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Query to fetch booking data
            String query = "SELECT  booking_id, b.username, b.id, b.car_id, c.name AS car_name, " +
                           "b.rental_date, b.return_date, b.total_days, b.total_price, b.status " +
                           "FROM booking b JOIN cars c ON b.car_id = c.car_id ORDER BY b.booking_id ASC";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            // Store retrieved data in booking list
            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getString("username"),
                    rs.getInt("booking_id"),
                    rs.getInt("id"),
                    rs.getInt("car_id"),
                  
                    rs.getDate("rental_date"),
                    rs.getDate("return_date"),
                    rs.getInt("total_days"),
                    rs.getDouble("total_price"),
                    rs.getString("status")
                );
                bookingList.add(booking);
            }
            

            // Store the list in request attribute
            request.setAttribute("bookingList", bookingList);

            // Forward request to JSP page
            request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);

        } catch (Exception e) {
        	response.getWriter().println(e.getMessage());
            
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }
}
