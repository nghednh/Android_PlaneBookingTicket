package com.example.myapplication;

import android.util.Log;

public class Flight {
    private String fromCity;
    private String toCity;
    private String departureDate;
    private int departureTime;

    private String arrivalDate;
    private int arrivalTime;
    private int price;
    private String flightNumber;
    private String flightClass;

    public Flight(String fromCity, String toCity, String departureDate, String departureTime,
                  String arrivalDate, String arrivalTime, String price, String flightNumber, String flightClass) {
        Log.d("2ans","asi");
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.departureDate = departureDate;
        this.departureTime = convertStringToInt(departureTime);
        Log.d("2ans","asi");
        this.arrivalDate = arrivalDate;
        this.arrivalTime = convertStringToInt(arrivalTime);
        this.price = convertDollarStringToInt(price);
        this.flightNumber = flightNumber;
        this.flightClass = flightClass;
    }
    public static int convertStringToInt(String timeString) {
        // Split the time string by ':' to separate hours and minutes
        String[] parts = timeString.split(":");

        // Extract the hour part and convert it to an integer
        int hour = Integer.parseInt(parts[0]);
        Log.d("fsd", "ds" + hour);
        return hour;
    }
    public static int convertDollarStringToInt(String dollarString) {
        // Remove the dollar sign and any leading or trailing whitespace
        String cleanedStr = dollarString.replace("$", "").trim();

        // Convert the cleaned string to a double first
        double doubleValue = Double.parseDouble(cleanedStr);

        // Cast the double to an integer
        int intValue = (int) doubleValue;

        return intValue;
    }

    // Getters and Setters
    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
}