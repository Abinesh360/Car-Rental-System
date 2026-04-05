package backend;

import java.io.IOException;
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

@WebServlet("/FetchCarsServlet")
public class FetchCarsServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Car_Rental_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB2806";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Car> carList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Fetch all car details from the database
            String query = "SELECT car_id, name, seat_capacity, color, image_url, daily_rent FROM cars";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Car car = new Car(
                    rs.getInt("car_id"),
                    rs.getString("name"),
                    rs.getInt("seat_capacity"),
                    rs.getString("color"),
                    rs.getString("image_url"),
                    rs.getDouble("daily_rent")
                );
                carList.add(car);
            }

            // Store the car list in request attribute
            request.setAttribute("carList", carList);
            request.getRequestDispatcher("remove_car.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }
}
