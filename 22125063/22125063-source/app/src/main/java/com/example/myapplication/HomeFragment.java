package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class HomeFragment extends Fragment {

    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> cities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        searchView = rootView.findViewById(R.id.searchView);
        listView = rootView.findViewById(R.id.listView);

        // Initialize cities data
        String[] citiesArray = getResources().getStringArray(R.array.cities);
        cities = new ArrayList<>(Arrays.asList(citiesArray));

        // Initialize adapter
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, cities);
        listView.setAdapter(adapter);

        // Hide ListView initially
        listView.setVisibility(View.GONE);

        setupSearchView();

        // Set up feature-in-development button click listeners
        setupFeatureInDevelopmentButtons(rootView);

        // Set up transport button click listener
        setupTransportButton(rootView);

        return rootView;
    }


    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Filter the list based on the query
                adapter.getFilter().filter(query);

                // Show the ListView
                listView.setVisibility(View.VISIBLE);

                // Return true to indicate the query has been handled
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optionally handle text changes if needed
                return false;
            }
        });
    }
    private void setupFeatureInDevelopmentButtons(View rootView) {
        ImageButton ivHotel = rootView.findViewById(R.id.ivHotel);
        ImageButton ivEvents = rootView.findViewById(R.id.ivEvents);
        ImageButton ivTrips = rootView.findViewById(R.id.ivTrips);

        View.OnClickListener featureInDevelopmentListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature is currently in development!", Toast.LENGTH_SHORT).show();
            }
        };

        ivHotel.setOnClickListener(featureInDevelopmentListener);
        ivEvents.setOnClickListener(featureInDevelopmentListener);
        ivTrips.setOnClickListener(featureInDevelopmentListener);
    }

    private void setupTransportButton(View rootView) {
        ImageButton ivTransport = rootView.findViewById(R.id.ivTransport);
        ivTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransportActivity.class);
                startActivity(intent);
            }
        });
    }
}

