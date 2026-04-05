package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Car_Rental_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB2806";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ✅ Fetch user ID
        int userId = getUserId(username, password);

        if (userId > 0) { // ✅ If user exists, store details in session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userId", userId); //
            request.getRequestDispatcher("CarListServlet").forward(request, response);
        } else {
            response.sendRedirect("login.html?error=Invalid username or password");
        }
    }

    // ✅ Fetch user ID from database
    private int getUserId(String username, String password) {
        int userId = -1; // Default: not found
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            userId = resultSet.getInt("id"); // ✅ Fetch user ID
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId; // ✅ Return user ID (-1 if not found)
    }
}
