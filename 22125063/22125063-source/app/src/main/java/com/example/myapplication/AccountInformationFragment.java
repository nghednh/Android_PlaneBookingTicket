package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class AccountInformationFragment extends Fragment {

    private static final int PICK_IMAGE = 100;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private Button saveButton;
    private Button changeAvatarButton;
    private ImageView avatarImageView; // Added for avatar image handling
    private ProfileViewModel profileViewModel;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_information, container, false);

        // Initialize views
        firstNameEditText = view.findViewById(R.id.edit_first_name);
        lastNameEditText = view.findViewById(R.id.edit_last_name);
        phoneEditText = view.findViewById(R.id.edit_phone);
        emailEditText = view.findViewById(R.id.edit_email);
        saveButton = view.findViewById(R.id.button_save);
        avatarImageView = view.findViewById(R.id.avatar_image); // Initialize avatar image view
        changeAvatarButton = view.findViewById(R.id.change_avatar_but);
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        profileViewModel.getImageUri().observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                avatarImageView.setImageURI(uri);
            }
        });

        changeAvatarButton.setOnClickListener(v -> openGallery());
        // Load saved data
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loadUserData();

         //Save button click listener
        saveButton.setOnClickListener(v -> saveUserData());
        return view;
    }

    // Method to load user data from SharedPreferences
    private void loadUserData() {
        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");
        String phone = sharedPreferences.getString("phone", "");
        String email = sharedPreferences.getString("email", "");

        firstNameEditText.setText(firstName);
        lastNameEditText.setText(lastName);
        phoneEditText.setText(phone);
        emailEditText.setText(email);

        // Load and set avatar image URI if available
//        String avatarImageUri = sharedPreferences.getString("avatarImageUri", null);
//        if (avatarImageUri != null) {
//            try {
//                avatarImageView.setImageURI(Uri.parse(avatarImageUri));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    // Method to save user data to SharedPreferences
    private void saveUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName", firstNameEditText.getText().toString());
        editor.putString("lastName", lastNameEditText.getText().toString());
        editor.putString("phone", phoneEditText.getText().toString());
        editor.putString("email", emailEditText.getText().toString());
        editor.apply();

        // Update profile name in AccountFragment after saving
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        updateProfileName(firstName, lastName);
    }

    // Method to update profile name in AccountFragment
    private void updateProfileName(String firstName, String lastName) {
        // Find AccountFragment and update profile name dynamically
        AccountFragment accountFragment = (AccountFragment) getParentFragmentManager().findFragmentByTag("AccountFragment");
        if (accountFragment != null) {
            accountFragment.updateProfileName(firstName, lastName);
        }
    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            if (data != null) {
                Uri imageUri = data.getData();
                //avatarImageView.setImageURI(imageUri);
                profileViewModel.setImageUri(imageUri);
            }
        }
    }
}