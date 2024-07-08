package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class SearchBookingActivity extends AppCompatActivity {

    private static final int REQUEST_FILTER = 1;

    private TextView textAvailableFlights;
    private ListView listFlights;
    private List<Flight> flightList;
    private FlightAdapter flightAdapter;

    private Button[] dateButtons = new Button[7];
    private Drawable defaultBackground;
    private Drawable selectedBackground;

    private String fromCity;
    private String toCity;
    private String departureDate;
    String selectedDate;
    private String flightClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_booking);

        // Check and create directory if not exists
        File directory = new File(getFilesDir(), "recents");
        if (!directory.exists()) {
            if (directory.mkdir()) {
                Log.d("SearchBookingActivity", "Directory created: " + directory.getPath());
            } else {
                Log.e("SearchBookingActivity", "Failed to create directory: " + directory.getPath());
            }
        }

        textAvailableFlights = findViewById(R.id.text_available_flights);
        listFlights = findViewById(R.id.list_flights);
        ImageButton buttonBack = findViewById(R.id.button_back);
        ImageButton buttonFilter = findViewById(R.id.button_filter);

        // Initialize date buttons
        dateButtons[0] = findViewById(R.id.button1);
        dateButtons[1] = findViewById(R.id.button2);
        dateButtons[2] = findViewById(R.id.button3);
        dateButtons[3] = findViewById(R.id.button4);
        dateButtons[4] = findViewById(R.id.button5);
        dateButtons[5] = findViewById(R.id.button6);
        dateButtons[6] = findViewById(R.id.button7);

        // Get default and selected backgrounds
        defaultBackground = ContextCompat.getDrawable(this, R.drawable.button_background);
        selectedBackground = ContextCompat.getDrawable(this, R.drawable.button_background_selected);

        // Set onClickListener for the back button
        buttonBack.setOnClickListener(v -> onBackPressed());

        // Set onClickListener for the filter button
        buttonFilter.setOnClickListener(v -> {
            Intent filterIntent = new Intent(SearchBookingActivity.this, FilterActivity.class);
            startActivityForResult(filterIntent, REQUEST_FILTER);
        });

        // Get intent data
        Intent intent = getIntent();
        fromCity = intent.getStringExtra("fromCity");
        toCity = intent.getStringExtra("toCity");
        departureDate = intent.getStringExtra("departureDate");
        flightClass = intent.getStringExtra("isEconomy");

        // Get flights that match all criteria
        selectedDate=departureDate;
        flightList = FlightDataGenerator.getFlightsByCriteria(fromCity, toCity, selectedDate, flightClass);
        textAvailableFlights.setText(flightList.size() + " flights available");

        flightAdapter = new FlightAdapter(this, flightList);
        listFlights.setAdapter(flightAdapter);

        // Get week dates
        String[] weekDates = DateUtils.getWeekDates(departureDate);

        // Set dates on buttons and initial selection
        setDateButtons(weekDates);

        // Set item click listener for the ListView
        listFlights.setOnItemClickListener((parent, view, position, id) -> {
            Flight selectedFlight = flightList.get(position);

            Intent detailIntent = new Intent(SearchBookingActivity.this, SelectSeatActivity.class);
            detailIntent.putExtra("fromCity", fromCity);
            detailIntent.putExtra("toCity", toCity);
            detailIntent.putExtra("flightNumber", selectedFlight.getFlightNumber());
            detailIntent.putExtra("departureDate", selectedFlight.getDepartureDate());
            detailIntent.putExtra("departureTime", selectedFlight.getDepartureTime());
            detailIntent.putExtra("numberOfPassengers", getIntent().getIntExtra("numberOfPassengers", 1));
            detailIntent.putExtra("flightClass", flightClass);
            detailIntent.putExtra("price", selectedFlight.getPrice());
            startActivity(detailIntent);
        });
    }

    private void setDateButtons(String[] weekDates) {
        for (int i = 0; i < dateButtons.length; i++) {
            dateButtons[i].setText(weekDates[i]);
            dateButtons[i].setTag(i);
            dateButtons[i].setOnClickListener(v -> selectButton((int) v.getTag()));
        }

        // Select the first button by default
        selectButton(0);
    }

    private void selectButton(int index) {
        for (int i = 0; i < dateButtons.length; i++) {
            if (i == index) {
                dateButtons[i].setBackground(selectedBackground);
                dateButtons[i].setTextColor(ContextCompat.getColor(this, R.color.white));
            } else {
                dateButtons[i].setBackground(defaultBackground);
                dateButtons[i].setTextColor(ContextCompat.getColor(this, R.color.black));
            }
        }
        String selectedDate;
        if (index != 0) {
            selectedDate = DateUtils.getNextDate(departureDate, index);

        } else {
            selectedDate = departureDate;
        }

        // Get updated flights based on the selected date
        List<Flight> updatedFlights = FlightDataGenerator.getFlightsByCriteria(fromCity, toCity, selectedDate, flightClass);

        // Update the text view with the number of available flights
        textAvailableFlights.setText(updatedFlights.size() + " flights available");

        // Update the adapter with new flights
        flightAdapter.updateFlights(updatedFlights);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FILTER && resultCode == RESULT_OK && data != null) {
            String sortCriteria = data.getStringExtra("sortCriteria");
            String departureTime = data.getStringExtra("departureTime");
            String arrivalTime = data.getStringExtra("arrivalTime");
            double minPrice = data.getIntExtra("minPrice", 0);
            double maxPrice = data.getIntExtra("maxPrice", Integer.MAX_VALUE);

            List<Flight> filteredFlights = FlightDataGenerator.getFlightsByCriteria(fromCity, toCity, selectedDate, flightClass);

            if (departureTime != null && !departureTime.isEmpty()) {
                filteredFlights = FlightDataGenerator.getFlightsByDepartureTime(fromCity, toCity, selectedDate, departureTime, filteredFlights);
            }

            if (arrivalTime != null && !arrivalTime.isEmpty()) {
                filteredFlights = FlightDataGenerator.getFlightsByArrivalTime(fromCity, toCity, selectedDate, arrivalTime, filteredFlights);
            }
            if (minPrice != 0 || maxPrice != Double.MAX_VALUE) {
                Log.d("SearchBookingActivity", "minPrice: " + minPrice + ", maxPrice: " + maxPrice);
                filteredFlights = FlightDataGenerator.getFlightsByPrice(fromCity, toCity, selectedDate, minPrice, maxPrice, filteredFlights);
            }

            // Sort the flights based on the selected criteria
            if ("Arrival time".equals(sortCriteria)) {
                filteredFlights.sort(Comparator.comparing(Flight::getArrivalTime));
            } else if ("Departure time".equals(sortCriteria)) {
                filteredFlights.sort(Comparator.comparing(Flight::getDepartureTime));
            } else if ("Price".equals(sortCriteria)) {
                filteredFlights.sort(Comparator.comparing(Flight::getPrice));
            }

            // Update the ListView or RecyclerView with the filteredFlights
            textAvailableFlights.setText(filteredFlights.size() + " flights available");
            flightAdapter.updateFlights(filteredFlights);
        }
    }
}
