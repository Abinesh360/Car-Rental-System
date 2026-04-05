package backend;
import java.sql.*;
import java.util.ArrayList;

/*public class CarDAO {
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Rental_System", "root", "AB2806");
            String query = "SELECT * FROM cars";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setName(rs.getString("name"));
                car.setSeatCapacity(rs.getInt("seat_capacity"));
                car.setColor(rs.getString("color"));
                car.setImageUrl(rs.getString("image_url"));
                car.setDailyRent(rs.getDouble("daily_rent"));
                carList.add(car);
            }
            System.out.println("Cars fetched successfully. Total: " + carList.size());
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching car details: " + e.getMessage());
        }
        return carList;
    }
}*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println(" Database connection failed.");
            return carList;
        }

        String sql = "SELECT * FROM cars";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(" Executing SQL Query: " + sql);

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

                // ✅ Debugging Output
                System.out.println("Car Fetched: ID=" + car.getCarId() +
                                   ", Name=" + car.getName() +
                                   ", Seats=" + car.getSeatCapacity() +
                                   ", Color=" + car.getColor());
            }

            System.out.println("Total Cars Fetched: " + carList.size());

        } catch (SQLException e) {
            System.out.println(" Error fetching car details: " + e.getMessage());
            e.printStackTrace();
        }

        return carList;
    }
}

