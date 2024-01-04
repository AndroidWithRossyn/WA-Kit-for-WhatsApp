package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.Utility;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActThankYouBinding;

public class ActivityThankYou extends BaseActivity {
    ActThankYouBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActThankYouBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        hideSystemBars();

        Bitmap bitmapLocal1 = Utility.decodeSampledBitmapFromResource(getResources(), R.drawable.splash_preview, 500, 500);
        binding.ivAppIcon.setImageBitmap(bitmapLocal1);

        new Handler(Looper.getMainLooper()).postDelayed(this::finish, 1500);
    }
}