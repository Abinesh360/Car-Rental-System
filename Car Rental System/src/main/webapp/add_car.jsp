<!DOCTYPE html>
<html>
<head>
    <title>Add New Car</title>
    <style>/* General styles */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
    text-align: center;
}

/* Form container */
form {
    background: white;
    width: 50%;
    margin: 0 auto;
    padding: 20px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
}

/* Form heading */
h2 {
    color: #333;
    margin-bottom: 20px;
}

/* Label styling */
label {
    display: block;
    font-weight: bold;
    text-align: left;
    margin: 10px 0 5px;
}

/* Input fields */
input {
    width: 100%;
    padding: 8px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

/* Button styling */
button {
    background: #007BFF;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: 0.3s;
}

button:hover {
    background: #0056b3;
}

/* Centering form elements */
form label, form input {
    text-align: left;
    display: block;
    width: 90%;
    margin: 0 auto;
}

/* Responsive Design */
@media (max-width: 768px) {
    form {
        width: 80%;
    }
}
    </style>
</head>
<body>
    <h2>Add New Car</h2>
    <form action="AddCarServlet" method="post">
        <label for="name">Car Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="seat_capacity">Seat Capacity:</label>
        <input type="number" id="seat_capacity" name="seat_capacity" required><br>

        <label for="color">Color:</label>
        <input type="text" id="color" name="color" required><br>

        <label for="image_url">Image URL:</label>
        <input type="text" id="image_url" name="image_url"><br>

        <label for="daily_rent">Daily Rent:</label>
        <input type="number" id="daily_rent" name="daily_rent" step="0.01" required><br>

        <button type="submit">Add Car</button>
    </form>
</body>
</html>
