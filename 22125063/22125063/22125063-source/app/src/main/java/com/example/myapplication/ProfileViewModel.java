package com.example.myapplication;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<Uri> imageUri = new MutableLiveData<>();

    public void setImageUri(Uri uri) {
        imageUri.setValue(uri);
    }
    public LiveData<Uri> getImageUri() {
        return imageUri;
    }
}
