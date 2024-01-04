package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActThankYouBinding;

public class ThankYouActivity extends BaseActivity {
    ActThankYouBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActThankYouBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        hideSystemBars();

        new Handler(Looper.getMainLooper()).postDelayed(this::finish, 1500);
    }
}