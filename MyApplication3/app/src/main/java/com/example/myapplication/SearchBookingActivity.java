package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SearchBookingActivity extends AppCompatActivity {

    private TextView textAvailableFlights;
    private ListView listFlights;
    private List<Flight> flightList;
    private FlightAdapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_booking);

        textAvailableFlights = findViewById(R.id.text_available_flights);
        listFlights = findViewById(R.id.list_flights);
        ImageButton buttonBack = findViewById(R.id.button_back); // Find the back button

        // Set onClickListener for the back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String fromCity = intent.getStringExtra("fromCity");
        String toCity = intent.getStringExtra("toCity");
        String departureDate = intent.getStringExtra("departureDate");

        generateRandomFlights(fromCity, toCity, departureDate);

        textAvailableFlights.setText(flightList.size() + " flights available");

        flightAdapter = new FlightAdapter(this, flightList);
        listFlights.setAdapter(flightAdapter);
    }
    private void generateRandomFlights(String fromCity, String toCity, String departureDate) {
        flightList = new ArrayList<>();
        Random random = new Random();
        int numberOfFlights = 5;  // Generate between 1 and 5 flights

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, 6);  // Start from 6 AM
        calendar.set(Calendar.MINUTE,0);

        for (int i = 0; i < numberOfFlights; i++) {
            Flight flight = new Flight();
            flight.setFromCity(fromCity);
            flight.setToCity(toCity);
            flight.setDepartureDate(departureDate);
            flight.setDepartureTime(timeFormat.format(calendar.getTime()));
            flight.setPrice("$50");
            flight.setFlightNumber(fromCity.charAt(0) + "-" + toCity.charAt(0) + "-" + (i + 1));

            flightList.add(flight);

            calendar.add(Calendar.HOUR_OF_DAY, 2);  // Add 1 to 3 hours for the next flight
        }
    }
}

