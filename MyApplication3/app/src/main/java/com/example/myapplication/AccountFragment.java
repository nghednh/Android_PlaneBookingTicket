package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
        Toast.makeText(getActivity(), "Booking History is in development", Toast.LENGTH_SHORT).show();
    }

    public void onSettingsClick(View view) {
        Toast.makeText(getActivity(), "Settings is in development", Toast.LENGTH_SHORT).show();
    }
}