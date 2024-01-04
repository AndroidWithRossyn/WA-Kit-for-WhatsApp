package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.SachinApps.Whatscan.Pro.WhatsClone.BuildConfig;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {
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