package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.SachinApps.Whatscan.Pro.WhatsClone.fragment.FragmentExtras;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentExtras()).commit();

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());

    }
}