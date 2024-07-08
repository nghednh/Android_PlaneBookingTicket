package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FlightAdapter extends ArrayAdapter<Flight> {

    private static final String TAG = "FlightAdapter";
    private LayoutInflater inflater;

    public FlightAdapter(Context context, List<Flight> flights) {
        super(context, 0, flights);
        inflater = LayoutInflater.from(context);
        Log.d(TAG, "FlightAdapter initialized with " + flights.size() + " flights");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView called for position: " + position);

        Flight flight = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_flight, parent, false);
            Log.d(TAG, "convertView inflated for position: " + position);
        }

        TextView textFromCity = convertView.findViewById(R.id.text_from_city);
        TextView textToCity = convertView.findViewById(R.id.text_to_city);
        TextView textDate = convertView.findViewById(R.id.text_date);
        TextView textDepartureTime = convertView.findViewById(R.id.text_departure_time);
        TextView textPrice = convertView.findViewById(R.id.text_price);
        TextView textFlightNumber = convertView.findViewById(R.id.text_flight_number);

        if (flight != null) {
            textFromCity.setText(flight.getFromCity());
            textToCity.setText(flight.getToCity());
            textDate.setText(flight.getDepartureDate());
            textDepartureTime.setText(String.valueOf(flight.getDepartureTime())+":00");
            textPrice.setText("$"+String.valueOf(flight.getPrice()));
            textFlightNumber.setText(flight.getFlightNumber());

            Log.d(TAG, "Flight details set for position: " + position);
        }

        return convertView;
    }

    public void updateFlights(List<Flight> newFlights) {
        Log.d(TAG, "Updating flights with new list of size: " + newFlights.size());

        clear();
        addAll(newFlights);
        notifyDataSetChanged();
    }
}
