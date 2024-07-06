package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Intent intent = getIntent();
        String fromCity = intent.getStringExtra("fromCity");
        String toCity = intent.getStringExtra("toCity");
        String flightID = intent.getStringExtra("flightNumber");
        String departureDate = intent.getStringExtra("departureDate");
        String departureTime = intent.getStringExtra("departureTime");
        int numberOfPassengers = intent.getIntExtra("numberOfPassengers", 1);
        String flightClass = intent.getStringExtra("flightClass");

        ((TextView) findViewById(R.id.text_from_city)).setText(fromCity);
        ((TextView) findViewById(R.id.text_to_city)).setText(toCity);
        ((TextView) findViewById(R.id.text_flight_id)).setText(flightID);
        ((TextView) findViewById(R.id.text_departure_date)).setText(departureDate);
        ((TextView) findViewById(R.id.text_departure_time)).setText(departureTime);
        ((TextView) findViewById(R.id.text_number_of_passengers)).setText(String.valueOf(numberOfPassengers));
        ((TextView) findViewById(R.id.text_flight_class)).setText(flightClass);
    }
}
