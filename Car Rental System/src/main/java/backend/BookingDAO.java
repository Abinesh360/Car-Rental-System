package backend;

import java.sql.*;
import java.util.ArrayList;
//DAO  Data Access Object.
public class BookingDAO {
    
  

    public static ArrayList<Booking> getBookingsByUserId(int userId) {
        ArrayList<Booking> bookings = new ArrayList<>();

        try {
         

            // Create connection
            Connection conn = DBConnection.getConnection();

            // Prepare SQL
            String sql = "SELECT * FROM booking WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            // Execute query
            ResultSet rs = stmt.executeQuery();

            // Process result
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
                booking.setUser_name(rs.getString("username"));
                booking.setUserId(rs.getInt("id"));
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCarId(rs.getInt("car_id"));
                booking.setRentalDate(rs.getDate("rental_date"));
                booking.setReturnDate(rs.getDate("return_date"));
                booking.setTotalDays(rs.getInt("total_days"));
                booking.setTotalPrice(rs.getDouble("total_price"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
               
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }
}
