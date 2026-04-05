package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Car_Rental_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB2806";

    
    
    
    
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null || session.getAttribute("username") == null) {
            request.setAttribute("error", "Session expired. Please login again.");
            request.getRequestDispatcher("login.html").forward(request, response);
            return;
        }
        int userId = ((Integer) session.getAttribute("userId")).intValue();
        String username = (String) session.getAttribute("username");

        int carId = Integer.parseInt(request.getParameter("carId"));
        double dailyRent = Double.parseDouble(request.getParameter("dailyRent"));
        String rentalDateStr = request.getParameter("rentalDate");
        String returnDateStr = request.getParameter("returnDate");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date rentalDate = sdf.parse(rentalDateStr);
            Date returnDate = sdf.parse(returnDateStr);

            long diff = returnDate.getTime() - rentalDate.getTime();
            int totalDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            double totalPrice = totalDays * dailyRent;

            if (totalDays <= 0) {
                session.setAttribute("error", "Return date must be after rental date.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO booking (username, id, car_id, rental_date, return_date, total_days, total_price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setInt(2, userId);
            ps.setInt(3, carId);
            ps.setString(4, rentalDateStr);
            ps.setString(5, returnDateStr);
            ps.setInt(6, totalDays);
            ps.setDouble(7, totalPrice);
            ps.setString(8, "Pending");
            ps.executeUpdate();

            session.setAttribute("message", "Booking successful!");
            response.sendRedirect("UserViewBookingsServlet");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Booking failed.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
            dispatcher.forward(request, response);
        }
    }
}
