package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AccountFragment extends Fragment {

    private TextView profileNameTextView;
    private ImageView profileImageView; // Added ImageView reference
    private ProfileViewModel profileViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        profileImageView = view.findViewById(R.id.profile_image);
        profileNameTextView = view.findViewById(R.id.profile_name);
        profileImageView = view.findViewById(R.id.profile_image);

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        profileViewModel.getImageUri().observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                profileImageView.setImageURI(uri);
            }
        });

        // Load and set initial profile name and image (if needed)
        loadProfileData();

        view.findViewById(R.id.personal_info).setOnClickListener(this::onPersonalInfoClick);
        view.findViewById(R.id.payment_and_cards).setOnClickListener(this::onPaymentAndCardsClick);
        view.findViewById(R.id.saved).setOnClickListener(this::onSavedClick);
        view.findViewById(R.id.booking_history).setOnClickListener(this::onBookingHistoryClick);
        view.findViewById(R.id.settings).setOnClickListener(this::onSettingsClick);


        return view;
    }
    public void onToggleClicked(View view) {
        boolean isChecked = ((ToggleButton) view).isChecked();

        if (isChecked) {
            // Switch to dark mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Switch to light mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Recreate the activity to apply theme changes
        requireActivity().recreate();
    }
    private void loadProfileData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");

        String fullName = firstName + " " + lastName;
        if (profileNameTextView != null) {
            profileNameTextView.setText(fullName);
        }

        // Load profile image from SharedPreferences if available
//        String avatarImageUri = sharedPreferences.getString("avatarImageUri", null);
//        if (avatarImageUri != null) {
//            try {
//                InputStream inputStream = requireActivity().getContentResolver().openInputStream(Uri.parse(avatarImageUri));
//                profileImageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void updateProfileName(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (profileNameTextView != null) {
            profileNameTextView.setText(fullName);
        }
    }

    public void onPersonalInfoClick(View view) {
        Fragment accountInformationFragment = new AccountInformationFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, accountInformationFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onPaymentAndCardsClick(View view) {
        Toast.makeText(getActivity(), "Payment and Cards is in development", Toast.LENGTH_SHORT).show();
    }

    public void onSavedClick(View view) {
        Toast.makeText(getActivity(), "Saved is in development", Toast.LENGTH_SHORT).show();
    }

    public void onBookingHistoryClick(View view) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyTickets", Context.MODE_PRIVATE);

        // Retrieve ticket list
        Gson gson = new Gson();
        String json = sharedPreferences.getString("selectedSeats", "");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> savedTickets = gson.fromJson(json, type);

        // Retrieve flight information
        String fromCity = sharedPreferences.getString("fromCity", "");
        String toCity = sharedPreferences.getString("toCity", "");
        String flightID = sharedPreferences.getString("flightID", "");
        String flightClass = sharedPreferences.getString("flightClass", "");
        String departureDate = sharedPreferences.getString("departureDate", "");
        int departureTime = sharedPreferences.getInt("departureTime", -1);
        int numberOfPassengers = sharedPreferences.getInt("numberOfPassengers", 1);
        Intent ticketIntent = new Intent(getActivity(), TicketActivity.class);

        // Add any extra data you need to pass to the TicketActivity
        Log.d("TicketIntent", "fromCity: " + fromCity);
        ticketIntent.putExtra("fromCity", fromCity);

        Log.d("TicketIntent", "toCity: " + toCity);
        ticketIntent.putExtra("toCity", toCity);

        Log.d("TicketIntent", "flightNumber: " + flightID);
        ticketIntent.putExtra("flightNumber", flightID);

        Log.d("TicketIntent", "departureDate: " + departureDate);
        ticketIntent.putExtra("departureDate", departureDate);

        Log.d("TicketIntent", "departureTime: " + departureTime);
        ticketIntent.putExtra("departureTime", departureTime);

        Log.d("TicketIntent", "numberOfPassengers: " + numberOfPassengers);
        ticketIntent.putExtra("numberOfPassengers", numberOfPassengers);

        Log.d("TicketIntent", "flightClass: " + flightClass);
        ticketIntent.putExtra("flightClass", flightClass);

        Log.d("TicketIntent", "selectedSeats: " + new ArrayList<>(savedTickets));
        ticketIntent.putExtra("selectedSeats", new ArrayList<>(savedTickets));

        startActivity(ticketIntent);

    }

    public void onSettingsClick(View view) {
        Toast.makeText(getActivity(), "Settings is in development", Toast.LENGTH_SHORT).show();
    }
}