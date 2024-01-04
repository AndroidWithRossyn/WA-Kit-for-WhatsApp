package com.SachinApps.Whatscan.Pro.WhatsClone.wa_videos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.AdsManager;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActWhatsappVideosBinding;

public class WhatsappVideosActivity extends AppCompatActivity {

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

        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.isNetworkConnected(WhatsappVideosActivity.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            AdsManager.loadBannerAd(WhatsappVideosActivity.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));
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