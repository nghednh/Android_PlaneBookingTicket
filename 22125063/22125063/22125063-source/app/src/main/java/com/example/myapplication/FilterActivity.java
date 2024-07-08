package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private RadioGroup sortGroup, departureGroup, arrivalGroup;
    private EditText minPrice, maxPrice;
    private Button resetButton, doneButton;
    private ImageView backButton;
    RangeSlider rangeSlider;


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
        rangeSlider = findViewById(R.id.priceslider);
        rangeSlider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                currencyFormat.setCurrency(Currency.getInstance("USD"));
                return currencyFormat.format(value);
            }
        });
        rangeSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(RangeSlider slider, float value, boolean fromUser) {
                // Handle the value changes here
                List<Float> values = slider.getValues();
                float minValue1 = values.get(0);
                minPrice.setText(String.valueOf(minValue1));
                float maxValue1 = values.get(1);
                maxPrice.setText(String.valueOf(maxValue1));
            }
        });
    }

    private void resetFilters() {
        sortGroup.clearCheck();
        departureGroup.clearCheck();
        arrivalGroup.clearCheck();
        minPrice.setText("0");
        maxPrice.setText("5000");
    }

    private void applyFilters() {
        Intent intent = new Intent();

        int selectedSortId = sortGroup.getCheckedRadioButtonId();
        int selectedDepartureId = departureGroup.getCheckedRadioButtonId();
        int selectedArrivalId = arrivalGroup.getCheckedRadioButtonId();

        String minPriceValue1 = minPrice.getText().toString();
        int minPriceValue=Integer.parseInt(minPriceValue1);
        String maxPriceValue1 = maxPrice.getText().toString();
        int maxPriceValue=Integer.parseInt(maxPriceValue1);

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

        if (!minPriceValue1.isEmpty()) {
            intent.putExtra("minPrice", minPriceValue);
        }

        if (!maxPriceValue1.isEmpty()) {
            intent.putExtra("maxPrice", maxPriceValue);
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}
