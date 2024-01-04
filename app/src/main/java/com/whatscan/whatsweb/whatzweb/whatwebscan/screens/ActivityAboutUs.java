package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.whatscan.whatsweb.whatzweb.whatwebscan.BuildConfig;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActAboutUsBinding;

public class ActivityAboutUs extends AppCompatActivity {
    ActAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActAboutUsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("About Us");
        binding.tvAppVersion.setText("Version " + BuildConfig.VERSION_NAME);
    }

}