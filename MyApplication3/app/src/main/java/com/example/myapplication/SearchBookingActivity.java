package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

public class SearchBookingActivity extends AppCompatActivity {

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
    private String flightClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_booking);

        textAvailableFlights = findViewById(R.id.text_available_flights);
        listFlights = findViewById(R.id.list_flights);
        ImageButton buttonBack = findViewById(R.id.button_back); // Find the back button

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
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        fromCity = intent.getStringExtra("fromCity");
        toCity = intent.getStringExtra("toCity");
        departureDate = intent.getStringExtra("departureDate");
        flightClass = intent.getStringExtra("isEconomy"); // Retrieve flight class
        // Get flights that match all criteria
        flightList = FlightDataGenerator.getFlightsByCriteria(fromCity, toCity, departureDate, flightClass);

        textAvailableFlights.setText(flightList.size() + " flights available");

        flightAdapter = new FlightAdapter(this, flightList);
        listFlights.setAdapter(flightAdapter);

        // Get week dates
        String[] weekDates = DateUtils.getWeekDates(departureDate);

        // Set dates on buttons and initial selection
        setDateButtons(weekDates);
    }

    private void setDateButtons(String[] weekDates) {
        for (int i = 0; i < dateButtons.length; i++) {
            dateButtons[i].setText(weekDates[i]);
            dateButtons[i].setTag(i);
            dateButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectButton((int) v.getTag());

                }
            });
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
        String selectedDate = getNextDate(departureDate, index);

        // Update flights based on selected date
        flightList = FlightDataGenerator.getFlightsByCriteria(fromCity, toCity, selectedDate, flightClass);
        textAvailableFlights.setText(flightList.size() + " flights available");

        // Update adapter with new flight list
        flightAdapter.updateFlights(flightList);
    }
}
