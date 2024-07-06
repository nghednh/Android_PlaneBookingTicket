package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FlightAdapter extends ArrayAdapter<Flight> {

    public FlightAdapter(Context context, List<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Flight flight = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_flight, parent, false);
        }

        TextView textFromCity = convertView.findViewById(R.id.text_from_city);
        TextView textToCity = convertView.findViewById(R.id.text_to_city);
        TextView textDate = convertView.findViewById(R.id.text_date);
        TextView textDepartureTime = convertView.findViewById(R.id.text_departure_time);
        TextView textPrice = convertView.findViewById(R.id.text_price);
        TextView textFlightNumber = convertView.findViewById(R.id.text_flight_number);

        textFromCity.setText(flight.getFromCity());
        textToCity.setText(flight.getToCity());
        textDate.setText(flight.getDepartureDate());
        textDepartureTime.setText(flight.getDepartureTime());
        textPrice.setText(flight.getPrice());
        textFlightNumber.setText(flight.getFlightNumber());

        return convertView;
    }
}
//package com.example.myapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class FlightAdapter extends ArrayAdapter<Flight> {
//
//    public FlightAdapter(Context context, List<Flight> flights) {
//        super(context, 0, flights);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Flight flight = getItem(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_flight, parent, false);
//        }
//
//        TextView textFromCity = convertView.findViewById(R.id.text_from_city);
//        TextView textToCity = convertView.findViewById(R.id.text_to_city);
//        TextView textDate = convertView.findViewById(R.id.text_date);
//        TextView textDepartureTime = convertView.findViewById(R.id.text_departure_time);
//        TextView textPrice = convertView.findViewById(R.id.text_price);
//        TextView textFlightNumber = convertView.findViewById(R.id.text_flight_number);
//
//        textFromCity.setText(flight.getFromCity());
//        textToCity.setText(flight.getToCity());
//        textDate.setText(flight.getDepartureDate());
//        textDepartureTime.setText(flight.getDepartureTime());
//        textPrice.setText(flight.getPrice());
//        textFlightNumber.setText(flight.getFlightNumber());
//
//        return convertView;
//    }
//}
//
