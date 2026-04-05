package backend;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final String ADMIN_USERNAME = "Admin";
    private static final String ADMIN_PASSWORD = "AB2806";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("adminUsername");
        String password = request.getParameter("adminPassword");

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ViewBookingsServlet");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Invalid username or password!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

