package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.AdsManager;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActSearchProfileBinding;


public class SearchProfileActivity extends AppCompatActivity {
    ActSearchProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSearchProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Search Profile");

        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getBooleanValue(UserHelper.showInterstitial) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            Log.d("databseConfig", "load Inters");
            AdsManager.loadNativeAds(SearchProfileActivity.this, binding.flAdplaceholder, UserHelper.getStringValue(UserHelper.nativeAdId));
        }

        binding.btnSearchProfile.setOnClickListener(v -> {
            if (isValidMobile(binding.etMobileNumber.getText().toString())) {
                Uri uri = Uri.parse("smsto:" + binding.etMobileNumber.getText().toString());
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            } else {
                Toast.makeText(SearchProfileActivity.this, "Enter valid Mobile Number..", Toast.LENGTH_SHORT).show();
            }

        });

        if (UserHelper.isNetworkConnected(SearchProfileActivity.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            AdsManager.loadBannerAd(SearchProfileActivity.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));
        } else {
            binding.bannerContainer.setVisibility(View.GONE);
        }
   }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public void backclickProfile(View view) {
        onBackPressed();
    }
}