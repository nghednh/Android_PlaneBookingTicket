package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectSeatActivity extends AppCompatActivity {

    private int currentTraveler = 1;
    private ListView seatListView;
    private int numberOfPassengers;
    private int pricePerPassenger;
    private Set<String> bookedSeats = new HashSet<>(Arrays.asList("A1", "B2", "C3","D1","B5","C5","A6","D6")); // Example booked seats
    private List<String> selectedSeats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        Intent intent = getIntent();
        numberOfPassengers = intent.getIntExtra("numberOfPassengers", 1);
        pricePerPassenger = intent.getIntExtra("price", 50);
        updateRadioButton(1);
        setupBookedSeats();
        hideNonExistTravelers();
        setupSeatSelection();
        updatePrice();
        ImageButton backButton = findViewById(R.id.backButton);
        Button continueButton = findViewById(R.id.continueButton);
        backButton.setOnClickListener(v -> {
            // Handle back button click
            finish();
        });

        continueButton.setOnClickListener(v -> {
            // Handle continue button click
            Intent receivedIntent = getIntent();

            Intent ticketIntent = new Intent(SelectSeatActivity.this, TicketActivity.class);
            ticketIntent.putExtras(receivedIntent.getExtras());
            // Add any extra data you need to pass to the TicketActivity
            ticketIntent.putExtra("selectedSeats", new ArrayList<>(selectedSeats));
            startActivity(ticketIntent);
        });
    }

    private void setupBookedSeats() {
        // Log the start of the method
        Log.d("SelectSeatActivity", "setupBookedSeats: Started setup");

        // Iterate over each booked seat
        for (String seat : bookedSeats) {
            // Log the current seat being processed
            Log.d("SelectSeatActivity", "setupBookedSeats: Processing seat " + seat);

            // Get the resource ID for the seat
            int resId = getResources().getIdentifier("seat" + seat, "id", getPackageName());
            Log.d("SelectSeatActivity", "setupBookedSeats: Resource ID for seat " + seat + " is " + resId);

            // Find the seat layout by resource ID
            FrameLayout seatLayout = findViewById(resId);
            if (seatLayout != null) {
                // Log that the seat layout was found
                Log.d("SelectSeatActivity", "setupBookedSeats: Seat layout found for seat " + seat);

                // Attempt to parse the color for booked seats
                try {
                    int bookedColor = Color.parseColor("#00796B"); // Replace this with your booked seat color
                    seatLayout.setBackgroundColor(bookedColor);
                    Log.d("SelectSeatActivity", "setupBookedSeats: Set booked color for seat " + seat);
                } catch (IllegalArgumentException e) {
                    Log.e("SelectSeatActivity", "setupBookedSeats: Error parsing color for seat " + seat, e);
                }

                // Set the seat as non-clickable
                seatLayout.setClickable(false);
                Log.d("SelectSeatActivity", "setupBookedSeats: Seat " + seat + " set as non-clickable");
            } else {
                // Log a warning if the seat layout was not found
                Log.w("SelectSeatActivity", "setupBookedSeats: Seat layout not found for seat " + seat);
            }
        }

        // Log the end of the method
        Log.d("SelectSeatActivity", "setupBookedSeats: Completed setup");
    }



    private void hideNonExistTravelers() {
        for (int i = numberOfPassengers + 1; i <= 6; i++) {
            int resId = getResources().getIdentifier("traveller" + i, "id", getPackageName());
            RadioButton radioButton = findViewById(resId);
            if (radioButton != null) {
                radioButton.setVisibility(View.GONE);
            }
        }
    }

    private void setupSeatSelection() {
        // Log the start of the method
        Log.d("SelectSeatActivity", "setupSeatSelection: Started setup");

        // Find the GridLayout for the seats
        GridLayout seatsGrid = findViewById(R.id.seats_grid);
        if (seatsGrid == null) {
            Log.e("SelectSeatActivity", "setupSeatSelection: seatsGrid is null");
            return;
        }

        // Get the number of child views in the GridLayout
        int childCount = seatsGrid.getChildCount();
        Log.d("SelectSeatActivity", "setupSeatSelection: seatsGrid has " + childCount + " child views");

        // Iterate over each child view (seat) in the GridLayout
        for (int i = 0; i < childCount; i++) {
            View seat = seatsGrid.getChildAt(i);
            if (seat == null) {
                Log.w("SelectSeatActivity", "setupSeatSelection: seat at index " + i + " is null");
                continue;
            }

            // Set an OnClickListener for the seat
            seat.setOnClickListener(v -> {
                // Log that the seat was clicked
                Log.d("SelectSeatActivity", "setupSeatSelection: Seat clicked with ID " + v.getId());

                // Get the seat ID from the view's resource entry name
                String seatId = getResources().getResourceEntryName(v.getId()).substring(4);
                Log.d("SelectSeatActivity", "setupSeatSelection: Seat ID is " + seatId);

                // Check if the seat is not already booked
                int num=0;
                if (!bookedSeats.contains(seatId)) {
                    // Log the seat selection process
                    Log.d("SelectSeatActivity", "setupSeatSelection: Selecting seat " + seatId);

                    // Attempt to parse the color for selected seats
                    try {
                        int selectedColor = Color.parseColor("#00FF00"); // Replace this with your selected seat color
                        v.setBackgroundColor(selectedColor);
                        Log.d("SelectSeatActivity", "setupSeatSelection: Set selected color for seat " + seatId);
                    } catch (IllegalArgumentException e) {
                        Log.e("SelectSeatActivity", "setupSeatSelection: Error parsing color for seat " + seatId, e);
                    }

                    // Set the seat as non-clickable
                    v.setClickable(false);
                    Log.d("SelectSeatActivity", "setupSeatSelection: Seat " + seatId + " set as non-clickable");

                    // Add the seat to the bookedSeats and selectedSeats lists
                    bookedSeats.add(seatId);
                    selectedSeats.add(seatId);
                    num++;
                    Log.d("SelectSeatActivity", "setupSeatSelection: Added seat " + seatId + " to bookedSeats and selectedSeats");

                    // Update seat details for the current traveler
                    updateSeatDetails(currentTraveler, seatId);
                    Log.d("SelectSeatActivity", "setupSeatSelection: Updated seat details for traveler " + currentTraveler);

                    // Increment the current traveler index
                    currentTraveler++;
                    Log.d("SelectSeatActivity", "setupSeatSelection: Incremented currentTraveler to " + currentTraveler);

                    // Update the radio button if there are more passengers to assign seats
                    if (currentTraveler <= numberOfPassengers) {
                        updateRadioButton(currentTraveler);
                        Log.d("SelectSeatActivity", "setupSeatSelection: Updated radio button for traveler " + currentTraveler);
                    } else {
                        for (int ic = 0; ic < childCount; ic++) {
                            View v1 = seatsGrid.getChildAt(ic);
                            v1.setClickable(false);
                            }
                    }
                } else {
                    Log.d("SelectSeatActivity", "setupSeatSelection: Seat " + seatId + " is already booked");
                }
            });
        }

        // Log the end of the method
        Log.d("SelectSeatActivity", "setupSeatSelection: Completed setup");
    }


    private void updateSeatDetails(int traveler, String seatId) {
        TextView seatDetails = findViewById(R.id.seatDetails);
        String previousText = seatDetails.getText().toString();
        String newText = "Traveler " + traveler + " / Seat " + seatId + "\n";
        seatDetails.setText(previousText + newText);
    }

    private void updateRadioButton(int traveler) {
        int resId = getResources().getIdentifier("traveller" + traveler, "id", getPackageName());
        RadioButton radioButton = findViewById(resId);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    private void updatePrice() {
        TextView priceAmount = findViewById(R.id.priceAmount);
        int totalPrice = numberOfPassengers * pricePerPassenger;
        priceAmount.setText("$" + totalPrice);
    }
}


//
//        // Set text to TextViews
//        TextView fromCityTextView = findViewById(R.id.text_from_city);
//        fromCityTextView.setText(fromCity);
//
//        TextView toCityTextView = findViewById(R.id.text_to_city);
//        toCityTextView.setText(toCity);
//
//        TextView flightIDTextView = findViewById(R.id.text_flight_id);
//        flightIDTextView.setText(flightID);
//
//        TextView departureDateTextView = findViewById(R.id.text_departure_date);
//        departureDateTextView.setText(departureDate);
//
//        TextView departureTimeTextView = findViewById(R.id.text_departure_time);
//        departureTimeTextView.setText(departureTime);
//
//        TextView numberOfPassengersTextView = findViewById(R.id.text_number_of_passengers);
//        numberOfPassengersTextView.setText(String.valueOf(numberOfPassengers));
//
//        TextView flightClassTextView = findViewById(R.id.text_flight_class);
//        flightClassTextView.setText(flightClass);
