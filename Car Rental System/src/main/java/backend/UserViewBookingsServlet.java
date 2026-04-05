package backend;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserViewBookingsServlet")
public class UserViewBookingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            // If user not logged in, redirect to login page
            response.sendRedirect("Login.html");
            return;
        }

        try{
            int userId = (int) session.getAttribute("userId");

            // Call DAO to fetch booking data
            ArrayList<Booking> bookingList1= BookingDAO.getBookingsByUserId(userId);

            request.setAttribute("bookingList1", bookingList1);
             request.getRequestDispatcher("user_dashboard.jsp").forward(request, response);
           

          
   
        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
