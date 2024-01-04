package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActInfoWhatsAppBinding;


public class ActivityInfoWhatsApp extends AppCompatActivity {
    ActInfoWhatsAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActInfoWhatsAppBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Whatsapp Info");


        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo("com.whatsapp", 0);
            String version = pInfo.versionName;
            binding.tvVersion.setText("Version " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void backWhatsappInfo(View view) {
        onBackPressed();
    }
}