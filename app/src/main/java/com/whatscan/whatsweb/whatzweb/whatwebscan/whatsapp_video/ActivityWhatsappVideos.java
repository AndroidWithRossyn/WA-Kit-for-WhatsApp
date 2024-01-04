package com.whatscan.whatsweb.whatzweb.whatwebscan.whatsapp_video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.AdManager;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActWhatsappVideosBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.screens.ActivityMain;

public class ActivityWhatsappVideos extends AppCompatActivity {

    ActWhatsappVideosBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActWhatsappVideosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Whatsapp Videos");

        FragmentVideo newFragment = new FragmentVideo();

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.isNetworkConnected(ActivityWhatsappVideos.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            AdManager.loadBannerAd(ActivityWhatsappVideos.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));
        } else {
            binding.bannerContainer.setVisibility(View.GONE);
        }
    }

    public void backWpVideo(View view) {
        finish();
//        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}