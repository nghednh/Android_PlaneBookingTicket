package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class TransportActivity extends AppCompatActivity {

    private AutoCompleteTextView editTextFrom, editTextTo;
    private EditText editTextDeparture, editTextReturn;
    private EditText editTextPassenger, editTextChildren, editTextPet, editTextLuggage;
    private RadioGroup radioGroupClass, radioGroupTransport;
    private RadioButton radioButtonEconomy, radioButtonBusiness, radioButtonPlane, radioButtonShip, radioButtonTrain, radioButtonBus;
    private ImageButton buttonBack, buttonSwap;
    private Set<String> citiesSet;

    private Calendar departureCalendar = Calendar.getInstance();
    private Calendar returnCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        // Initialize UI elements
        editTextFrom = findViewById(R.id.editText_from);
        editTextTo = findViewById(R.id.editText_to);
        editTextDeparture = findViewById(R.id.editText_departure);
        editTextReturn = findViewById(R.id.editText_return);
        editTextPassenger = findViewById(R.id.editText_passenger);
        editTextChildren = findViewById(R.id.editText_children);
        editTextPet = findViewById(R.id.editText_pet);
        editTextLuggage = findViewById(R.id.editText_luggage);
        radioGroupClass = findViewById(R.id.radioGroup_class);
        radioGroupTransport = findViewById(R.id.radioGroup_transport);
        radioButtonEconomy = findViewById(R.id.radioButton_economy);
        radioButtonBusiness = findViewById(R.id.radioButton_business);
        radioButtonPlane = findViewById(R.id.radioButton_plane);
        radioButtonShip = findViewById(R.id.radioButton_ship);
        radioButtonTrain = findViewById(R.id.radioButton_train);
        radioButtonBus = findViewById(R.id.radioButton_bus);
        buttonBack = findViewById(R.id.button_back);
        buttonSwap = findViewById(R.id.button_swap);

        // Set up ArrayAdapter for AutoCompleteTextView
        String[] cities = getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        editTextFrom.setAdapter(adapter);
        editTextTo.setAdapter(adapter);

        // Convert cities array to a set for validation
        citiesSet = new HashSet<>(Arrays.asList(cities));

        // Set listeners
        buttonBack.setOnClickListener(view -> onBackPressed());

        buttonSwap.setOnClickListener(view -> {
            String fromCity = editTextFrom.getText().toString();
            String toCity = editTextTo.getText().toString();
            editTextFrom.setText(toCity);
            editTextTo.setText(fromCity);
        });

        editTextDeparture.setOnClickListener(view -> showDatePickerDialog(editTextDeparture, true));
        editTextReturn.setOnClickListener(view -> showDatePickerDialog(editTextReturn, false));

        // Ensure number inputs only
        editTextPassenger.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        editTextChildren.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        editTextPet.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        editTextLuggage.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        // Handle transport mode selection
        radioGroupTransport.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != R.id.radioButton_plane) {
                Toast.makeText(TransportActivity.this, "This vehicle is not available", Toast.LENGTH_SHORT).show();
                radioButtonPlane.setChecked(true);
            }
        });

        findViewById(R.id.button_search).setOnClickListener(view -> {
            // Retrieve the input data
            String fromCity = editTextFrom.getText().toString();
            String toCity = editTextTo.getText().toString();

            // Validate city names
            if (!citiesSet.contains(fromCity)) {
                Toast.makeText(this, "Please select a valid city for 'From'", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!citiesSet.contains(toCity)) {
                Toast.makeText(this, "Please select a valid city for 'To'", Toast.LENGTH_SHORT).show();
                return;
            }

            String departureDate = editTextDeparture.getText().toString();
            String returnDate = editTextReturn.getText().toString();

            if (!validateReturnDate()) {
                Toast.makeText(this, "Return date must be after departure date", Toast.LENGTH_SHORT).show();
                return;
            }

            int passengerCount = Integer.parseInt(editTextPassenger.getText().toString());
            int childrenCount = Integer.parseInt(editTextChildren.getText().toString());
            int petCount = Integer.parseInt(editTextPet.getText().toString());
            int luggageCount = Integer.parseInt(editTextLuggage.getText().toString());
            boolean isEconomy = radioButtonEconomy.isChecked();
            String transportMode = "Plane";

            // Create an Intent to start SearchBookingActivity
            Intent intent = new Intent(TransportActivity.this, SearchBookingActivity.class);
            intent.putExtra("fromCity", fromCity);
            intent.putExtra("toCity", toCity);
            intent.putExtra("departureDate", departureDate);
            intent.putExtra("returnDate", returnDate);
            intent.putExtra("passengerCount", passengerCount);
            intent.putExtra("childrenCount", childrenCount);
            intent.putExtra("petCount", petCount);
            intent.putExtra("luggageCount", luggageCount);
            intent.putExtra("isEconomy", isEconomy);
            intent.putExtra("transportMode", transportMode);

            // Start the activity
            startActivity(intent);
        });
    }

    private void showDatePickerDialog(final EditText editText, boolean isDeparture) {
        final Calendar calendar = isDeparture ? departureCalendar : returnCalendar;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {
                    calendar.set(year1, month1, dayOfMonth);
                    editText.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);

                    if (!isDeparture && !validateReturnDate()) {
                        Toast.makeText(this, "Return date must be after departure date", Toast.LENGTH_SHORT).show();
                        editTextReturn.setText("");
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    private boolean validateReturnDate() {
        return returnCalendar.after(departureCalendar);
    }
}
