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

@WebServlet("/UpdateBookingStatusServlet")
public class UpdateBookingStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
    	int bookingId = Integer.parseInt(request.getParameter("bookingId"));
    	pw.print(bookingId);
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental_system", "root", "AB2806");

            String sql = "UPDATE booking SET status = ? WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            ps.executeUpdate();

            conn.close();
            response.sendRedirect("ViewBookingsServlet");// or the page that reloads bookings
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
