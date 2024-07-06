package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class BookingFragment extends Fragment {

    private int[] images = {R.drawable.trips, R.drawable.transport, R.drawable.events, R.drawable.hotel};
    private String[] texts = {"Trips", "Transport", "Events", "Hotels"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        ListView listView = view.findViewById(R.id.listView);
        ImageButtonAdapter adapter = new ImageButtonAdapter(getContext(), images, texts);
        listView.setAdapter(adapter);

        return view;
    }
}
