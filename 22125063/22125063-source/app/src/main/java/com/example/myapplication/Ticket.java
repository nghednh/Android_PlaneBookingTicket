package com.example.myapplication;

public class Ticket {

    private String fromCity;
    private String toCity;
    private String departureDate;
    private String departureTime;
    private String flightClass;
    private String ticketID;
    private String seat;

    public Ticket(String fromCity, String toCity, String departureDate, String departureTime,
                  String flightClass, String ticketID, String seat) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.flightClass = flightClass;
        this.ticketID = ticketID;
        this.seat = seat;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getSeat() {
        return seat;
    }

}
