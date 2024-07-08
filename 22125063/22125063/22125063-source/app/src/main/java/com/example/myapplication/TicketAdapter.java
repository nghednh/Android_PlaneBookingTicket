package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TicketAdapter extends ArrayAdapter<Ticket> {

    public TicketAdapter(Context context, List<Ticket> tickets) {
        super(context, 0, tickets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Ticket ticket = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticket, parent, false);
        }

        // Lookup view for data population
        TextView tvFromCity = convertView.findViewById(R.id.tvFromCity);
        TextView tvToCity = convertView.findViewById(R.id.tvToCity);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvDepartureTime = convertView.findViewById(R.id.tvDepartureTime);
        TextView tvClass = convertView.findViewById(R.id.tvClass);
        TextView tvTicket = convertView.findViewById(R.id.tvTicket);
        TextView tvSeat = convertView.findViewById(R.id.tvSeat);

        // Populate the data into the template view using the data object
        tvFromCity.setText(ticket.getFromCity());
        tvToCity.setText(ticket.getToCity());
        tvDate.setText(ticket.getDepartureDate());
        tvDepartureTime.setText(ticket.getDepartureTime());
        tvClass.setText(ticket.getFlightClass());
        tvTicket.setText(ticket.getTicketID());
        tvSeat.setText(ticket.getSeat());

        // Return the completed view to render on screen
        return convertView;
    }
}
