package backend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Rental_System", "root", "AB2806");

            // Check if email exists in the database
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // Update the user's password
                PreparedStatement updatePs = conn.prepareStatement("UPDATE users SET password = ? WHERE email = ?");
                updatePs.setString(1, newPassword);
                updatePs.setString(2, email);
                updatePs.executeUpdate();
                
                out.println("<h3>Password Reset Successful!</h3>");
                out.println("<p>You can now <a href='login.html'>login</a> with your new password.</p>");
            } else {
                out.println("<h3>Email not found!</h3>");
                out.println("<p>Please check your email and try again.</p>");
                out.println("<a href='forgot_password.html'>Try Again</a>");
            }
            
            conn.close();
        } catch (Exception e) {
            out.println("<h3>Error occurred: " + e.getMessage() + "</h3>");
        }
    }
}
