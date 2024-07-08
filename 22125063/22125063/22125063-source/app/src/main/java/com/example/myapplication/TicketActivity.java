package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TicketActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnDownloadTicket;
    private String fromCity;
    private String toCity;
    private String flightID;
    private String departureDate;
    private int departureTime;
    private int numberOfPassengers;
    private String flightClass;
    private List<String> selectedSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Initialize views
        listView = findViewById(R.id.list_ticket);
        btnDownloadTicket = findViewById(R.id.btnDownloadTicket);

        // Fetch extras from Intent
        Intent intent = getIntent();
        fromCity = intent.getStringExtra("fromCity");
        toCity = intent.getStringExtra("toCity");
        flightID = intent.getStringExtra("flightNumber");
        departureDate = intent.getStringExtra("departureDate");
        departureTime = intent.getIntExtra("departureTime",-1);
        numberOfPassengers = intent.getIntExtra("numberOfPassengers", 1);
        flightClass = intent.getStringExtra("flightClass");
        selectedSeats = intent.getStringArrayListExtra("selectedSeats");

        // Back button
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });

        // Mock data for tickets
        List<Ticket> ticketList = generateMockTickets(numberOfPassengers);

        // Adapter for the ListView
        TicketAdapter adapter = new TicketAdapter(this, ticketList);
        listView.setAdapter(adapter);

        // Download ticket button
        btnDownloadTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TicketActivity.this, "Downloading ticket...", Toast.LENGTH_SHORT).show();
                // Add actual download functionality here
                saveTicketInformation();
            }
        });
        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<Ticket> generateMockTickets(int numberOfPassengers) {
        List<Ticket> tickets = new ArrayList<>();

        // For demo purposes, generating tickets for each passenger
        for (int i = 0; i < numberOfPassengers; i++) {
            tickets.add(new Ticket(fromCity, toCity, departureDate, String.valueOf(departureTime)+":00", flightClass, flightID+"*"+selectedSeats.get(i), selectedSeats.get(i)));
        }

        return tickets;
    }

    private void saveTicketInformation() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTickets", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Clear existing data
        editor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(selectedSeats);

        editor.putString("selectedSeats", json);
        editor.putString("fromCity", fromCity);
        editor.putString("toCity", toCity);
        editor.putString("flightID", flightID);
        editor.putString("flightClass", flightClass);
        editor.putString("departureDate", departureDate);
        editor.putInt("departureTime", departureTime);
        editor.putInt("numberOfPassengers", numberOfPassengers);
        editor.apply();
    }
}
