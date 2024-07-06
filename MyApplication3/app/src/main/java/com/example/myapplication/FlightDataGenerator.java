package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class FlightDataGenerator {

    private static List<Flight> flights;

    static {
        flights = generateSampleFlights();
    }

    public static List<Flight> generateSampleFlights() {
        List<Flight> flights = new ArrayList<>();

        // Generate sample data
        flights.add(new Flight("New York", "Los Angeles", "10/7/2024", "15:00",
                "10/7/2024", "17:00", "$250.00", "NY-LA-1", "Economy"));
        flights.add(new Flight("San Francisco", "Chicago", "11/7/2024", "14:00",
                "11/7/2024", "16:00", "$300.00", "SF-CHI-1", "Business"));
        flights.add(new Flight("London", "Paris", "12/7/2024", "16:00",
                "12/7/2024", "18:00", "$150.00", "LDN-PAR-1", "Economy"));
        flights.add(new Flight("New York", "Chicago", "13/7/2024", "10:00",
                "13/7/2024", "12:00", "$200.00", "NY-CHI-1", "Economy"));
        flights.add(new Flight("Los Angeles", "San Francisco", "14/7/2024", "11:00",
                "14/7/2024", "13:00", "$180.00", "LA-SF-1", "Business"));
        flights.add(new Flight("Chicago", "New York", "15/7/2024", "12:00",
                "15/7/2024", "14:00", "$220.00", "CHI-NY-1", "Economy"));
        flights.add(new Flight("Houston", "Dallas", "16/7/2024", "13:00",
                "16/7/2024", "15:00", "$120.00", "HOU-DAL-1", "Business"));
        flights.add(new Flight("Phoenix", "Denver", "17/7/2024", "14:00",
                "17/7/2024", "16:00", "$150.00", "PHX-DEN-1", "Economy"));
        flights.add(new Flight("Philadelphia", "Washington", "18/7/2024", "15:00",
                "18/7/2024", "17:00", "$100.00", "PHI-WAS-1", "Business"));
        flights.add(new Flight("San Antonio", "Austin", "19/7/2024", "16:00",
                "19/7/2024", "18:00", "$80.00", "SA-AUS-1", "Economy"));
        flights.add(new Flight("San Diego", "Los Angeles", "20/7/2024", "17:00",
                "20/7/2024", "19:00", "$90.00", "SD-LA-1", "Business"));
        flights.add(new Flight("Dallas", "Houston", "21/7/2024", "18:00",
                "21/7/2024", "20:00", "$110.00", "DAL-HOU-1", "Economy"));
        flights.add(new Flight("San Jose", "San Francisco", "22/7/2024", "19:00",
                "22/7/2024", "21:00", "$140.00", "SJ-SF-1", "Business"));
        flights.add(new Flight("Austin", "Dallas", "23/7/2024", "20:00",
                "23/7/2024", "22:00", "$70.00", "AUS-DAL-1", "Economy"));
        flights.add(new Flight("Jacksonville", "Miami", "24/7/2024", "21:00",
                "24/7/2024", "23:00", "$180.00", "JAX-MIA-1", "Business"));
        flights.add(new Flight("San Francisco", "Los Angeles", "25/7/2024", "22:00",
                "25/7/2024", "00:00", "$120.00", "SF-LA-1", "Economy"));
        flights.add(new Flight("Indianapolis", "Chicago", "26/7/2024", "23:00",
                "26/7/2024", "01:00", "$100.00", "IND-CHI-1", "Business"));
        flights.add(new Flight("Columbus", "Cleveland", "27/7/2024", "09:00",
                "27/7/2024", "11:00", "$80.00", "COL-CLE-1", "Economy"));
        flights.add(new Flight("Fort Worth", "Dallas", "28/7/2024", "10:00",
                "28/7/2024", "12:00", "$60.00", "FTW-DAL-1", "Business"));
        flights.add(new Flight("Charlotte", "Atlanta", "29/7/2024", "11:00",
                "29/7/2024", "13:00", "$110.00", "CHA-ATL-1", "Economy"));
        flights.add(new Flight("Seattle", "Portland", "30/7/2024", "12:00",
                "30/7/2024", "14:00", "$90.00", "SEA-PDX-1", "Business"));
        flights.add(new Flight("Denver", "Phoenix", "31/7/2024", "13:00",
                "31/7/2024", "15:00", "$130.00", "DEN-PHX-1", "Economy"));
        flights.add(new Flight("El Paso", "Albuquerque", "1/8/2024", "14:00",
                "1/8/2024", "16:00", "$100.00", "ELP-ABQ-1", "Business"));
        flights.add(new Flight("Detroit", "Chicago", "2/8/2024", "15:00",
                "2/8/2024", "17:00", "$80.00", "DET-CHI-1", "Economy"));
        flights.add(new Flight("Washington", "New York", "3/8/2024", "16:00",
                "3/8/2024", "18:00", "$150.00", "WAS-NY-1", "Business"));
        flights.add(new Flight("Boston", "New York", "4/8/2024", "17:00",
                "4/8/2024", "19:00", "$120.00", "BOS-NY-1", "Economy"));
        flights.add(new Flight("Memphis", "Nashville", "5/8/2024", "18:00",
                "5/8/2024", "20:00", "$70.00", "MEM-NAS-1", "Business"));
        flights.add(new Flight("Portland", "Seattle", "6/8/2024", "19:00",
                "6/8/2024", "21:00", "$80.00", "PDX-SEA-1", "Economy"));
        flights.add(new Flight("Oklahoma City", "Dallas", "7/8/2024", "20:00",
                "7/8/2024", "22:00", "$90.00", "OKC-DAL-1", "Business"));
        flights.add(new Flight("Las Vegas", "Los Angeles", "8/8/2024", "21:00",
                "8/8/2024", "23:00", "$110.00", "LAS-LA-1", "Economy"));
        flights.add(new Flight("Baltimore", "Washington", "9/8/2024", "22:00",
                "9/8/2024", "00:00", "$60.00", "BAL-WAS-1", "Business"));
        flights.add(new Flight("Louisville", "Indianapolis", "10/8/2024", "23:00",
                "10/8/2024", "01:00", "$80.00", "LOU-IND-1", "Economy"));
        flights.add(new Flight("Milwaukee", "Chicago", "11/8/2024", "09:00",
                "11/8/2024", "11:00", "$100.00", "MIL-CHI-1", "Business"));
        flights.add(new Flight("Albuquerque", "Phoenix", "12/8/2024", "10:00",
                "12/8/2024", "12:00", "$120.00", "ABQ-PHX-1", "Economy"));
        flights.add(new Flight("Tucson", "Phoenix", "13/8/2024", "11:00",
                "13/8/2024", "13:00", "$140.00", "TUC-PHX-1", "Business"));
        flights.add(new Flight("Fresno", "Sacramento", "14/8/2024", "12:00",
                "14/8/2024", "14:00", "$150.00", "FRE-SAC-1", "Economy"));
        flights.add(new Flight("Sacramento", "San Francisco", "15/8/2024", "13:00",
                "15/8/2024", "15:00", "$170.00", "SAC-SF-1", "Business"));
        flights.add(new Flight("San Antonio", "Dallas", "16/8/2024", "14:00",
                "16/8/2024", "16:00", "$200.00", "SA-DAL-1", "Economy"));
        flights.add(new Flight("Denver", "Salt Lake City", "17/8/2024", "15:00",
                "17/8/2024", "17:00", "$180.00", "DEN-SLC-1", "Business"));
        flights.add(new Flight("Salt Lake City", "Las Vegas", "18/8/2024", "16:00",
                "18/8/2024", "18:00", "$160.00", "SLC-LAS-1", "Economy"));
        flights.add(new Flight("Seattle", "Vancouver", "19/8/2024", "17:00",
                "19/8/2024", "19:00", "$120.00", "SEA-VAN-1", "Business"));
        flights.add(new Flight("Toronto", "Montreal", "20/8/2024", "18:00",
                "20/8/2024", "20:00", "$100.00", "TOR-MTL-1", "Economy"));
        flights.add(new Flight("Miami", "Orlando", "21/8/2024", "19:00",
                "21/8/2024", "21:00", "$80.00", "MIA-ORL-1", "Business"));
        flights.add(new Flight("Atlanta", "Charlotte", "22/8/2024", "20:00",
                "22/8/2024", "22:00", "$90.00", "ATL-CHA-1", "Economy"));
        flights.add(new Flight("Boston", "Washington", "23/8/2024", "21:00",
                "23/8/2024", "23:00", "$110.00", "BOS-WAS-1", "Business"));
        flights.add(new Flight("Chicago", "Detroit", "24/8/2024", "22:00",
                "24/8/2024", "00:00", "$150.00", "CHI-DET-1", "Economy"));
        flights.add(new Flight("Dallas", "Austin", "25/8/2024", "23:00",
                "25/8/2024", "01:00", "$130.00", "DAL-AUS-1", "Business"));

        return flights;
    }

    public static List<Flight> getFlightsByCriteria(String fromCity, String toCity, String departureDate, String flightClass) {
        List<Flight> matchingFlights = new ArrayList<>();

        // Assuming departureDate in SearchBookingActivity is in format "10/7/2024"
        // Convert it to "2024-07-10" for comparison

        for (Flight flight : flights) {
            if (flight.getFromCity().equals(fromCity) &&
                    flight.getToCity().equals(toCity) &&
                    flight.getDepartureDate().equals(departureDate)
            && flight.getFlightClass().equals(flightClass)) {
                matchingFlights.add(flight);
            }
        }

        return matchingFlights;
    }


}
