package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class FilterActivity extends AppCompatActivity {

    private RadioGroup sortGroup, departureGroup, arrivalGroup;
    private EditText minPrice, maxPrice;
    private Button resetButton, doneButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        sortGroup = findViewById(R.id.sortGroup);
        departureGroup = findViewById(R.id.departureGroup);
        arrivalGroup = findViewById(R.id.arrivalGroup);
        minPrice = findViewById(R.id.minPrice);
        maxPrice = findViewById(R.id.maxPrice);
        resetButton = findViewById(R.id.resetButton);
        doneButton = findViewById(R.id.doneButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> finish());

        resetButton.setOnClickListener(view -> resetFilters());

        doneButton.setOnClickListener(view -> applyFilters());
    }

    private void resetFilters() {
        sortGroup.clearCheck();
        departureGroup.clearCheck();
        arrivalGroup.clearCheck();
        minPrice.setText("");
        maxPrice.setText("");
    }

    private void applyFilters() {
        Intent intent = new Intent();

        int selectedSortId = sortGroup.getCheckedRadioButtonId();
        int selectedDepartureId = departureGroup.getCheckedRadioButtonId();
        int selectedArrivalId = arrivalGroup.getCheckedRadioButtonId();

        String minPriceValue = minPrice.getText().toString();
        String maxPriceValue = maxPrice.getText().toString();

        if (selectedSortId != -1) {
            RadioButton selectedSortButton = findViewById(selectedSortId);
            intent.putExtra("sortCriteria", selectedSortButton.getText().toString());
        }

        if (selectedDepartureId != -1) {
            RadioButton selectedDepartureButton = findViewById(selectedDepartureId);
            intent.putExtra("departureTime", selectedDepartureButton.getText().toString());
        }

        if (selectedArrivalId != -1) {
            RadioButton selectedArrivalButton = findViewById(selectedArrivalId);
            intent.putExtra("arrivalTime", selectedArrivalButton.getText().toString());
        }

        if (!minPriceValue.isEmpty()) {
            intent.putExtra("minPrice", Double.parseDouble(minPriceValue));
        }

        if (!maxPriceValue.isEmpty()) {
            intent.putExtra("maxPrice", Double.parseDouble(maxPriceValue));
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}
