package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.whatscan.whatsweb.whatzweb.whatwebscan.fragment.FragmentExtras;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActivitySettingBinding;

public class ActivitySetting extends AppCompatActivity {

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