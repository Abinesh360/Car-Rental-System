package backend;	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CarListServlet")
public class CarListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();
        List<Car> carList = carDAO.getAllCars();

        System.out.println("Total Cars Retrieved: " + (carList != null ? carList.size() : "NULL"));

        if (carList != null && !carList.isEmpty()) {
            request.getSession().setAttribute("carList", carList);
        } else {
            request.getSession().removeAttribute("carList");
        }

        response.sendRedirect("booking.jsp");
    }
}

