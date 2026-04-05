package backend;

import java.sql.Date;

public class Booking {
	private String user_name;
    private int bookingId;
    private int userId;
    private int carId;
    private String carName;
    private Date rentalDate;
    private Date returnDate;
    private int totalDays;
    private double totalPrice;
    private String status;

    // Constructor
    public Booking(String user_name,int bookingId, int userId, int carId, Date string, Date string2, int totalDays, double totalPrice, String status) {
        this.user_name= user_name;
    	this.bookingId = bookingId;
        this.userId = userId;
        this.carId = carId;
    
        this.rentalDate = string;
        this.returnDate = string2;
        this.totalDays = totalDays;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	// Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date date) {
        this.rentalDate = date;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date date) {
        this.returnDate = date;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
