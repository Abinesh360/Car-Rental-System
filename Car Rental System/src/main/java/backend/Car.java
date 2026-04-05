package backend;

public class Car {
 
	private int carId;
	private String name;
    private int seatCapacity;
    private String color;
    private String imageUrl;
    private double dailyRent;
    public Car(int carId, String name, int seatCapacity, String color, String imageUrl, double dailyRent) {
 		super();
 		this.carId = carId;
 		this.name = name;
 		this.seatCapacity = seatCapacity;
 		this.color = color;
 		this.imageUrl = imageUrl;
 		this.dailyRent = dailyRent;
 	}
    public int getCarId() {
 		return carId;
 	}
 	public void setCarId(int carId) {
 		this.carId = carId;
 	}
 	public String getName() {
 		return name;
 	}
 	public void setName(String name) {
 		this.name = name;
 	}
 	public int getSeatCapacity() {
 		return seatCapacity;
 	}
 	public void setSeatCapacity(int seatCapacity) {
 		this.seatCapacity = seatCapacity;
 	}
 	public String getColor() {
 		return color;
 	}
 	public void setColor(String color) {
 		this.color = color;
 	}
 	public String getImageUrl() {
 		return imageUrl;
 	}
 	public void setImageUrl(String imageUrl) {
 		this.imageUrl = imageUrl;
 	}
 	public double getDailyRent() {
 		return dailyRent;
 	}
 	public void setDailyRent(double dailyRent) {
 		this.dailyRent = dailyRent;
 	}

   
}
